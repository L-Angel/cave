/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.util;

import fun.langel.cql.exception.SqlException;
import fun.langel.cql.node.Column;

import java.util.List;

/**
 * Resolves {@code ORDER BY 1} style positional ordinals against the SELECT list.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public final class OrderByUtil {

    private OrderByUtil() {
    }

    public static Column resolve(final String name, final List<Column> selected) {
        if (name == null || !name.matches("[0-9]+")) {
            return null;
        }
        int index = Integer.parseInt(name) - 1;
        if (selected == null || index < 0 || index >= selected.size()) {
            throw new SqlException("ORDER BY ordinal out of range: " + name);
        }
        return selected.get(index);
    }
}
