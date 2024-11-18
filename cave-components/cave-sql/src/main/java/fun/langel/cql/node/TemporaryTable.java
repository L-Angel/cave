package fun.langel.cql.node;

import fun.langel.cql.statement.SelectStatement;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/11/12 16:13
 **/
public class TemporaryTable extends Table {

    private final SelectStatement selectStatement;

    private TemporaryTable(final SelectStatement statement, final String alias) {
        super(alias);
        this.selectStatement = statement;
    }

    public static TemporaryTable of(SelectStatement selectStatement, String alias) {
        return new TemporaryTable(selectStatement, alias);
    }

    public SelectStatement getSelectStatement() {
        return selectStatement;
    }
}
