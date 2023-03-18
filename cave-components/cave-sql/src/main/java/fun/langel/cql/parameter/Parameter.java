package fun.langel.cql.parameter;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/16 15:29
 **/
public class Parameter {

    private final String name;

    private Object value;

    private final int pos;

    private Class<?> klass;

    public Parameter(final String name,
                     final Object value,
                     final int pos,
                     final Class<?> klass) {
        this.name = name;
        this.value = value;
        this.pos = pos;
        this.klass = klass;
    }

    public Parameter(final String name,
                     final Object value) {
        this(name, value, -1, null);
    }

    public int pos() {
        return this.pos;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getKlass() {
        return this.klass;
    }

    public void setKlass(final Class<?> klass) {
        this.klass = klass;
    }
}
