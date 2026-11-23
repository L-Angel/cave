package fun.langel.cql.resolve.dialect;

import fun.langel.cql.capability.Capabilities;
import fun.langel.cql.capability.CapabilityValidator;
import fun.langel.cql.constant.Const;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.ElasticSearchQDL;
import fun.langel.cql.enums.Order;
import fun.langel.cql.node.*;
import fun.langel.cql.node.func.*;
import fun.langel.cql.node.operator.*;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.AggregationUtil;
import fun.langel.cql.util.ListUtil;
import fun.langel.cql.util.OrderByUtil;
import fun.langel.cql.util.Props;
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
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 20:59
 **/
public class ElasticSearchQDLDialectResolver implements ElasticSearchDialectResolver<SelectStatement, SearchRequest> {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchDialectResolver.class);

    private static final int DEFAULT_SIZE = 10000;

    public static final String GROUP_AGGREGATION = "__cave_group";

    @Override
    public Dialect<SearchRequest> resolve(SelectStatement statement) {
        CapabilityValidator.validate(statement, Capabilities.ELASTICSEARCH);
        List<Column> columns = statement.columns() == null ? Collections.emptyList() : statement.columns();
        validateColumns(columns);
        validateTables(statement);

        boolean grouped = statement.groupBy() != null;
        if (statement.having() != null && !grouped) {
            throw new UnsupportedOperationException("HAVING is only supported together with GROUP BY for elasticsearch yet.");
        }
        if (grouped) {
            validateGroupBy(statement, columns);
        }

        boolean hasAggregate = columns.stream().anyMatch(AggregationUtil::isAggregateColumn);
        boolean hasPlain = columns.stream().anyMatch(column -> !column.isFunction());
        boolean hasNonAggregateFunction = columns.stream()
                .anyMatch(column -> column.isFunction() && !AggregationUtil.isAggregate(column.function()));
        if (!grouped && hasAggregate && (hasPlain || hasNonAggregateFunction)) {
            throw new UnsupportedOperationException("Mixing aggregate and non-aggregate columns requires GROUP BY.");
        }
        boolean aggregateQuery = hasAggregate && !grouped;

        SearchSourceBuilder ssb = new SearchSourceBuilder();
        if (grouped) {
            // Group results are returned as aggregation buckets, not hits.
            ssb.size(0);
            ssb.fetchSource(false);
        } else if (statement.limit() != null) {
            ssb.from(statement.limit().offset());
            ssb.size(statement.limit().fetch());
            ssb.fetchSource(sourceFields(columns), null);
        } else if (aggregateQuery) {
            ssb.size(0);
            ssb.fetchSource(sourceFields(columns), null);
        } else {
            ssb.size(defaultSize());
            ssb.fetchSource(sourceFields(columns), null);
        }
        if (statement.where() != null) {
            ssb.query(filter(statement.where()));
        }
        if (grouped) {
            ssb.aggregation(groupAggregation(statement, columns));
        } else {
            if (statement.orderBy() != null) {
                sort(ssb, statement.orderBy(), columns);
            }
            if (aggregateQuery) {
                for (AggregationBuilder aggBuilder : statsAggregation(columns, false)) {
                    ssb.aggregation(aggBuilder);
                }
            }
        }
        List<String> tables = ListUtil.isNullOrEmpty(statement.tables()) ? null
                : statement.tables().stream().map(Table::getName)
                .map(name -> name.toLowerCase(Locale.ROOT)).collect(Collectors.toList());
        SearchRequest sr = new SearchRequest(ListUtil.toStringArray(tables));
        sr.searchType(SearchType.DEFAULT);
        sr.source(ssb);
        if (LOG.isDebugEnabled()) {
            LOG.debug("ElasticSearch Qdl : {}", sr.source());
        }
        return new ElasticSearchQDL(sr);
    }

    /**
     * Translates a CQL WHERE expression into an Elasticsearch query. A missing
     * condition becomes match_all, which UPDATE / DELETE use to target every
     * document (matching SQL semantics).
     */
    public QueryBuilder filter(Expr expr) {
        if (expr == null) {
            return QueryBuilders.matchAllQuery();
        }
        List<QueryBuilder> builders = resolveQueryCondition(expr);
        if (ListUtil.isNullOrEmpty(builders)) {
            throw new UnsupportedOperationException("WHERE condition could not be translated to elasticsearch.");
        }
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must().addAll(builders);
        return qb;
    }

    private int defaultSize() {
        int size = Props.getInt(Const.CAVE_ELASTICSEARCH_DEFAULT_SIZE, DEFAULT_SIZE);
        return size > 0 ? size : DEFAULT_SIZE;
    }

    private int groupSize(SelectStatement statement) {
        int size = Props.getInt(Const.CAVE_ELASTICSEARCH_GROUP_SIZE, DEFAULT_SIZE);
        if (size <= 0) {
            size = DEFAULT_SIZE;
        }
        // Without HAVING/ORDER BY the composite bucket order is sufficient, so
        // only fetch as many groups as LIMIT can consume.
        if (statement.having() == null && statement.orderBy() == null && statement.limit() != null) {
            long needed = (long) statement.limit().offset() + statement.limit().fetch();
            if (needed > 0) {
                size = (int) Math.min(size, Math.max(1L, needed));
            }
        }
        return size;
    }

    private void validateGroupBy(SelectStatement statement, List<Column> columns) {
        if (columns.isEmpty()) {
            throw new UnsupportedOperationException("GROUP BY requires an explicit column list.");
        }
        List<Column> groupColumns = statement.groupBy().columns();
        if (groupColumns.isEmpty()) {
            throw new UnsupportedOperationException("GROUP BY requires at least one column.");
        }
        for (Column column : columns) {
            if (AggregationUtil.isAggregateColumn(column)) {
                continue;
            }
            if (column.isFunction()) {
                if (column.function() instanceof C_KeyValue) {
                    String source = ((C_KeyValue) column.function()).column().name();
                    if (groupKeyIndex(source, groupColumns, columns) < 0) {
                        throw new UnsupportedOperationException("c_keyvalue in a GROUP BY query requires its "
                                + "source column in GROUP BY: " + source);
                    }
                    continue;
                }
                throw new UnsupportedOperationException("Function " + column.function().getClass().getSimpleName()
                        + " is not supported in a GROUP BY query.");
            }
            if (groupKeyIndex(column.name(), groupColumns, columns) < 0) {
                throw new UnsupportedOperationException("Column " + column.name()
                        + " must appear in GROUP BY or be aggregated.");
            }
        }
    }

    private CompositeAggregationBuilder groupAggregation(SelectStatement statement, List<Column> columns) {
        List<Column> groupColumns = statement.groupBy().columns();
        List<CompositeValuesSourceBuilder<?>> sources = new ArrayList<>();
        for (int idx = 0; idx < groupColumns.size(); idx++) {
            String source = resolveGroupSource(groupColumns.get(idx).name(), columns);
            sources.add(new TermsValuesSourceBuilder("k" + idx).field(source).missingBucket(true));
        }
        CompositeAggregationBuilder composite =
                new CompositeAggregationBuilder(GROUP_AGGREGATION, sources).size(groupSize(statement));
        for (AggregationBuilder metric : statsAggregation(columns, true)) {
            composite.subAggregation(metric);
        }
        return composite;
    }

    private int groupKeyIndex(String source, List<Column> groupColumns, List<Column> columns) {
        for (int idx = 0; idx < groupColumns.size(); idx++) {
            if (resolveGroupSource(groupColumns.get(idx).name(), columns).equals(source)) {
                return idx;
            }
        }
        return -1;
    }

    private String resolveGroupSource(String name, List<Column> columns) {
        for (Column column : columns) {
            if (!column.isFunction() && column.alias() != null && column.alias().equalsIgnoreCase(name)) {
                return column.name();
            }
        }
        return name;
    }

    private void validateTables(SelectStatement statement) {
        if (statement.tables() == null) {
            return;
        }
        for (Table table : statement.tables()) {
            if (table instanceof TemporaryTable) {
                throw new UnsupportedOperationException("Subquery in FROM is not supported for elasticsearch yet.");
            }
        }
    }

    private void validateColumns(List<Column> columns) {
        for (Column column : columns) {
            if (!column.isFunction()) {
                if (column.name() == null) {
                    throw new UnsupportedOperationException("Unsupported SELECT column: " + column);
                }
                continue;
            }
            Function function = column.function();
            if (AggregationUtil.isAggregate(function) || function instanceof C_KeyValue) {
                continue;
            }
            throw new UnsupportedOperationException("Function " + function.getClass().getSimpleName()
                    + " is not supported for elasticsearch queries yet.");
        }
    }

    private List<AggregationBuilder> statsAggregation(List<Column> columns, boolean grouped) {
        if (ListUtil.isNullOrEmpty(columns)) {
            return Collections.emptyList();
        }
        long aggregateCount = columns.stream().filter(AggregationUtil::isAggregateColumn).count();
        List<AggregationBuilder> builders = new LinkedList<>();
        for (int idx = 0, len = columns.size(); idx < len; idx++) {
            Column col = columns.get(idx);
            if (!AggregationUtil.isAggregateColumn(col)) {
                continue;
            }
            Function func = col.function();
            String name = AggregationUtil.outputName(col, idx);
            if (func instanceof Avg) {
                AvgAggregationBuilder avg = AggregationBuilders.avg(name);
                avg.field(AggregationUtil.fieldName(func));
                builders.add(avg);
            } else if (func instanceof Count) {
                Count count = (Count) func;
                if (count.matchAll()) {
                    if (grouped) {
                        // count(*) per group comes from the composite bucket doc_count.
                        continue;
                    }
                    if (aggregateCount > 1) {
                        // count(*) next to other metrics: a match_all filter
                        // aggregation exposes doc_count. A single count(*) is
                        // answered directly from hits.totalHits.
                        builders.add(AggregationBuilders.filter(name, QueryBuilders.matchAllQuery()));
                    }
                    continue;
                }
                if (count.distinct()) {
                    CardinalityAggregationBuilder cardinality = AggregationBuilders.cardinality(name);
                    cardinality.field(AggregationUtil.fieldName(func));
                    builders.add(cardinality);
                } else {
                    ValueCountAggregationBuilder valueCount = AggregationBuilders.count(name);
                    valueCount.field(AggregationUtil.fieldName(func));
                    builders.add(valueCount);
                }
            } else if (func instanceof Sum) {
                SumAggregationBuilder sum = AggregationBuilders.sum(name);
                sum.field(AggregationUtil.fieldName(func));
                builders.add(sum);
            } else if (func instanceof Min) {
                MinAggregationBuilder min = AggregationBuilders.min(name);
                min.field(AggregationUtil.fieldName(func));
                builders.add(min);
            } else if (func instanceof Max) {
                MaxAggregationBuilder max = AggregationBuilders.max(name);
                max.field(AggregationUtil.fieldName(func));
                builders.add(max);
            }
        }
        return builders;
    }

    private String[] sourceFields(List<Column> columns) {
        if (ListUtil.isNullOrEmpty(columns)) {
            return null;
        }
        List<String> fields = new ArrayList<>();
        for (Column column : columns) {
            if (!column.isFunction()) {
                fields.add(column.name());
            } else if (column.function() instanceof C_KeyValue) {
                fields.add(((C_KeyValue) column.function()).column().name());
            }
        }
        return fields.isEmpty() ? null : fields.toArray(new String[0]);
    }

    private boolean isAggregateColumn(Column column) {
        return AggregationUtil.isAggregateColumn(column);
    }

    private boolean isAggregate(Function function) {
        return AggregationUtil.isAggregate(function);
    }

    protected void sort(SearchSourceBuilder builder, OrderBy orderBy, List<Column> columns) {
        for (Column col : orderBy.columns()) {
            String field;
            Column ordinal = OrderByUtil.resolve(col.name(), columns);
            if (ordinal != null) {
                if (ordinal.isFunction()) {
                    throw new UnsupportedOperationException("ORDER BY an aggregate ordinal is not supported yet.");
                }
                field = ordinal.name();
            } else {
                field = resolveOrderField(col.name(), columns);
            }
            if (col.order() == Order.ASC) {
                builder.sort(field, SortOrder.ASC);
            } else {
                builder.sort(field, SortOrder.DESC);
            }
        }
    }

    private String resolveOrderField(String name, List<Column> columns) {
        for (Column column : columns) {
            if (column.alias() != null && column.alias().equalsIgnoreCase(name)) {
                if (column.isFunction()) {
                    throw new UnsupportedOperationException("ORDER BY an aggregate alias is not supported yet: " + name);
                }
                return column.name();
            }
        }
        return name;
    }

    private List<QueryBuilder> resolveQueryCondition(Expr expr) {
        if (expr == null) {
            return Collections.emptyList();
        }
        if (expr instanceof GroupedExpr) {
            return resolveQueryCondition(((GroupedExpr) expr).expression());
        }
        if (expr instanceof NotExpr) {
            List<QueryBuilder> inner = resolveQueryCondition(((NotExpr) expr).expression());
            if (ListUtil.isNullOrEmpty(inner)) {
                throw new UnsupportedOperationException("NOT condition could not be translated to elasticsearch.");
            }
            BoolQueryBuilder innerBool = QueryBuilders.boolQuery();
            innerBool.must().addAll(inner);
            return Collections.singletonList(QueryBuilders.boolQuery().mustNot(innerBool));
        }
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
            Value begin = expr.begin();
            Value end = expr.end();
            if (begin == null || end == null || begin.isNull() || end.isNull()) {
                return Collections.singletonList(matchNone());
            }
            final RangeQueryBuilder rqb = QueryBuilders.rangeQuery(((Column) expr.left()).name());
            rqb.from(begin.value());
            rqb.to(end.value());
            return Collections.singletonList(rqb);
        } else if (operator instanceof FunctionOperator) {
            if (operator == FunctionOperator.C_EXISTS) {
                C_Exists c_exists = (C_Exists) expr;
                return Collections.singletonList(QueryBuilders.existsQuery(c_exists.executable().toString()));
            } else if (operator == FunctionOperator.C_SCRIPT) {
                C_Script c_script = (C_Script) expr;
                return Collections.singletonList(QueryBuilders.scriptQuery(new Script(c_script.executable().toString())));
            }
            throw new UnsupportedOperationException("Unsupported CQL function operator: " + operator);
        }
        throw new UnsupportedOperationException("Unsupported operator: " + operator);
    }


    private List<QueryBuilder> resolveRelOperator(RelOperator operator, Expr expr) {
        String name = ((Column) expr.left()).name();
        if (operator == RelOperator.IS) {
            Value value = (Value) expr.right();
            if (value.isNull()) {
                return Collections.singletonList(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery(name)));
            }
            if (value.notNull()) {
                return Collections.singletonList(QueryBuilders.existsQuery(name));
            }
            return Collections.singletonList(matchNone());
        }
        if (operator == RelOperator.IN || operator == RelOperator.NOT_IN) {
            Range range = (Range) expr.right();
            List<Value> values = range.values().stream()
                    .filter(value -> value != null && !value.isNull())
                    .collect(Collectors.toList());
            if (values.isEmpty()) {
                return Collections.singletonList(matchNone());
            }
            String field = termsField(name, values.get(0));
            List<Object> terms = values.stream().map(Value::value).collect(Collectors.toList());
            if (operator == RelOperator.IN) {
                return Collections.singletonList(QueryBuilders.termsQuery(field, terms));
            }
            // Standard SQL: NOT IN excludes documents where the field is NULL/missing.
            return Collections.singletonList(QueryBuilders.boolQuery()
                    .must(QueryBuilders.existsQuery(name))
                    .mustNot(QueryBuilders.termsQuery(field, terms)));
        }

        if (expr.right() instanceof Column) {
            throw new UnsupportedOperationException("Field-to-field comparison is not supported for elasticsearch yet.");
        }
        Value cValue = (Value) expr.right();
        if (cValue == null || cValue.isNull()) {
            // Standard SQL: comparisons with NULL are UNKNOWN, so they match no rows.
            return Collections.singletonList(matchNone());
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
                return Collections.singletonList(QueryBuilders.termQuery(termField(name, cValue), cValue.value()));
            case NOT_EQUAL:
                return Collections.singletonList(QueryBuilders.boolQuery()
                        .must(QueryBuilders.existsQuery(name))
                        .mustNot(QueryBuilders.termQuery(termField(name, cValue), cValue.value())));
            case LIKE:
                return Collections.singletonList(QueryBuilders.wildcardQuery(keywordField(name),
                        toWildcard(String.valueOf(cValue.value()), expr.escape())));
            case NOT_LIKE:
                return Collections.singletonList(QueryBuilders.boolQuery()
                        .must(QueryBuilders.existsQuery(name))
                        .mustNot(QueryBuilders.wildcardQuery(keywordField(name),
                                toWildcard(String.valueOf(cValue.value()), expr.escape()))));
            default:
                throw new UnsupportedOperationException("Unsupported comparison operator: " + operator);
        }
    }

    /**
     * Translates SQL LIKE wildcards to Elasticsearch wildcard syntax. Literal
     * {@code *}, {@code ?} and {@code \} characters are escaped.
     */
    private String toWildcard(String pattern, Character escape) {
        StringBuilder sb = new StringBuilder(pattern.length());
        for (int idx = 0; idx < pattern.length(); idx++) {
            char c = pattern.charAt(idx);
            if (escape != null && c == escape && idx + 1 < pattern.length()) {
                appendEscaped(sb, pattern.charAt(++idx));
            } else if (c == '%') {
                sb.append('*');
            } else if (c == '_') {
                sb.append('?');
            } else if (c == '\\' && idx + 1 < pattern.length()) {
                appendEscaped(sb, pattern.charAt(++idx));
            } else {
                appendEscaped(sb, c);
            }
        }
        return sb.toString();
    }

    private void appendEscaped(StringBuilder sb, char c) {
        if (c == '\\' || c == '*' || c == '?') {
            sb.append('\\');
        }
        sb.append(c);
    }

    private String termField(String name, Value value) {
        return isKeywordType(value) ? keywordField(name) : name;
    }

    private String termsField(String name, Value value) {
        return isKeywordType(value) ? keywordField(name) : name;
    }

    private boolean isKeywordType(Value value) {
        return value.type() == Value.Type.STRING || value.type() == Value.Type.PARAM;
    }

    private String keywordField(String name) {
        return name + ".keyword";
    }

    private QueryBuilder matchNone() {
        return QueryBuilders.boolQuery().mustNot(QueryBuilders.matchAllQuery());
    }
}
