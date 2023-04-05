/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
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
