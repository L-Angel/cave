/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.resolve.rv;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.func.C_KeyValue;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;
import fun.langel.cql.util.StringUtil;

import java.util.List;

/**
 * Applies storage-independent post-processing to a result row: c_keyvalue
 * projections and SELECT alias renaming. Shared by all non-JDBC dialects so
 * result semantics stay aligned.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public final class RowProjector {

    private RowProjector() {
    }

    public static void apply(final Row row, final List<Column> columns) {
        if (row == null || columns == null || columns.isEmpty()) {
            return;
        }
        // Key/value projection runs before alias renaming, otherwise a source
        // field that is also selected directly could be moved out from under it.
        for (Column column : columns) {
            if (column.isFunction() && column.function() instanceof C_KeyValue) {
                applyKeyValue(row, column, (C_KeyValue) column.function());
            }
        }
        for (Column column : columns) {
            if (!column.isFunction() && column.alias() != null
                    && !column.alias().equals(column.name())) {
                ReturnValue<?> value = row.getValue().remove(column.name());
                if (value != null) {
                    row.put(column.alias(), value);
                }
            }
        }
    }

    private static void applyKeyValue(final Row row, final Column column, final C_KeyValue keyValue) {
        String sourceField = keyValue.column().name();
        ReturnValue<?> raw = row.getValue().get(sourceField);
        if (raw == null) {
            return;
        }
        C_KeyValue.KeyValueExecutable executable = (C_KeyValue.KeyValueExecutable) keyValue.executable();
        String transformed = executable.execute(String.valueOf(raw.getValue()));
        if (transformed == null) {
            return;
        }
        String name = StringUtil.withDefault(column.alias(), sourceField + "." + keyValue.key());
        row.put(name, fun.langel.cql.rv.String.of(transformed));
    }
}
