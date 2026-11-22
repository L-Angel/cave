/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.node;

import fun.langel.cql.node.operator.LogicalOperator;
import fun.langel.cql.node.operator.Operator;

/**
 * Unary NOT expression. Dialect resolvers translate it into a negated query.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class NotExpr implements Expr {

    private final Expr expression;

    public NotExpr(final Expr expression) {
        this.expression = expression;
    }

    public Expr expression() {
        return this.expression;
    }

    @Override
    public Node left() {
        return this.expression;
    }

    @Override
    public Operator operator() {
        return LogicalOperator.NOT;
    }

    @Override
    public Node right() {
        return null;
    }
}
