package fun.langel.cql;

import fun.langel.cql.exception.SqlException;
import fun.langel.cql.node.Assignment;
import fun.langel.cql.node.Column;
import fun.langel.cql.node.Value;
import fun.langel.cql.statement.DeleteStatement;
import fun.langel.cql.statement.InsertStatement;
import fun.langel.cql.statement.UpdateStatement;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class DmlParsingTest {

    @Test
    public void insertParsesTableColumnsAndRows() {
        InsertStatement statement = Cql.parseInsertStatement(
                "insert into users(id, name, score, active, note) "
                        + "values(1, 'alice', 3.5, true, null), (2, 'bob', -4, false, 'x')");

        Assert.assertEquals("users", statement.table().getName());
        Assert.assertEquals(Arrays.asList("id", "name", "score", "active", "note"), names(statement.into()));
        Assert.assertEquals(2, statement.values().size());

        List<Value> first = statement.values().get(0);
        Assert.assertEquals(1L, first.get(0).value());
        Assert.assertEquals("alice", first.get(1).value());
        Assert.assertEquals(3.5D, first.get(2).value());
        Assert.assertEquals(Boolean.TRUE, first.get(3).value());
        Assert.assertTrue(first.get(4).isNull());

        List<Value> second = statement.values().get(1);
        Assert.assertEquals(2L, second.get(0).value());
        Assert.assertEquals(-4L, second.get(2).value());
        Assert.assertEquals("x", second.get(4).value());
    }

    @Test
    public void insertSetFormIsParsedAsSingleRow() {
        InsertStatement statement = Cql.parseInsertStatement("insert into users set id = 1, name = 'alice'");

        Assert.assertEquals(Arrays.asList("id", "name"), names(statement.into()));
        Assert.assertEquals(1, statement.values().size());
        Assert.assertEquals(1L, statement.values().get(0).get(0).value());
        Assert.assertEquals("alice", statement.values().get(0).get(1).value());
    }

    @Test
    public void updateParsesAssignmentsAndWhere() {
        UpdateStatement statement = Cql.parseUpdateStatement(
                "update users set name = 'alice', score = 9.5 where id = 7");

        Assert.assertEquals("users", statement.table().getName());
        Assert.assertEquals(2, statement.assignments().size());
        Assignment first = statement.assignments().get(0);
        Assert.assertEquals("name", first.column().name());
        Assert.assertEquals("alice", first.value().value());
        Assert.assertEquals("score", statement.assignments().get(1).column().name());
        Assert.assertEquals(9.5D, statement.assignments().get(1).value().value());
        Assert.assertNotNull(statement.where());
        Assert.assertEquals("id", ((Column) statement.where().left()).name());
    }

    @Test
    public void deleteParsesTableAndWhere() {
        DeleteStatement statement = Cql.parseDeleteStatement("delete from users where id = 7");

        Assert.assertEquals("users", statement.table().getName());
        Assert.assertNotNull(statement.where());
        Assert.assertEquals("id", ((Column) statement.where().left()).name());
    }

    @Test
    public void deleteWithoutWhereIsAllowed() {
        DeleteStatement statement = Cql.parseDeleteStatement("delete from users");

        Assert.assertEquals("users", statement.table().getName());
        Assert.assertNull(statement.where());
    }

    @Test
    public void insertParsesExponentNumbersAsNumbers() {
        InsertStatement statement = Cql.parseInsertStatement("insert into t(v) values(1e3), (1.5E-4)");

        Assert.assertEquals(Value.Type.NUMBER, statement.values().get(0).get(0).type());
        Assert.assertEquals(1000D, statement.values().get(0).get(0).value());
        Assert.assertEquals(1.5E-4D, statement.values().get(1).get(0).value());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void insertRequiresColumnList() {
        Cql.parseInsertStatement("insert into users values(1, 'alice')");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void insertSelectIsRejected() {
        Cql.parseInsertStatement("insert into users(id) select id from other");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void insertIgnoreIsRejected() {
        Cql.parseInsertStatement("insert ignore into users(id) values(1)");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void onDuplicateKeyUpdateIsRejected() {
        Cql.parseInsertStatement("insert into users(id) values(1) on duplicate key update id = 2");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updateAliasIsRejected() {
        Cql.parseUpdateStatement("update users u set name = 'a'");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updateOrderByIsRejected() {
        Cql.parseUpdateStatement("update users set name = 'a' order by id");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void deleteLimitIsRejected() {
        Cql.parseDeleteStatement("delete from users limit 1");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updateExpressionValueIsRejected() {
        Cql.parseUpdateStatement("update users set score = score + 1");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void insertExpressionValueIsRejected() {
        Cql.parseInsertStatement("insert into users(id) values(1 + 1)");
    }

    @Test(expected = SqlException.class)
    public void insertValueCountMustMatchColumns() {
        Cql.parseInsertStatement("insert into users(id, name) values(1)");
    }

    @Test(expected = SqlException.class)
    public void duplicateInsertColumnsAreRejected() {
        Cql.parseInsertStatement("insert into users(id, id) values(1, 2)");
    }

    @Test(expected = SqlException.class)
    public void duplicateSetColumnsAreRejected() {
        Cql.parseUpdateStatement("update users set name = 'a', name = 'b'");
    }

    @Test(expected = SqlException.class)
    public void parseDeleteStatementRequiresDelete() {
        Cql.parseDeleteStatement("select id from users");
    }

    private static List<String> names(List<Column> columns) {
        List<String> names = new ArrayList<>();
        for (Column column : columns) {
            names.add(column.name());
        }
        return names;
    }
}
