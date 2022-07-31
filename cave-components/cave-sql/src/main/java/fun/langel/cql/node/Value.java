package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/28 21:29
 **/
public class Value implements Terminal, Node, Expr {

    private String value;

    private boolean isNull;

    private boolean notNull;

    public Value(final String value, boolean isNull, boolean notNull) {
        this.value = value;
        this.isNull = isNull;
        this.notNull = notNull;
    }

    public boolean isNull() {
        return this.isNull;
    }

    public boolean notNull() {
        return this.notNull;
    }


    public String value() {
        return this.value;
    }
}
