package fun.langel.cql.invoke.support;

import fun.langel.cql.Language;
import fun.langel.cql.annotation.Param;
import fun.langel.cql.annotation.Select;
import fun.langel.cql.annotation.Update;
import fun.langel.cql.bind.Arg;
import fun.langel.cql.bind.CaveMethod;
import fun.langel.cql.bind.Target;
import fun.langel.cql.datasource.Connection;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.DataSourceType;
import fun.langel.cql.datasource.Session;
import fun.langel.cql.invoke.Invocation;
import fun.langel.cql.invoke.Result;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.parameter.ParameterResolver;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import fun.langel.cql.rv.String;
import fun.langel.cql.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class SelectInvokerTest {

    public interface Demo {
        @Select(sql = "select f from t where f = #{p}")
        java.lang.String query(@Param(name = "p") java.lang.String p);

        @Update(sql = "update t set f = #{p} where id = 1")
        int update(@Param(name = "p") java.lang.String p);
    }

    @Test
    public void mysqlSelectUsesParameterizedSqlAndClosesSession() throws Exception {
        FakeSession session = new FakeSession(Language.MYSQL);
        SelectInvoker invoker = selectInvoker(session);

        Result result = invoker.invoke(invocation("query"));

        Assert.assertEquals("select f from t where f = ?", session.executedSql);
        Assert.assertEquals("ok", result.getValue());
        Assert.assertTrue(session.closed);
    }

    @Test
    public void mysqlUpdateExecutesAndReturnsAffectedRows() throws Exception {
        FakeSession session = new FakeSession(Language.MYSQL);
        UpdateInvoker invoker = updateInvoker(session);

        Result result = invoker.invoke(invocation("update"));

        Assert.assertEquals("update t set f = ? where id = 1", session.executedSql);
        Assert.assertEquals(3, result.getValue());
        Assert.assertTrue(session.closed);
    }

    @Test
    public void nonJdbcUpdateUsesOriginalSqlForInterpolation() throws Exception {
        FakeSession session = new FakeSession(Language.ELASTIC_SEARCH);
        UpdateInvoker invoker = updateInvoker(session);

        Result result = invoker.invoke(invocation("update"));

        Assert.assertEquals("update t set f = #{p} where id = 1", session.executedSql);
        Assert.assertEquals(3, result.getValue());
        Assert.assertTrue(session.closed);
    }

    private SelectInvoker selectInvoker(Session session) throws Exception {
        Method method = Demo.class.getMethod("query", java.lang.String.class);
        CaveMethod.MethodSignature signature = new CaveMethod.MethodSignature(method);
        return new SelectInvoker(target(), dataSource(session), signature.sql(), parameters(signature));
    }

    private UpdateInvoker updateInvoker(Session session) throws Exception {
        Method method = Demo.class.getMethod("update", java.lang.String.class);
        CaveMethod.MethodSignature signature = new CaveMethod.MethodSignature(method);
        return new UpdateInvoker(target(), dataSource(session), signature.sql(), parameters(signature));
    }

    private Invocation invocation(java.lang.String methodName) throws Exception {
        Method method = Demo.class.getMethod(methodName, java.lang.String.class);
        return new Invocation(new CaveMethod.MethodSignature(method));
    }

    private Target target() {
        Arg[] args = new Arg[]{new Arg("p", "p", "value", java.lang.String.class)};
        return () -> args;
    }

    private Pair<java.lang.String, List<Parameter>> parameters(CaveMethod.MethodSignature signature) {
        return new ParameterResolver().resolve(signature.sql(), target().args());
    }

    private DataSource dataSource(Session session) {
        return new DataSource() {
            @Override
            public DataSourceType type() {
                return DataSourceType.MYSQL;
            }

            @Override
            public Connection getConnection() {
                return () -> session;
            }
        };
    }

    private static class FakeSession implements Session {

        private final Language language;
        private java.lang.String executedSql;
        private boolean closed;

        private FakeSession(Language language) {
            this.language = language;
        }

        @Override
        public Language lang() {
            return language;
        }

        @Override
        public fun.langel.cql.rv.ReturnValue<?> executeQuery(java.lang.String sql) {
            this.executedSql = sql;
            Row row = new Row();
            row.put("f", String.of("ok"));
            Rows rows = new Rows();
            rows.add(row);
            return rows;
        }

        @Override
        public java.lang.Number executeUpdate(java.lang.String sql) {
            this.executedSql = sql;
            return fun.langel.cql.rv.Number.of(3);
        }

        @Override
        public void close() {
            this.closed = true;
        }
    }
}
