package fun.langel.cql.node;

import fun.langel.cql.node.operator.BetweenOperator;
import fun.langel.cql.node.operator.Operator;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/26 21:57
 **/
public class ExprImpl implements Expr {
    private Node left;

    private Node right;

    private Operator operator;

    private Value begin;

    private Value end;

    public ExprImpl(final Node left, final Operator operator, final Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public ExprImpl(final Column column, final Value begin, final Value end) {
        this.operator = BetweenOperator.BETWEEN;
        this.begin = begin;
        this.end = end;
        this.left = column;
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

    @Override
    public Value begin() {
        return this.begin;
    }

    @Override
    public Value end() {
        return this.end;
    }
}
