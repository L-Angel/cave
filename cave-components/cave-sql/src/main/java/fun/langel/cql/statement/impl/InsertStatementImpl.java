package fun.langel.cql.statement.impl;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Table;
import fun.langel.cql.node.Value;
import fun.langel.cql.statement.InsertStatement;

import java.util.List;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/10 15:44
 **/
public class InsertStatementImpl implements InsertStatement {


    private Table table;

    private List<Column> into;

    private List<List<Value>> values;

    public InsertStatementImpl() {
    }


    public void setTable(Table table) {
        this.table = table;
    }

    public void setInto(List<Column> into) {
        this.into = into;
    }

    public void setValues(List<List<Value>> values) {
        this.values = values;
    }

    @Override
    public Table table() {
        return this.table;
    }

    @Override
    public List<Column> into() {
        return this.into;
    }

    @Override
    public List<List<Value>> values() {
        return this.values;
    }
}
