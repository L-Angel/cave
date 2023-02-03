package fun.langel.cql.resolve.dialect;

import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.ElasticSearchQDL;
import fun.langel.cql.enums.Order;
import fun.langel.cql.node.*;
import fun.langel.cql.node.func.*;
import fun.langel.cql.node.operator.*;
import fun.langel.cql.rv.Number;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.ArrayUtil;
import fun.langel.cql.util.ListUtil;
import fun.langel.cql.util.RandomUtil;
import fun.langel.cql.util.StringUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.AggregatorFactories;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 20:59
 **/
public class ElasticSearchQDLDialectResolver implements ElasticSearchDialectResolver<SelectStatement, SearchRequest> {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchDialectResolver.class);

    @Override
    public Dialect<SearchRequest> resolve(SelectStatement statement) {
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        if (statement.limit() != null) {
            ssb.from(statement.limit().offset());
            ssb.size(statement.limit().fetch());
        }
        ssb.fetchSource(sourceFields(statement.columns()), null);
        if (statement.where() != null) {
            List<QueryBuilder> builders = resolveQueryCondition(statement.where());
            if (ListUtil.isNullOrEmpty(builders)) {
                ssb.query(QueryBuilders.matchAllQuery());
            } else {
                BoolQueryBuilder qb = QueryBuilders.boolQuery();
                qb.must().addAll(builders);
                ssb.query(qb);
            }
        }
        if (statement.orderBy() != null) {
            sort(ssb, statement.orderBy());
        }
        if (statement.columns().stream().anyMatch(Column::isFunction)) {
            for (AggregationBuilder aggBuilder : statsAggregation(statement.columns())) {
                ssb.aggregation(aggBuilder);
            }
        }
        List<String> tables = ListUtil.isNullOrEmpty(statement.tables()) ? null : statement.tables().stream().map(Table::getName).map(String::toLowerCase).collect(Collectors.toList());
        SearchRequest sr = new SearchRequest(ListUtil.toStringArray(tables));
        sr.searchType(SearchType.DEFAULT);
        sr.source(ssb);
        if (LOG.isDebugEnabled()) {
            LOG.debug("ElasticSearch Qdl : {}", sr.source());
        }
        return new ElasticSearchQDL(sr);
    }

    private List<AggregationBuilder> statsAggregation(List<Column> funcCols) {
        if (ListUtil.isNullOrEmpty(funcCols)) {
            return Collections.emptyList();
        }
        List<AggregationBuilder> builders = new LinkedList<>();
        for (int idx = 0, len = funcCols.size(); idx < len; idx++) {
            Column col = funcCols.get(idx);
            if (!col.isFunction()) {
                continue;
            }
            Function func = col.function();
            if (func instanceof Avg) {
                AvgAggregationBuilder avg = AggregationBuilders.avg(StringUtil.withDefault(col.name(), "avg_" + idx));
                avg.field(((Column) func.executable()).name());
                builders.add(avg);
            } else if (func instanceof Count) {
                if (((Count) func).matchAll()) {
                    continue;
                }
                ValueCountAggregationBuilder count = AggregationBuilders.count(StringUtil.withDefault(col.name(), "count_" + idx));
                count.field(((Column) func.executable()).name());
                builders.add(count);
            } else if (func instanceof Sum) {
                SumAggregationBuilder sum = AggregationBuilders.sum(StringUtil.withDefault(col.name(), "sum_" + idx));
                sum.field(((Column) func.executable()).name());
                builders.add(sum);
            }
        }
        return builders;
    }

    private String[] sourceFields(List<Column> columns) {
        if (ListUtil.isNullOrEmpty(columns)) {
            return null;
        }
        // if (v.function() instanceof C_KeyValue) {
        //     return ((C_KeyValue)v.function()).executable().
        // } else {
        //     return ((Column) v).name();
        // }
        List<String> fields = columns.stream().filter(v -> ((v != null) && !v.isFunction())).map(Column::name).collect(Collectors.toList());
        String[] nFields = new String[fields.size()];
        return ListUtil.isNullOrEmpty(fields) ? null : fields.toArray(nFields);
    }

    protected void sort(SearchSourceBuilder builder, OrderBy orderBy) {
        for (Column col : orderBy.columns()) {
            if (col.order() == Order.ASC) {
                builder.sort(col.name(), SortOrder.ASC);
            } else {
                builder.sort(col.name(), SortOrder.DESC);
            }
        }

    }

    private List<QueryBuilder> resolveQueryCondition(Expr expr) {
        Operator operator = expr.operator();
        if (operator == LogicalOperator.OR) {
            final List<QueryBuilder> builders = new LinkedList<>();
            builders.addAll(resolveQueryCondition((Expr) expr.left()));
            builders.addAll(resolveQueryCondition((Expr) expr.right()));
            BoolQueryBuilder qb = QueryBuilders.boolQuery();
            qb.should().addAll(builders);
            return Collections.singletonList(qb);
        } else if (operator == LogicalOperator.AND) {
            final List<QueryBuilder> builders = new LinkedList<>();
            builders.addAll(resolveQueryCondition((Expr) expr.left()));
            builders.addAll(resolveQueryCondition((Expr) expr.right()));
            return builders;
        } else if (operator instanceof RelOperator) { // 关系运算符
            return resolveRelOperator((RelOperator) operator, expr);
        } else if (operator instanceof BetweenOperator) {
            final RangeQueryBuilder rqb = QueryBuilders.rangeQuery(((Column) expr.left()).name());
            rqb.from(expr.begin().value());
            rqb.to(expr.end().value());
            return Collections.singletonList(rqb);
        } else if (operator instanceof FunctionOperator) {
            if (operator == FunctionOperator.C_EXISTS) {
                C_Exists c_exists = (C_Exists) expr;
                return Collections.singletonList(QueryBuilders.existsQuery(c_exists.executable().toString()));
            } else if (operator == FunctionOperator.C_SCRIPT) {
                C_Script c_script = (C_Script) expr;
                return Collections.singletonList(QueryBuilders.scriptQuery(new Script(c_script.executable().toString())));
            }
        }
        return Collections.emptyList();
    }


    private List<QueryBuilder> resolveRelOperator(RelOperator operator, Expr expr) {
        String name = ((Column) expr.left()).name();
        if (operator == RelOperator.IN || operator == RelOperator.NOT_IN) {
            Range range = (Range) expr.right();
            if (operator == RelOperator.IN) {
                return Collections.singletonList(QueryBuilders.termsQuery(name + ".keyword", range.values().stream().map(Value::value).collect(Collectors.toList())));
            } else if (operator == RelOperator.NOT_IN) {
                return Collections.singletonList(QueryBuilders.boolQuery().mustNot(QueryBuilders.termsQuery(name + ".keyword", range.values().stream().map(Value::value).collect(Collectors.toList()))));
            }
        } else {
            Value cValue = (Value) expr.right();
            if (cValue.isNull()) {
                return Collections.emptyList();
            }
            switch (operator) {
                case LESS:
                    return Collections.singletonList(QueryBuilders.rangeQuery(name).lt(cValue.value()));
                case LESS_OR_EQUALS:
                    return Collections.singletonList(QueryBuilders.rangeQuery(name).lte(cValue.value()));
                case GREATER:
                    return Collections.singletonList(QueryBuilders.rangeQuery(name).gt(cValue.value()));
                case GREATER_OR_EQUALS:
                    return Collections.singletonList(QueryBuilders.rangeQuery(name).gte(cValue.value()));
                case EQUAL:
                    if (cValue.type() == Value.Type.NUMBER) {
                        return Collections.singletonList(QueryBuilders.termQuery(name, cValue.value()));
                    }
                    return Collections.singletonList(QueryBuilders.termQuery(name + ".keyword", cValue.value()));
                case NOT_EQUAL:
                    if (cValue.type() == Value.Type.NUMBER) {
                        return Collections.singletonList(QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(name, cValue.value())));
                    }
                    return Collections.singletonList(QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(name + ".keyword", cValue.value())));
                case LIKE:
                    return Collections.singletonList(QueryBuilders.matchPhraseQuery(name, cValue.value()));
                case NOT_LIKE:
                    return Collections.singletonList(QueryBuilders.boolQuery().mustNot(QueryBuilders.matchPhraseQuery(name, cValue.value())));
            }
        }
        return Collections.emptyList();
    }
}
