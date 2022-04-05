package fun.langel.cql.rv;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/28 11:13
 **/
public class Long implements ReturnValue<java.lang.Long> {

    private long value;

    public Long(final long value) {
        this.value = value;
    }

    @Override
    public java.lang.Long getValue() {
        return this.value;
    }
}
