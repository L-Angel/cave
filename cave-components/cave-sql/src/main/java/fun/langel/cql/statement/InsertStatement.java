package fun.langel.cql.statement;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Table;
import fun.langel.cql.node.Value;

import java.util.List;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/10 15:43
 **/
public interface InsertStatement extends Statement {

    Table table();

    List<Column> into();

    List<List<Value>> values();


}
