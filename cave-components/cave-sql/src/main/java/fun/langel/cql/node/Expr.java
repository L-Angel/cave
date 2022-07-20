package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/20 19:35
 **/
public class Expr implements Node {

    private Node left;

    private Node right;

    private Operator operator;

    public Expr(final Node left, final Operator operator, final Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Node left() {
        return this.left;
    }

    public Operator operator() {
        return this.operator;
    }

    public Node right() {
        return this.right;
    }
}
