package fun.langel.cql.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/16 15:57
 **/
public class Pair<L, R> {

    public static final Pair EMPTY = new EmptyPair(null, null);

    private final L left;

    private final R right;

    private Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public static <LL, RR> Pair<LL, RR> of(final LL left, final RR right) {
        if (left == null && right == null) {
            return empty();
        }
        return new Pair<LL, RR>(left, right);
    }

    public static EmptyPair empty() {
        return new EmptyPair();
    }

    public L left() {
        return this.left;
    }

    public R right() {
        return this.right;
    }

    public Map<L, R> toMap() {
        final Map<L, R> map = new HashMap<>();
        map.put(left(), right());
        return map;
    }

    public static class EmptyPair extends Pair {

        private EmptyPair(Object left, Object right) {
            super(left, right);
        }

        private EmptyPair() {
            this(null, null);
        }

    }

    public static <K, V> Map<K, V> asMap(List<Pair<K, V>> pairs) {
        final Map<K, V> map = new HashMap<>();
        for (Pair<K, V> pair : pairs) {
            map.put(pair.left(), pair.right());
        }
        return map;
    }
}
