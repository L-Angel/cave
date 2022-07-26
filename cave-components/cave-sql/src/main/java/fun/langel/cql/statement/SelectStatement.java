package fun.langel.cql.statement;

import fun.langel.cql.node.Expr;
import fun.langel.cql.node.Table;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/20 19:27
 **/
public class SelectStatement implements Statement {

    private Expr condition;

    private Table table;
}
