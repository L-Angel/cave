/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
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
