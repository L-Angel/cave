/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.spi;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2023/4/5 22:49
 **/
public class Loader {

    private static final Map<Class<?>, List> cache = new ConcurrentHashMap<>();

    private Loader() {
    }

    public static <T> List<T> load(Class<T> klass) {
        if (cache.containsKey(klass)) {
            return (List<T>) cache.get(klass);
        }
        synchronized (klass) {
            ServiceLoader<?> loader = ServiceLoader.load(klass, classLoader());
            Iterator<?> it = loader.iterator();
            while (it.hasNext()) {
                cache.computeIfAbsent(klass, (r) -> new LinkedList()).add(it.next());
            }
        }
        return cache.get(klass);
    }


    public static <T> T loadSingle(Class<T> klass) {
        List<T> v = load(klass);
        if (v == null || v.isEmpty()) {
            return null;
        }
        return v.get(0);
    }

    public static ClassLoader classLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = Loader.class.getClassLoader();
        }
        if (cl == null) {
            cl = ClassLoader.getSystemClassLoader();
        }
        return cl;
    }
}
