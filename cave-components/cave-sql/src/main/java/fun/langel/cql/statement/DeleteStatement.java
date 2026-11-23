package fun.langel.cql.statement;

import fun.langel.cql.node.Expr;
import fun.langel.cql.node.Table;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/20 19:50
 **/
public interface DeleteStatement extends Statement {

    default Table table() {
        return null;
    }

    default Expr where() {
        return null;
    }
}
