package fun.langel.cql.statement.impl;

import fun.langel.cql.node.*;
import fun.langel.cql.statement.SelectStatement;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/20 19:27
 **/
public class SelectStatementImpl implements SelectStatement {


    private List<Column> columns;
    private From from;

    private OrderBy orderBy;

    private Limit limit;

    public SelectStatementImpl() {
    }

    public void setFrom(final From from) {
        this.from = from;
    }

    public void setOrderBy(final OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public void setLimit(final Limit limit) {
        this.limit = limit;
    }

    public void setColumns(final List<Column> columns) {
        this.columns = columns;
    }


    @Override
    public Limit limit() {
        return this.limit;
    }

    @Override
    public OrderBy orderBy() {
        return this.orderBy;
    }

    @Override
    public List<Column> columns() {
        return this.columns;
    }

    @Override
    public Expr where() {
        if (this.from == null) {
            return null;
        }
        return this.from.where();
    }

    @Override
    public List<Table> tables() {
        if (this.from == null) {
            return null;
        }
        return this.from.tables();
    }


}
