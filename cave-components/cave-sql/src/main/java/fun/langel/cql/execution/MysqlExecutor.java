package fun.langel.cql.execution;

import fun.langel.cql.invoke.ExecutionContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/20 15:22
 **/
public class MysqlExecutor implements Executor {
    @Override
    public Result execute(ExecutionContext execCtx) {
        Properties props = new Properties();
        props.put("lex", "MYSQL");
        final String sql = "select * from table1 where field1 =#{value1};";

        try (Connection connection = DriverManager.getConnection("jdbc:calcite:", props)) {
            // connection
        } catch (Exception ex) {

        }
        return Result.EMPTY;
    }
}
