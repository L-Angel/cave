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
 * @date 2022/12/15 22:52
 **/
public class Count implements Function {

    private final Column executable;

    private final boolean distinct;

    private Count(Column executable, boolean distinct) {
        this.executable = executable;
        this.distinct = distinct;
    }

    public boolean matchAll() {
        return !this.distinct
                && ("*".equalsIgnoreCase(executable.name()) || "1".equalsIgnoreCase(executable.name()));
    }

    public boolean distinct() {
        return this.distinct;
    }

    @Override
    public Node executable() {
        return this.executable;
    }

    public static Count of(Column column) {
        return new Count(column, false);
    }

    public static Count of(Column column, boolean distinct) {
        return new Count(column, distinct);
    }


}
