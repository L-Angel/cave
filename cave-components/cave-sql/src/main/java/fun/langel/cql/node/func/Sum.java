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
 * @date 2023/1/8 03:28
 **/
public class Sum implements Function {
    private final Column executable;

    private Sum(final Column executable) {
        this.executable = executable;
    }

    @Override
    public Node executable() {
        return this.executable;
    }

    public static Sum of(Column col) {
        return new Sum(col);
    }
}
