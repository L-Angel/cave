/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fun.langel.cql.extension;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Expr;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2023/4/5 22:50
 **/
public class DefaultCqlParseExtension implements CqlParseExtension {
    @Override
    public List<Column> afterSelectElements(List<Column> columns) {
        return columns;
    }

    @Override
    public Expr afterWhere(Expr expr) {
        return expr;
    }
}
