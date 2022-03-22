package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 12:05
 **/
public class BinaryOpNode implements Node {

    private final NodeKind kind;

    private final Node left;

    private final Node right;

    public BinaryOpNode(NodeKind kind, Node left, Node right) {
        this.kind = kind;
        this.left = left;
        this.right = right;
    }

    public Node left() {
        return this.left;
    }

    public Node right() {
        return this.right;
    }

    @Override
    public NodeKind kind() {
        return this.kind;
    }
}
