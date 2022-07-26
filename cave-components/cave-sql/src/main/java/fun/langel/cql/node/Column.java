package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/26 14:38
 **/
public class Column implements Node {

    private final String name;

    private final String alias;

    public Column(final String name, final String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String name() {
        return this.name;
    }

    public String alias() {
        return this.alias;
    }
}
