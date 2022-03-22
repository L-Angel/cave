package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 04:38
 **/
public class Between implements Node {

    private final Column column;

    private final String from;

    private final String end;

    public Between(Column column, String from, String end) {
        this.column = column;
        this.from = from;
        this.end = end;
    }

    public Column column() {
        return this.column;
    }

    public String from() {
        return from;
    }

    public String end() {
        return end;
    }

    @Override
    public NodeKind kind() {
        return NodeKind.BETWEEN;
    }
}
