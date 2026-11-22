/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.node;

import fun.langel.cql.node.operator.Operator;

/**
 * Marks an explicitly parenthesized expression so AND/OR precedence rewriting
 * does not flatten across the grouping boundary.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class GroupedExpr implements Expr {

    private final Expr delegate;

    public GroupedExpr(final Expr delegate) {
        this.delegate = delegate;
    }

    public Expr expression() {
        return this.delegate;
    }

    @Override
    public Node left() {
        return this.delegate.left();
    }

    @Override
    public Operator operator() {
        return this.delegate.operator();
    }

    @Override
    public Node right() {
        return this.delegate.right();
    }

    @Override
    public Value begin() {
        return this.delegate.begin();
    }

    @Override
    public Value end() {
        return this.delegate.end();
    }
}
