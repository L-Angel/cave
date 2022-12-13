package fun.langel.cql.node.func;

import fun.langel.cql.node.Function;
import fun.langel.cql.node.Node;
import fun.langel.cql.node.operator.FunctionOperator;
import fun.langel.cql.node.operator.Operator;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/12 21:46
 **/
public class C_Exists implements Function {

    private final Node executable;

    private C_Exists(final Node executable) {
        this.executable = executable;
    }

    @Override
    public Node executable() {
        return this.executable;
    }

    public static C_Exists of(Node executable) {
        return new C_Exists(executable);
    }

    @Override
    public Operator operator() {
        return FunctionOperator.C_EXISTS;
    }
}
