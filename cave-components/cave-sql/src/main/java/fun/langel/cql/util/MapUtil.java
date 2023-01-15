/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
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
