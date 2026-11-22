/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.resolve.rv;

import fun.langel.cql.node.Column;
import fun.langel.cql.resolve.RvResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/12 14:06
 **/
public class MongoRvResolver implements RvResolver<List<Bson>> {
    @Override
    public ReturnValue<?> resolve(List<Bson> from, List<Column> columns) {
        Rows rows = new Rows();
        if (from == null || from.isEmpty()) {
            return rows;
        }
        for (Bson bson : from) {
            Row row = resolve((Document) bson);
            RowProjector.apply(row, columns);
            rows.add(row);
        }
        return rows;
    }


}