/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
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
