package fun.langel.cql.statement.impl;

import fun.langel.cql.node.Expr;
import fun.langel.cql.node.Table;
import fun.langel.cql.statement.DeleteStatement;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/31 20:47
 **/
public class DeleteStatementImpl implements DeleteStatement {

    private Table table;

    private Expr where;

    public void setTable(Table table) {
        this.table = table;
    }

    public void setWhere(Expr where) {
        this.where = where;
    }

    @Override
    public Table table() {
        return this.table;
    }

    @Override
    public Expr where() {
        return this.where;
    }
}
