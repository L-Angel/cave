package fun.langel.cql.resolve.dialect;

import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.ElasticSearchQDL;
import fun.langel.cql.enums.Order;
import fun.langel.cql.node.*;
import fun.langel.cql.node.func.C_Exists;
import fun.langel.cql.node.func.C_Script;
import fun.langel.cql.node.operator.*;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.ArrayUtil;
import fun.langel.cql.util.ListUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 20:59
 **/
public class ElasticSearchQDLDialectResolver implements ElasticSearchDialectResolver<SelectStatement, SearchRequest> {

    @Override
    public Dialect<SearchRequest> resolve(SelectStatement statement) {
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        if (statement.limit() != null) {
            ssb.from(statement.limit().offset());
            ssb.size(statement.limit().fetch());
        }
        ssb.fetchSource(sourceFields(statement.columns()), null);
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must().addAll(resolveQueryCondition(statement.where()));
        ssb.query(qb);
        if (statement.orderBy() != null) {
            sort(ssb, statement.orderBy());
        }

        List<String> tables = ListUtil.isNullOrEmpty(statement.tables()) ? null : statement.tables().stream().map(Table::getName).map(String::toLowerCase).collect(Collectors.toList());
        SearchRequest sr = new SearchRequest(ListUtil.toStringArray(tables));
        sr.searchType(SearchType.DEFAULT);
        sr.source(ssb);
        return new ElasticSearchQDL(sr);
    }

    private String[] sourceFields(List<Node> columns) {
        if (ListUtil.isNullOrEmpty(columns)) {
            return null;
        }
        List<String> fields = columns.stream()
                .filter(v -> v instanceof Column)
                .map(v -> ((Column) v).name()).collect(Collectors.toList());
        return ArrayUtil.toArray(fields);
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
        } else if (operator instanceof RelOperator) {
            String name = ((Column) expr.left()).name();
            if (operator == RelOperator.IN || operator == RelOperator.NOT_IN) {
                Range range = (Range) expr.right();
                if (operator == RelOperator.IN) {
                    return Collections.singletonList(QueryBuilders.termsQuery(name + ".keyword", range.values().stream().map(Value::value).collect(Collectors.toList())));
                } else if (operator == RelOperator.NOT_IN) {
                    BoolQueryBuilder bqb = QueryBuilders.boolQuery();
                    bqb.mustNot(QueryBuilders.termsQuery(name + ".keyword", range.values().stream().map(Value::value).collect(Collectors.toList())));
                    return Collections.singletonList(bqb);
                }
            } else {
                Object value = ((Value) (expr).right()).value();

                final RangeQueryBuilder qb = QueryBuilders.rangeQuery(name);

                if (operator == RelOperator.LESS) {
                    qb.lt(value);
                    return Collections.singletonList(qb);
                } else if (operator == RelOperator.LESS_OR_EQUALS) {
                    qb.lte(value);
                    return Collections.singletonList(qb);
                } else if (operator == RelOperator.GREATER) {
                    qb.gt(value);
                    return Collections.singletonList(qb);
                } else if (operator == RelOperator.GREATER_OR_EQUALS) {
                    qb.gte(value);
                    return Collections.singletonList(qb);
                }
                if (operator == RelOperator.EQUAL) {
                    return Collections.singletonList(QueryBuilders.matchPhraseQuery(name + ".keyword", value));
                } else if (operator == RelOperator.NOT_EQUAL) {
                    BoolQueryBuilder bqb = QueryBuilders.boolQuery();
                    bqb.mustNot(QueryBuilders.matchPhraseQuery(name + ".keyword", value));
                    return Collections.singletonList(bqb);
                }

                if (operator == RelOperator.LIKE) {
                    return Collections.singletonList(QueryBuilders.matchPhraseQuery(name, value));
                } else if (operator == RelOperator.NOT_LIKE) {
                    BoolQueryBuilder bqb = QueryBuilders.boolQuery();
                    bqb.mustNot(QueryBuilders.matchPhraseQuery(name, value));
                    return Collections.singletonList(bqb);
                }
            }
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


}
