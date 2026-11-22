package fun.langel.cql.resolve.rv;

import fun.langel.cql.enums.Order;
import fun.langel.cql.node.Column;
import fun.langel.cql.node.Function;
import fun.langel.cql.node.Limit;
import fun.langel.cql.node.OrderBy;
import fun.langel.cql.node.func.Avg;
import fun.langel.cql.node.func.C_KeyValue;
import fun.langel.cql.node.func.Count;
import fun.langel.cql.node.func.Max;
import fun.langel.cql.node.func.Min;
import fun.langel.cql.node.func.Sum;
import fun.langel.cql.resolve.RvResolver;
import fun.langel.cql.resolve.dialect.ElasticSearchQDLDialectResolver;
import fun.langel.cql.rv.Number;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.AggregationUtil;
import fun.langel.cql.util.OrderByUtil;
import fun.langel.cql.util.Pair;
import fun.langel.cql.util.StringUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/4 19:28
 **/
public class ElasticSearchRvResolver implements RvResolver<SearchResponse> {

    /**
     * Resolves a response for GROUP BY / HAVING queries. Group buckets are
     * flattened into rows; HAVING, ORDER BY and LIMIT are applied in the
     * middleware so they see the complete bucket set.
     */
    public ReturnValue<?> resolve(SearchResponse from, SelectStatement statement) {
        if (statement.groupBy() != null) {
            return resolveGrouped(from, statement);
        }
        return resolve(from, statement.columns());
    }

    private ReturnValue<?> resolveGrouped(SearchResponse from, SelectStatement statement) {
        if (from == null || from.getAggregations() == null) {
            return new Rows();
        }
        Aggregation aggregation = from.getAggregations()
                .get(ElasticSearchQDLDialectResolver.GROUP_AGGREGATION);
        if (!(aggregation instanceof CompositeAggregation)) {
            return new Rows();
        }
        List<GroupBucket> buckets = new ArrayList<>();
        for (CompositeAggregation.Bucket bucket : ((CompositeAggregation) aggregation).getBuckets()) {
            buckets.add(new GroupBucket(bucket.getKey(), bucket.getDocCount(), bucket.getAggregations()));
        }
        return resolveGroupBuckets(buckets, statement);
    }

    /**
     * Package-visible seam so bucket mapping, HAVING, ORDER BY and LIMIT can be
     * tested without constructing an Elasticsearch SearchResponse.
     */
    Rows resolveGroupBuckets(List<GroupBucket> buckets, SelectStatement statement) {
        final List<Column> selected = statement.columns() == null
                ? Collections.emptyList() : statement.columns();
        final List<Column> groupColumns = statement.groupBy().columns();
        final Map<String, Integer> groupIndex = new HashMap<>();
        for (int idx = 0; idx < groupColumns.size(); idx++) {
            groupIndex.put(resolveGroupSource(groupColumns.get(idx).name(), selected), idx);
        }

        // Resolve output names / key indexes once instead of per bucket.
        final int size = selected.size();
        final boolean[] aggregate = new boolean[size];
        final boolean[] keyValue = new boolean[size];
        final String[] outputNames = new String[size];
        final String[] keyValueSources = new String[size];
        final int[] groupKeyIndexes = new int[size];
        for (int idx = 0; idx < size; idx++) {
            Column column = selected.get(idx);
            if (isAggregateColumn(column)) {
                aggregate[idx] = true;
                outputNames[idx] = AggregationUtil.outputName(column, idx);
            } else if (column.isFunction() && column.function() instanceof C_KeyValue) {
                keyValue[idx] = true;
                keyValueSources[idx] = ((C_KeyValue) column.function()).column().name();
                groupKeyIndexes[idx] = requireGroupKey(groupIndex, keyValueSources[idx]);
            } else {
                outputNames[idx] = outputName(column, idx);
                groupKeyIndexes[idx] = requireGroupKey(groupIndex, column.name());
            }
        }

        List<Row> rows = new ArrayList<>();
        for (GroupBucket bucket : buckets) {
            Row row = new Row();
            Aggregations bucketAggregations = bucket.aggregations;
            for (int idx = 0; idx < size; idx++) {
                if (aggregate[idx]) {
                    Column column = selected.get(idx);
                    if (column.function() instanceof Count && ((Count) column.function()).matchAll()) {
                        row.put(outputNames[idx], Number.of(bucket.docCount));
                    } else {
                        Aggregation metric = bucketAggregations == null
                                ? null : bucketAggregations.get(outputNames[idx]);
                        if (metric instanceof NumericMetricsAggregation.SingleValue) {
                            row.put(outputNames[idx],
                                    numeric(((NumericMetricsAggregation.SingleValue) metric).value()));
                        } else {
                            row.put(outputNames[idx], null);
                        }
                    }
                } else if (keyValue[idx]) {
                    row.put(keyValueSources[idx],
                            resolveObj(bucket.key.get("k" + groupKeyIndexes[idx])));
                } else {
                    row.put(outputNames[idx],
                            resolveObj(bucket.key.get("k" + groupKeyIndexes[idx])));
                }
            }
            RowProjector.apply(row, selected);
            if (statement.having() == null || RowPredicateEvaluator.evaluate(row, statement.having())) {
                rows.add(row);
            }
        }
        sortRows(rows, statement.orderBy(), selected);
        applyLimit(rows, statement.limit());
        return new Rows(rows);
    }

