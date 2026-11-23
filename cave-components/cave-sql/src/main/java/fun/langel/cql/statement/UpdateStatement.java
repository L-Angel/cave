package fun.langel.cql.statement;

import fun.langel.cql.node.Assignment;
import fun.langel.cql.node.Expr;
import fun.langel.cql.node.Table;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public interface UpdateStatement extends Statement {

    Table table();

    List<Assignment> assignments();

    Expr where();
}
