/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fun.langel.cql.util;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/15 21:50
 **/
public class ArrayUtil {
    private ArrayUtil() {
    }

    public static <T> T[] toArray(List<T> list) {
        if (ListUtil.isNullOrEmpty(list)) {
            return null;
        }
        T[] array = (T[]) new Object[list.size()];
        return list.toArray(array);
    }
}
