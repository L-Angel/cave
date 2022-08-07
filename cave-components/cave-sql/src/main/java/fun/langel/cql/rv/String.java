package fun.langel.cql.rv;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/7 20:37
 **/
public class String implements ReturnValue<java.lang.String> {

    private java.lang.String value;

    private String(final java.lang.String value) {
        this.value = value;
    }

    public static String of(final java.lang.String value) {
        return new String(value);
    }

    @Override
    public java.lang.String getValue() {
        return this.value;
    }
}
