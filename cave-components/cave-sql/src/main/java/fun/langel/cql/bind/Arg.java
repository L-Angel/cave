package fun.langel.cql.bind;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/8 20:04
 **/
public class Arg {

    private final String name;

    private final String alias;

    private final Object value;

    private final Class<?> klass;

    public Arg(final String name, final String alias,
               final Object value,
               final Class<?> klass) {
        this.name = name;
        this.alias = alias;
        this.value = value;
        this.klass = klass;
    }


    public String name() {
        return this.name;
    }

    public String alias() {
        return this.alias;
    }

    public Object value() {
        return this.value;
    }

    public Class<?> klass() {
        return this.klass;
    }
}
