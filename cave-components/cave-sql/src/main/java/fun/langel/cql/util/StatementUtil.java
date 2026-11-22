/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.util;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Function;
import fun.langel.cql.node.Node;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/21 01:22
 **/
public class StatementUtil {


    private StatementUtil() {
    }

    public static List<Function> selectFunctionCall(final List<Node> nodes) {
        return ListUtil.isNullOrEmpty(nodes) ? Collections.emptyList() : nodes.stream().filter(v -> (v instanceof Function)).map(v -> (Function) v).collect(Collectors.toList());
    }
}
