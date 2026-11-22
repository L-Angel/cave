/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.resolve.rv;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Expr;
import fun.langel.cql.node.GroupedExpr;
import fun.langel.cql.node.NotExpr;
import fun.langel.cql.node.Range;
import fun.langel.cql.node.Value;
import fun.langel.cql.node.func.C_Exists;
import fun.langel.cql.node.operator.BetweenOperator;
import fun.langel.cql.node.operator.FunctionOperator;
import fun.langel.cql.node.operator.LogicalOperator;
import fun.langel.cql.node.operator.Operator;
import fun.langel.cql.node.operator.RelOperator;
import fun.langel.cql.rv.Number;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Evaluates HAVING expressions against aggregation result rows. This keeps
 * HAVING semantics in the middleware instead of relying on Elasticsearch
 * bucket_selector scripts.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public final class RowPredicateEvaluator {

    private RowPredicateEvaluator() {
    }

    public static boolean evaluate(final Row row, final Expr expr) {
        if (expr == null) {
            return true;
        }
        if (expr instanceof GroupedExpr) {
            return evaluate(row, ((GroupedExpr) expr).expression());
        }
        if (expr instanceof NotExpr) {
            return !evaluate(row, ((NotExpr) expr).expression());
        }
        Operator operator = expr.operator();
        if (operator == LogicalOperator.AND) {
            return evaluate(row, (Expr) expr.left()) && evaluate(row, (Expr) expr.right());
        }
        if (operator == LogicalOperator.OR) {
            return evaluate(row, (Expr) expr.left()) || evaluate(row, (Expr) expr.right());
        }
        if (operator == FunctionOperator.C_EXISTS) {
            C_Exists exists = (C_Exists) expr;
            String name = exists.executable() == null ? null : exists.executable().toString();
            return name != null && row.getValue().get(name) != null;
        }
        if (operator == FunctionOperator.C_SCRIPT) {
            throw new UnsupportedOperationException("c_script is not supported in HAVING.");
        }
        if (operator instanceof BetweenOperator) {
            Object left = value(row, (Column) expr.left());
            Object begin = expr.begin().value();
            Object end = expr.end().value();
            return compare(left, begin) >= 0 && compare(left, end) <= 0;
        }
        if (operator instanceof RelOperator) {
            return evaluateRelational(row, (RelOperator) operator, expr);
        }
        throw new UnsupportedOperationException("Unsupported HAVING operator: " + operator);
    }

    private static boolean evaluateRelational(Row row, RelOperator operator, Expr expr) {
        Object left = value(row, (Column) expr.left());
        if (operator == RelOperator.IS) {
            Value value = (Value) expr.right();
            if (value.isNull()) {
                return left == null;
            }
            return left != null;
        }
        if (operator == RelOperator.IN || operator == RelOperator.NOT_IN) {
            Range range = (Range) expr.right();
            boolean matched = false;
            for (Value candidate : range.values()) {
                if (candidate.isNull()) {
                    continue;
                }
                if (compare(left, candidate.value()) == 0) {
                    matched = true;
                    break;
                }
            }
            return operator == RelOperator.IN ? matched : !matched;
        }
        if (operator == RelOperator.LIKE || operator == RelOperator.NOT_LIKE) {
            throw new UnsupportedOperationException("LIKE is not supported in HAVING yet.");
        }

        Object right;
        if (expr.right() instanceof Column) {
            right = value(row, (Column) expr.right());
        } else {
            Value value = (Value) expr.right();
            if (value.isNull()) {
                return false;
            }
            right = value.value();
        }
        if (left == null || right == null) {
            return false;
        }
        int compared = compare(left, right);
        switch (operator) {
            case EQUAL:
                return compared == 0;
            case NOT_EQUAL:
                return compared != 0;
            case GREATER:
                return compared > 0;
            case GREATER_OR_EQUALS:
                return compared >= 0;
            case LESS:
                return compared < 0;
            case LESS_OR_EQUALS:
                return compared <= 0;
            default:
                throw new UnsupportedOperationException("Unsupported HAVING operator: " + operator);
        }
    }

    private static Object value(Row row, Column column) {
        if (column == null || column.name() == null) {
            return null;
        }
        ReturnValue<?> value = row.getValue().get(column.name());
        return value == null ? null : value.getValue();
    }

    static int compare(Object left, Object right) {
        if (left == null && right == null) {
            return 0;
        }
        if (left == null) {
            return 1;
        }
        if (right == null) {
            return -1;
        }
        BigDecimal leftNumber = decimal(left);
        BigDecimal rightNumber = decimal(right);
        if (leftNumber != null && rightNumber != null) {
            return leftNumber.compareTo(rightNumber);
        }
        if (left instanceof java.lang.Boolean && right instanceof java.lang.Boolean) {
            return java.lang.Boolean.compare((java.lang.Boolean) left, (java.lang.Boolean) right);
        }
        if (left instanceof LocalDateTime && right instanceof LocalDateTime) {
            return ((LocalDateTime) left).compareTo((LocalDateTime) right);
        }
        if (left instanceof Comparable && left.getClass().equals(right.getClass())) {
            return ((Comparable) left).compareTo(right);
        }
        return java.lang.String.valueOf(left).compareTo(java.lang.String.valueOf(right));
    }

    private static BigDecimal decimal(Object value) {
        if (value instanceof Number) {
            BigDecimal decimal = ((Number) value).decimalValue();
            return decimal == null ? BigDecimal.ZERO : decimal;
        }
        if (value instanceof java.lang.Number) {
            return new BigDecimal(value.toString());
        }
        return null;
    }
}