    private int requireGroupKey(Map<String, Integer> groupIndex, String column) {
        Integer index = groupIndex.get(column);
        if (index == null) {
            throw new UnsupportedOperationException("Column " + column
                    + " must appear in GROUP BY or be aggregated.");
        }
        return index;
    }

    static class GroupBucket {
        final Map<String, Object> key;
        final long docCount;
        final Aggregations aggregations;

        GroupBucket(Map<String, Object> key, long docCount, Aggregations aggregations) {
            this.key = key;
            this.docCount = docCount;
            this.aggregations = aggregations;
        }
    }

    private String resolveGroupSource(String name, List<Column> columns) {
        for (Column column : columns) {
            if (!column.isFunction() && column.alias() != null && column.alias().equalsIgnoreCase(name)) {
                return column.name();
            }
        }
        return name;
    }

    private String outputName(Column column, int idx) {
        if (AggregationUtil.isAggregateColumn(column)) {
            return AggregationUtil.outputName(column, idx);
        }
        if (column.isFunction() && column.function() instanceof C_KeyValue) {
            C_KeyValue keyValue = (C_KeyValue) column.function();
            return StringUtil.withDefault(column.alias(),
                    keyValue.column().name() + "." + keyValue.key());
        }
        return StringUtil.withDefault(column.alias(), column.name());
    }

    private void sortRows(List<Row> rows, OrderBy orderBy, List<Column> columns) {
        if (orderBy == null || rows.isEmpty()) {
            return;
        }
        // Resolve output field names once instead of on every comparator call.
        final List<String> fields = new ArrayList<>(orderBy.columns().size());
        final List<Boolean> descending = new ArrayList<>(orderBy.columns().size());
        for (Column orderColumn : orderBy.columns()) {
            fields.add(resolveOrderOutput(orderColumn.name(), columns));
            descending.add(orderColumn.order() == Order.DESC);
        }
        rows.sort((left, right) -> {
            for (int idx = 0; idx < fields.size(); idx++) {
                int compared = RowPredicateEvaluator.compare(
                        rowValue(left, fields.get(idx)), rowValue(right, fields.get(idx)));
                if (compared != 0) {
                    return descending.get(idx) ? -compared : compared;
                }
            }
            return 0;
        });
    }

