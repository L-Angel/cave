package fun.langel.cql.node;

import fun.langel.cql.node.operator.Operator;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/20 19:35
 **/
public interface Expr extends Node {


    default Node left() {
        return Column.of("");
    }

    default Operator operator() {
        return null;
    }

    default Node right() {
        return new Value(null, false, false);
    }

    default Value begin() {
        return new Value(null, false, false);
    }

    default Value end() {
        return new Value(null, false, false);
    }
}
