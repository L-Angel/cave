package fun.langel.cql.invoke;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:03 下午
 **/
public class Result {

    private Object value;

    public Result() {
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }
}
