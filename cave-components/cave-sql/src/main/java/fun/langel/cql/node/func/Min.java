/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.node.func;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Function;
import fun.langel.cql.node.Node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class Min implements Function {

    private final Column executable;

    private Min(final Column executable) {
        this.executable = executable;
    }

    @Override
    public Node executable() {
        return this.executable;
    }

    public static Min of(final Column column) {
        return new Min(column);
    }
}
