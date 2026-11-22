/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/15 20:15
 **/
public class GroupBy implements Node {

    private final List<Column> columns = new LinkedList<>();

    private GroupBy(Column... cols) {
        this.columns.addAll(Arrays.asList(cols));
    }

    public static GroupBy of(Column col) {
        return new GroupBy(col);
    }

    public static GroupBy ofEmpty() {
        return new GroupBy();
    }

    public List<Column> columns() {
        return this.columns;
    }

    public void add(Column col) {
        this.columns.add(col);
    }
}
