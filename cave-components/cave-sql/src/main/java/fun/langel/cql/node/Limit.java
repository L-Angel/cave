package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 04:42
 **/
public class Limit implements Node {

    private long offset;

    private long fetch;

    public Limit(long offset, long fetch) {
        this.offset = offset;
        this.fetch = fetch;
    }

    public long offset() {
        return offset;
    }

    public long fetch() {
        return fetch;
    }

    @Override
    public NodeKind kind() {
        return NodeKind.LIMIT;
    }
}
