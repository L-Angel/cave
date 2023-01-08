package fun.langel.cql.resolve;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Function;
import fun.langel.cql.rv.Number;
import fun.langel.cql.rv.ReturnValue;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/4/26 17:16
 **/
public interface RvResolver<F> {
    ReturnValue<?> resolve(F from, List<Column> columns);

    default ReturnValue<?> resolveObj(Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof String) {
            return fun.langel.cql.rv.String.of((String) v);
        }
        if (int.class.isAssignableFrom(v.getClass()) || (v instanceof Integer)) {
            return Number.of((Integer) v);
        }
        if (long.class.isAssignableFrom(v.getClass()) || (v instanceof Long)) {
            return Number.of((Long) v);
        }
        if (float.class.isAssignableFrom(v.getClass()) || (v instanceof Float)) {
            return Number.of((Float) v);
        }
        if (double.class.isAssignableFrom(v.getClass()) || (v instanceof Double)) {
            return Number.of((Double) v);
        }
        return fun.langel.cql.rv.String.of(String.valueOf(v));
    }
}
