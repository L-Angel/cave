package fun.langel.cql.statement;

import fun.langel.cql.node.Expr;
import fun.langel.cql.node.Table;
import fun.langel.cql.statement.Statement;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/31 20:44
 **/
public interface SelectStatement extends Statement {
    Expr where();

    Table table();
}
