/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.capability;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Expr;
import fun.langel.cql.node.GroupedExpr;
import fun.langel.cql.node.NotExpr;
import fun.langel.cql.node.func.C_KeyValue;
import fun.langel.cql.node.operator.FunctionOperator;
import fun.langel.cql.node.operator.LogicalOperator;
import fun.langel.cql.node.operator.Operator;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.AggregationUtil;

import java.util.Collections;
import java.util.List;

/**
 * Validates a parsed statement against a datasource capability set. Unsupported
 * features fail with one consistent exception before any request is built.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public final class CapabilityValidator {

    private CapabilityValidator() {
    }

    public static void validate(final SelectStatement statement, final DialectCapabilities capabilities) {
        if (statement == null) {
            return;
        }
        List<Column> columns = statement.columns() == null ? Collections.emptyList() : statement.columns();
        if (statement.distinct() && !capabilities.distinct()) {
            throw unsupported(capabilities, "DISTINCT");
        }
        if (statement.groupBy() != null && !capabilities.groupBy()) {
            throw unsupported(capabilities, "GROUP BY");
        }
        if (statement.having() != null && !capabilities.having()) {
            throw unsupported(capabilities, "HAVING");
        }
        boolean hasAggregate = columns.stream().anyMatch(AggregationUtil::isAggregateColumn);
        if (hasAggregate && !capabilities.aggregations()) {
            throw unsupported(capabilities, "Aggregate functions");
        }
        boolean hasNonAggregate = columns.stream()
                .anyMatch(column -> !AggregationUtil.isAggregateColumn(column));
        if (hasAggregate && statement.groupBy() == null && hasNonAggregate) {
            throw new UnsupportedOperationException("Mixing aggregate and non-aggregate columns requires GROUP BY.");
        }
        if (!capabilities.keyValue()) {
            for (Column column : columns) {
                if (column.isFunction() && column.function() instanceof C_KeyValue) {
                    throw unsupported(capabilities, "c_keyvalue");
                }
            }
        }
        validateFunctions(statement.where(), capabilities);
        validateFunctions(statement.having(), capabilities);
    }

    private static void validateFunctions(final Expr expr, final DialectCapabilities capabilities) {
        if (expr == null) {
            return;
        }
        if (expr instanceof GroupedExpr) {
            validateFunctions(((GroupedExpr) expr).expression(), capabilities);
            return;
        }
        if (expr instanceof NotExpr) {
            validateFunctions(((NotExpr) expr).expression(), capabilities);
            return;
        }
        Operator operator = expr.operator();
        if (operator == LogicalOperator.AND || operator == LogicalOperator.OR) {
            validateFunctions((Expr) expr.left(), capabilities);
            validateFunctions((Expr) expr.right(), capabilities);
            return;
        }
        if (operator == FunctionOperator.C_EXISTS && !capabilities.cExists()) {
            throw unsupported(capabilities, "c_exists");
        }
        if (operator == FunctionOperator.C_SCRIPT && !capabilities.cScript()) {
            throw unsupported(capabilities, "c_script");
        }
    }

    private static UnsupportedOperationException unsupported(final DialectCapabilities capabilities,
                                                             final String feature) {
        return new UnsupportedOperationException(feature + " is not supported for "
                + capabilities.dialect() + " yet.");
    }
}
