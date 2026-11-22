package fun.langel.cql.resolve.rv;

import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import org.junit.Assert;
import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetMetaDataImpl;
import javax.sql.rowset.RowSetProvider;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class JdbcRvResolverTest {

    @Test
    public void mapsAllColumnsUsingJdbcOneBasedIndexes() throws SQLException {
        CachedRowSet rowSet = rowSet();

        Rows rows = (Rows) new JdbcRvResolver().resolve(rowSet, null);

        List<Row> values = rows.getValue();
        Assert.assertEquals(1, values.size());
        Row row = values.get(0);
        Assert.assertEquals(7, ((fun.langel.cql.rv.Number) row.getValue().get("id")).intValue());
        Assert.assertEquals("abc", row.getValue().get("name").getValue());
    }

    @Test
    public void mapsDecimalBooleanTimestampAndBinaryColumns() throws SQLException {
        RowSetMetaDataImpl meta = new RowSetMetaDataImpl();
        meta.setColumnCount(4);
        meta.setColumnName(1, "amount");
        meta.setColumnLabel(1, "amount");
        meta.setColumnType(1, java.sql.Types.DECIMAL);
        meta.setColumnName(2, "active");
        meta.setColumnLabel(2, "active");
        meta.setColumnType(2, java.sql.Types.BOOLEAN);
        meta.setColumnName(3, "created");
        meta.setColumnLabel(3, "created");
        meta.setColumnType(3, java.sql.Types.TIMESTAMP);
        meta.setColumnName(4, "payload");
        meta.setColumnLabel(4, "payload");
        meta.setColumnType(4, java.sql.Types.VARBINARY);

        CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
        rowSet.setMetaData(meta);
        rowSet.moveToInsertRow();
        rowSet.updateBigDecimal(1, new BigDecimal("12.34"));
        rowSet.updateBoolean(2, true);
        LocalDateTime created = LocalDateTime.of(2024, 1, 2, 3, 4, 5);
        rowSet.updateTimestamp(3, Timestamp.valueOf(created));
        rowSet.updateBytes(4, new byte[]{1, 2, 3});
        rowSet.insertRow();
        rowSet.moveToCurrentRow();
        rowSet.beforeFirst();

        Rows rows = (Rows) new JdbcRvResolver().resolve(rowSet, null);
        List<?> mapped = (List<?>) rows.mapTo(TypedRow.class);
        TypedRow typed = (TypedRow) mapped.get(0);

        Assert.assertEquals(new BigDecimal("12.34"), typed.amount);
        Assert.assertTrue(typed.active);
        Assert.assertEquals(created, typed.created);
        Assert.assertArrayEquals(new byte[]{1, 2, 3}, typed.payload);
    }

    public static class TypedRow {
        private BigDecimal amount;
        private boolean active;
        private LocalDateTime created;
        private byte[] payload;
    }

    private CachedRowSet rowSet() throws SQLException {
        RowSetMetaDataImpl meta = new RowSetMetaDataImpl();
        meta.setColumnCount(2);
        meta.setColumnName(1, "id");
        meta.setColumnLabel(1, "id");
        meta.setColumnType(1, java.sql.Types.INTEGER);
        meta.setColumnName(2, "name");
        meta.setColumnLabel(2, "name");
        meta.setColumnType(2, java.sql.Types.VARCHAR);

        CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
        rowSet.setMetaData(meta);
        rowSet.moveToInsertRow();
        rowSet.updateInt(1, 7);
        rowSet.updateString(2, "abc");
        rowSet.insertRow();
        rowSet.moveToCurrentRow();
        rowSet.beforeFirst();
        return rowSet;
    }
}
