package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/20 19:35
 **/
public interface Expr extends Node {


    default Node left() {
        return new Terminal();
    }

    default Operator operator() {
        return null;
    }

    default Node right() {
        return new Terminal();
    }
}
