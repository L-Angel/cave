/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.util;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Function;
import fun.langel.cql.node.func.Avg;
import fun.langel.cql.node.func.Count;
import fun.langel.cql.node.func.Max;
import fun.langel.cql.node.func.Min;
import fun.langel.cql.node.func.Sum;

/**
 * Shared aggregation metadata used by every dialect. Keeping default names and
 * field extraction here prevents Elasticsearch and MongoDB output names from
 * drifting apart.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public final class AggregationUtil {

    private AggregationUtil() {
    }

    public static boolean isAggregate(final Function function) {
        return function instanceof Avg
                || function instanceof Count
                || function instanceof Sum
                || function instanceof Min
                || function instanceof Max;
    }

    public static boolean isAggregateColumn(final Column column) {
        return column != null && column.isFunction() && isAggregate(column.function());
    }

    public static String outputName(final Column column, final int index) {
        return StringUtil.withDefault(column.alias(), defaultName(column.function(), index));
    }

    public static String defaultName(final Function function, final int index) {
        if (function instanceof Avg) {
            return "avg_" + index;
        }
        if (function instanceof Count) {
            return "count_" + index;
        }
        if (function instanceof Sum) {
            return "sum_" + index;
        }
        if (function instanceof Min) {
            return "min_" + index;
        }
        if (function instanceof Max) {
            return "max_" + index;
        }
        return "agg_" + index;
    }

    public static String fieldName(final Function function) {
        if (function == null || !(function.executable() instanceof Column)
                || ((Column) function.executable()).name() == null) {
            throw new UnsupportedOperationException("Aggregate function requires a plain column: "
                    + (function == null ? "null" : function.getClass().getSimpleName()));
        }
        return ((Column) function.executable()).name();
    }
}
