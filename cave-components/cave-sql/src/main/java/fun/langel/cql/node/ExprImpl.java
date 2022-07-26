package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/26 21:57
 **/
public class ExprImpl implements Expr{
    private Node left;

    private Node right;

    private Operator operator;

    public ExprImpl(final Node left, final Operator operator, final Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Node left() {
        return this.left;
    }

    @Override
    public Operator operator() {
        return this.operator;
    }

    @Override
    public Node right() {
        return this.right;
    }
}
