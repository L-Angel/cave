package fun.langel.cql.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/1 17:37
 **/
public class ListUtil {

    private ListUtil() {
    }

    public static boolean isNullOrEmpty(final List v) {
        return v == null || v.isEmpty();
    }

    public static String[] toStringArray(final List<String> v) {
        if (v == null || v.isEmpty()) {
            return new String[]{};
        }
        String[] arr = new String[v.size()];
        return v.toArray(arr);
    }

    public static <E> LinkedList<E> toLinkedList(Collection<E> collection) {
        return new LinkedList<>(collection);
    }
}
