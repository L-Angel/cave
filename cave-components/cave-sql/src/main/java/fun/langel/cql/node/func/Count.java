/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
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

    private Count(Column executable) {
        this.executable = executable;
    }

    public boolean matchAll() {
        return "*".equalsIgnoreCase(executable.name());
    }

    @Override
    public Node executable() {
        return this.executable;
    }

    public static Count of(Column column) {
        return new Count(column);
    }


}
