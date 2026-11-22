/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2023/1/8 20:45
 **/
public class MapUtil {

    private MapUtil() {
    }

    public static <K, V> Map<K, V> merge(Map<K, V> first, Map<K, V> second) {
        final Map<K, V> map = new HashMap<>();
        if (first != null) {
            map.putAll(first);
        }
        if (second != null) {
            map.putAll(second);
        }
        return map;
    }
}
