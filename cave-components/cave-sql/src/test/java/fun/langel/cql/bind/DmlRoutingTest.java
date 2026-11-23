package fun.langel.cql.bind;

import fun.langel.cql.Language;
import fun.langel.cql.annotation.Delete;
import fun.langel.cql.annotation.Insert;
import fun.langel.cql.annotation.Param;
import fun.langel.cql.annotation.Update;
import fun.langel.cql.datasource.Connection;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.DataSourceHolder;
import fun.langel.cql.datasource.DataSourceType;
import fun.langel.cql.datasource.Session;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.spring.Configuration;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class DmlRoutingTest {

    public interface Demo {
        @Insert(sql = "insert into users(id, name) values(#{id}, #{name})", direct = "mysql")
        int insert(@Param(name = "id") long id, @Param(name = "name") String name);

        @Update(sql = "update users set name = #{name} where id = #{id}", direct = "mysql")
        int update(@Param(name = "name") String name, @Param(name = "id") long id);

        @Delete(sql = "delete from users where id = #{id}", direct = "mysql")
        boolean delete(@Param(name = "id") long id);

        @Insert(sql = "insert into t(id, name) values(#{id}, #{name})", direct = "es")
        int insertEs(@Param(name = "id") long id, @Param(name = "name") String name);

        @Delete(sql = "delete from t where id = #{id}", direct = "mongo")
        int deleteMongo(@Param(name = "id") long id);
    }

    private static volatile FakeSession LAST_SESSION;

    @Test
    public void insertRoutesToExecuteInsert() throws Exception {
        Object result = method("insert", long.class, String.class).execute(7L, "rick");

        Assert.assertEquals(3, result);
        Assert.assertEquals("INSERT", LAST_SESSION.operation);
        Assert.assertEquals("insert into users(id, name) values(?, ?)", LAST_SESSION.sql);
        Assert.assertTrue(LAST_SESSION.closed);
    }

    @Test
    public void updateRoutesToExecuteUpdate() throws Exception {
        Object result = method("update", String.class, long.class).execute("rick", 7L);

        Assert.assertEquals(3, result);
        Assert.assertEquals("UPDATE", LAST_SESSION.operation);
        Assert.assertEquals("update users set name = ? where id = ?", LAST_SESSION.sql);
        Assert.assertTrue(LAST_SESSION.closed);
    }

    @Test
    public void deleteRoutesToExecuteDeleteAndMapsBoolean() throws Exception {
        Object result = method("delete", long.class).execute(7L);

        Assert.assertEquals(Boolean.TRUE, result);
        Assert.assertEquals("DELETE", LAST_SESSION.operation);
        Assert.assertEquals("delete from users where id = ?", LAST_SESSION.sql);
        Assert.assertTrue(LAST_SESSION.closed);
    }

    @Test
    public void nonJdbcInsertUsesExecuteInsertAndOriginalSql() throws Exception {
        Object result = method("insertEs", long.class, String.class).execute(7L, "rick");

        Assert.assertEquals(3, result);
        Assert.assertEquals("INSERT", LAST_SESSION.operation);
        Assert.assertEquals("insert into t(id, name) values(#{id}, #{name})", LAST_SESSION.sql);
        Assert.assertTrue(LAST_SESSION.closed);
    }

    @Test
    public void nonJdbcDeleteUsesExecuteDeleteAndOriginalSql() throws Exception {
        Object result = method("deleteMongo", long.class).execute(7L);

        Assert.assertEquals(3, result);
        Assert.assertEquals("DELETE", LAST_SESSION.operation);
        Assert.assertEquals("delete from t where id = #{id}", LAST_SESSION.sql);
        Assert.assertTrue(LAST_SESSION.closed);
    }

    private CaveMethod method(String name, Class<?>... parameterTypes) throws Exception {
        Configuration configuration = new Configuration();
        configuration.addDataSource(DataSourceHolder.builder()
                .setName("mysql")
                .setKlass(FakeMySqlDataSource.class)
                .build());
        configuration.addDataSource(DataSourceHolder.builder()
                .setName("es")
                .setKlass(FakeEsDataSource.class)
                .build());
        configuration.addDataSource(DataSourceHolder.builder()
                .setName("mongo")
                .setKlass(FakeMongoDataSource.class)
                .build());
        Method method = Demo.class.getMethod(name, parameterTypes);
        return new CaveMethod(configuration, Demo.class, method);
    }

    public static class FakeMySqlDataSource implements DataSource {
        @Override
        public DataSourceType type() {
            return DataSourceType.MYSQL;
        }

        @Override
        public Connection getConnection() {
            LAST_SESSION = new FakeSession(Language.MYSQL);
            return () -> LAST_SESSION;
        }
    }

    public static class FakeEsDataSource implements DataSource {
        @Override
        public DataSourceType type() {
            return DataSourceType.ELASTIC_SEARCH;
        }

        @Override
        public Connection getConnection() {
            LAST_SESSION = new FakeSession(Language.ELASTIC_SEARCH);
            return () -> LAST_SESSION;
        }
    }

    public static class FakeMongoDataSource implements DataSource {
        @Override
        public DataSourceType type() {
            return DataSourceType.MONGO;
        }

        @Override
        public Connection getConnection() {
            LAST_SESSION = new FakeSession(Language.MONGO);
            return () -> LAST_SESSION;
        }
    }

    private static class FakeSession implements Session {
        private final Language language;
        private String operation;
        private String sql;
        private boolean closed;

        private FakeSession(Language language) {
            this.language = language;
        }

        @Override
        public Language lang() {
            return language;
        }

        @Override
        public java.lang.Number executeInsert(String sql) {
            return record("INSERT", sql);
        }

        @Override
        public java.lang.Number executeUpdate(String sql) {
            return record("UPDATE", sql);
        }

        @Override
        public java.lang.Number executeDelete(String sql) {
            return record("DELETE", sql);
        }

        private java.lang.Number record(String operation, String sql) {
            this.operation = operation;
            this.sql = sql;
            return fun.langel.cql.rv.Number.of(3);
        }

        @Override
        public void close() {
            this.closed = true;
        }

        @Override
        public ReturnValue<?> executeQuery(String sql) {
            throw new UnsupportedOperationException();
        }
    }
}
