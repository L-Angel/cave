package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/1 17:15
 **/
public class Limit implements Node {

    private final int fetch;

    private final int offset;

    private Limit(final int offset, final int fetch) {
        this.offset = offset;
        this.fetch = fetch;
    }

    public static Limit of(final int from, final int fetch) {
        return new Limit(from, fetch);
    }

    public int fetch() {
        return this.fetch;
    }

    public int offset() {
        return this.offset;
    }
}
