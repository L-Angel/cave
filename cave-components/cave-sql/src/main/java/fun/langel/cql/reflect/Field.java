package fun.langel.cql.reflect;

import fun.langel.cql.annotation.CField;

import java.util.Arrays;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/10 16:37
 **/
public class Field {


    private List<String> alias;

    private final java.lang.reflect.Field actual;

    private Field(final java.lang.reflect.Field f) {
        this.actual = f;
        this.actual.setAccessible(true);
        CField cf = this.actual().getDeclaredAnnotation(CField.class);
        if (cf != null) {
            this.alias = Arrays.asList(cf.alias());
        }
    }

    public static Field of(final java.lang.reflect.Field f) {
        return new Field(f);
    }

    public java.lang.reflect.Field actual() {
        return this.actual;
    }

    public void setValue(final Object o, final Object value) throws IllegalAccessException {
        actual().set(o, value);
    }

    public String getName() {
        return actual().getName();
    }

    public Class<?> getKlass() {
        return actual().getType();
    }

    public List<String> alias() {
        return this.alias;
    }

}