    private String resolveOrderOutput(String name, List<Column> columns) {
        Column ordinal = OrderByUtil.resolve(name, columns);
        if (ordinal != null) {
            return outputName(ordinal, columns.indexOf(ordinal));
        }
        for (int idx = 0; idx < columns.size(); idx++) {
            Column column = columns.get(idx);
            if ((column.alias() != null && column.alias().equalsIgnoreCase(name))
                    || (column.name() != null && column.name().equalsIgnoreCase(name))) {
                return outputName(column, idx);
            }
        }
        return name;
    }

    private Object rowValue(Row row, String field) {
        ReturnValue<?> value = row.getValue().get(field);
        return value == null ? null : value.getValue();
    }

    private void applyLimit(List<Row> rows, Limit limit) {
        if (limit == null) {
            return;
        }
        int offset = Math.max(0, limit.offset());
        if (limit.fetch() <= 0 || offset >= rows.size()) {
            rows.clear();
            return;
        }
        int end = Math.min(rows.size(), offset + limit.fetch());
        List<Row> limited = new ArrayList<>(rows.subList(offset, end));
        rows.clear();
        rows.addAll(limited);
    }

    @Override
    public ReturnValue<?> resolve(SearchResponse from,
                                  List<Column> columns) {
        if (from == null || from.getHits() == null) {
            return null;
        }
        final List<Column> selected = columns == null ? Collections.emptyList() : columns;
        SearchHits hits = from.getHits();
        if (isSingleCountAll(selected)) {
            return Number.of(hits.totalHits);
        }

        Map<String, Object> aggMap = Pair.asMap(aggregationMap(from.getAggregations()));
        if (!aggMap.isEmpty()) {
            if (!selected.isEmpty() && selected.stream().allMatch(this::isAggregateColumn)) {
                if (aggMap.size() == 1 && selected.size() == 1) {
                    return numeric(aggMap.values().iterator().next());
                }
                return resolve(aggMap, Collections.emptyList());
            }
            throw new UnsupportedOperationException("Aggregate results cannot be combined with document rows "
                    + "without GROUP BY.");
        }

        Rows rows = new Rows();
        SearchHit[] hitArray = hits.getHits();
        if (hitArray == null || hitArray.length == 0) {
            return rows;
        }
        for (SearchHit hit : hitArray) {
            rows.add(resolve(hit.getSourceAsMap(), selected));
        }
        return rows;
    }

    private boolean isSingleCountAll(List<Column> columns) {
        return columns.size() == 1
                && columns.get(0).isFunction()
                && columns.get(0).function() instanceof Count
                && ((Count) columns.get(0).function()).matchAll();
    }

    private boolean isAggregateColumn(Column column) {
        return AggregationUtil.isAggregateColumn(column);
    }

    private boolean isAggregate(Function function) {
        return AggregationUtil.isAggregate(function);
    }

    private ReturnValue<?> numeric(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Double || value instanceof Float) {
            return Number.of(((java.lang.Number) value).doubleValue());
        }
        if (value instanceof java.lang.Number) {
            return Number.of(((java.lang.Number) value).longValue());
        }
        return null;
    }

    private List<Pair<String, Object>> aggregationMap(Aggregations aggregations) {
        final List<Pair<String, Object>> pairs = new LinkedList<>();
        if (aggregations == null) {
            return pairs;
        }
        for (Aggregation aggregation : aggregations) {
            if (aggregation instanceof Filter) {
                pairs.add(Pair.of(aggregation.getName(), ((Filter) aggregation).getDocCount()));
            } else if (aggregation instanceof NumericMetricsAggregation.SingleValue) {
                pairs.add(Pair.of(aggregation.getName(), ((NumericMetricsAggregation.SingleValue) aggregation).value()));
            }
        }
        return pairs;
    }

    Row resolve(Map<String, Object> source, List<Column> columns) {
        Row row = new Row();
        if (source != null) {
            for (Map.Entry<String, Object> entry : source.entrySet()) {
                row.put(entry.getKey(), resolveObj(entry.getValue()));
            }
        }
        RowProjector.apply(row, columns);
        return row;
    }
}
