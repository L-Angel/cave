package fun.langel.cql.reflect;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/10 16:37
 **/
public class Field {


    private final java.lang.reflect.Field actual;

    private Field(final java.lang.reflect.Field f) {
        this.actual = f;
        this.actual.setAccessible(true);
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

}
