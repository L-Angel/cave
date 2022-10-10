package fun.langel.cql.resolve.rv;

import fun.langel.cql.resolve.RvResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/9/27 15:13
 **/
public class JdbcRvResolver implements RvResolver<Object> {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcRvResolver.class);

    @Override
    public ReturnValue<?> resolve(Object from) {
        try {
            if (from instanceof ResultSet) {
                Rows rows = new Rows();
                ResultSet rs = (ResultSet) from;
                while (rs.next()) {
                    Row row = new Row();
                    ResultSetMetaData meta = rs.getMetaData();
                    int colSize = meta.getColumnCount();
                    for (int idx = 0; idx < colSize; idx++) {
                        String columnName = meta.getColumnName(idx);
                        row.put(columnName, resolveObj(rs.getObject(idx)));
                    }
                    rows.add(row);
                }
                return rows;
            } else {
                return resolveObj(from);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
}
