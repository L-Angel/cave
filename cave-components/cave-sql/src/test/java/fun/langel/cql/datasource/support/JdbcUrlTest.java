package fun.langel.cql.datasource.support;

import fun.langel.cql.constant.Const;
import fun.langel.cql.exception.DataSourceException;
import fun.langel.cql.util.Props;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class JdbcUrlTest {

    @Before
    public void clearProperties() {
        Props.setOverride(Const.CAVE_MYSQL_HOST, null);
        Props.setOverride(Const.CAVE_MYSQL_DBNAME, null);
        Props.setOverride(Const.CAVE_MYSQL_URL, null);
        Props.setOverride(Const.CAVE_MYSQL_URL_PARAMS, null);
    }

    @After
    public void resetProperties() {
        clearProperties();
    }

    @Test
    public void buildsUrlFromHostAndDatabaseWithParams() {
        Props.setOverride(Const.CAVE_MYSQL_HOST, "127.0.0.1:3307");
        Props.setOverride(Const.CAVE_MYSQL_DBNAME, "demo");
        Props.setOverride(Const.CAVE_MYSQL_URL_PARAMS, "useSSL=false&serverTimezone=UTC");

        Assert.assertEquals("jdbc:mysql://127.0.0.1:3307/demo?useSSL=false&serverTimezone=UTC",
                JdbcDataSource.jdbcUrl());
    }

    @Test
    public void customUrlTakesPrecedenceAndKeepsExistingQuery() {
        Props.setOverride(Const.CAVE_MYSQL_URL, "jdbc:mysql://custom:3306/other?useSSL=true");
        Props.setOverride(Const.CAVE_MYSQL_URL_PARAMS, "allowPublicKeyRetrieval=true");

        Assert.assertEquals("jdbc:mysql://custom:3306/other?useSSL=true&allowPublicKeyRetrieval=true",
                JdbcDataSource.jdbcUrl());
    }

    @Test(expected = DataSourceException.class)
    public void missingHostAndUrlFailsFast() {
        JdbcDataSource.jdbcUrl();
    }
}
