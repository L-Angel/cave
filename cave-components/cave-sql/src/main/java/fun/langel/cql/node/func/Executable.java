/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.node.func;

import fun.langel.cql.node.Node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/21 01:45
 **/
public interface Executable<FROM,RETURN> extends Node {

    RETURN execute(FROM value);
}
