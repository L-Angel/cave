package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/26 14:40
 **/
public class Table implements Node {

    private final String name;

    private final String alias;

    private Table(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public static Table of(final String name, final String alias) {
        return new Table(name, alias);
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }
}
