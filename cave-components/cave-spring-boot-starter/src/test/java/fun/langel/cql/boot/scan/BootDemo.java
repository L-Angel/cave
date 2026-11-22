/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.boot.scan;

import fun.langel.cql.annotation.Cave;
import fun.langel.cql.annotation.Select;

/**
 * Test fixture scanned through {@code cave.packages}.
 *
 * @author Rick Jiang
 */
@Cave
public interface BootDemo {

    @Select(sql = "select field1 from demo_index")
    String query();
}
