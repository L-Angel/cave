package fun.langel.cql.statement.impl;

import fun.langel.cql.node.Expr;
import fun.langel.cql.node.From;
import fun.langel.cql.node.Table;
import fun.langel.cql.statement.SelectStatement;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/20 19:27
 **/
public class SelectStatementImpl implements SelectStatement {


    private From from;

    public SelectStatementImpl() {
    }

    public void setFrom(final From from) {
        this.from = from;
    }


    public Expr where() {
        if (this.from == null) {
            return null;
        }
        return this.from.where();
    }

    public Table table() {
        if (this.from == null) {
            return null;
        }
        return this.from.table();
    }
}
