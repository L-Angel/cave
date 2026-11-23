package fun.langel.cql.statement.impl;

import fun.langel.cql.node.Assignment;
import fun.langel.cql.node.Expr;
import fun.langel.cql.node.Table;
import fun.langel.cql.statement.UpdateStatement;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class UpdateStatementImpl implements UpdateStatement {

    private Table table;

    private List<Assignment> assignments;

    private Expr where;

    public UpdateStatementImpl() {
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setWhere(Expr where) {
        this.where = where;
    }

    @Override
    public Table table() {
        return this.table;
    }

    @Override
    public List<Assignment> assignments() {
        return this.assignments;
    }

    @Override
    public Expr where() {
        return this.where;
    }
}
