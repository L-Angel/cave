package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 04:37
 **/
public class Column implements Node {

    private SortRule sort;

    private NodeKind kind = NodeKind.FIELD;

    private String name;

    private String alias;


    public Column(NodeKind kind, String name, String alias, SortRule sort) {
        this.kind = kind;
        this.name = name;
        this.alias = alias;
        if (sort == null) {
            this.sort = SortRule.NONE;
        }
    }

    public Column(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public static Column of(final String name) {
        return new Column(NodeKind.FIELD, name, name, null);
    }


    @Override
    public NodeKind kind() {
        return this.kind;
    }

    public static enum SortRule {
        NONE,
        ASC,
        DESC;
    }
}
