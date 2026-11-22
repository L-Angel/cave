/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
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
