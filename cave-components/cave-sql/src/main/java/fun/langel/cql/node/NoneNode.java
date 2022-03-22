package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 15:33
 **/
public class NoneNode implements Node {

    public static NoneNode EMPTY = new NoneNode();

    private NoneNode() {

    }

    @Override
    public NodeKind kind() {
        return NodeKind.NONE;
    }
}
