package fun.langel.cql.rv;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/4 11:30 上午
 **/
public class Int implements ReturnValue<Integer> {

    private int value = 0;

    public Int() {
        this(null);
    }

    public Int(final Integer v) {
        if (v == null) {
            return;
        }
        this.value = v;
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
