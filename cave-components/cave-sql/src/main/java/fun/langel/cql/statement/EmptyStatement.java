package fun.langel.cql.statement;

import fun.langel.cql.node.Expr;
import fun.langel.cql.node.Table;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/20 19:53
 **/
public class EmptyStatement implements SelectStatement, DeleteStatement {
    @Override
    public Expr where() {
        return null;
    }

    @Override
    public Table table() {
        return null;
    }
}
