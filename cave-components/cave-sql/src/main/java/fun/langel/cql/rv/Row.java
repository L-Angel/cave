package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;
import fun.langel.cql.node.Value;
import fun.langel.cql.reflect.Field;
import fun.langel.cql.reflect.Klass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/22 8:35 下午
 **/
public class Row implements ReturnValue<Map<java.lang.String, ReturnValue<?>>> {

    private final Map<java.lang.String, ReturnValue<?>> columns = new HashMap<>();

    public Row() {
        this(null);
    }

    public Row(final Map<java.lang.String, ReturnValue<?>> cols) {
        if (cols == null || cols.isEmpty()) {
            return;
        }
        this.columns.putAll(cols);
    }

    public void put(final java.lang.String k, ReturnValue<?> v) {
        this.columns.put(k, v);
    }

    @Override
    public Map<java.lang.String, ReturnValue<?>> getValue() {
        return this.columns;
    }

    @Override
    public Object mapTo(Class<?> klass) throws MappingException {

        Klass cKls = Klass.of(klass);

        Object o = null;
        try {
            o = cKls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new MappingException(e.getMessage(), e);
        }
        for (Field f : cKls.fields()) {
            ReturnValue<?> v = columns.get(f.getName());
            if (v == null) {
                continue;
            }
            try {
                f.setValue(o, v.mapTo(f.getKlass()));
            } catch (IllegalAccessException e) {
                throw new MappingException(e.getMessage(), e);
            }
        }
        return o;

    }
}
