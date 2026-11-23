package fun.langel.cql.invoke;

import fun.langel.cql.Language;
import fun.langel.cql.bind.Target;
import fun.langel.cql.datasource.Connection;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.PreparedSession;
import fun.langel.cql.datasource.Session;
import fun.langel.cql.exception.DataSourceException;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.util.Pair;

import java.util.List;


/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/20 2:51 下午
 **/
public abstract class AbstractInvoker implements Invoker {

    public enum DmlOperation {
        INSERT,
        UPDATE,
        DELETE
    }

    private final Target target;

    private final String sql;

    private DataSource dataSource;

    private final Pair<String, List<Parameter>> parameterized;

    public AbstractInvoker(final Target target,
                           final DataSource dataSource,
                           final String sql,
                           final Pair<String, List<Parameter>> parameterized) {
        this.target = target;
        this.dataSource = dataSource;
        this.sql = sql;
        this.parameterized = parameterized;
    }

    public Target target() {
        return this.target;
    }

    public String sql() {
        return this.sql;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public Connection getConnection() {
        return getDataSource() == null ? null : dataSource.getConnection();
    }

    public Session getSession() {
        Connection connection = getConnection();
        if (connection == null) {
            throw new DataSourceException("Dont find any matched datasource.");
        }
        Session session;
        try {
            session = connection.getSession();
        } catch (RuntimeException ex) {
            connection.close();
            throw ex;
        }
        if (session == null) {
            connection.close();
            throw new DataSourceException("Dont find any matched datasource.");
        }
        if (session instanceof PreparedSession && parameterized() != null) {
            ((PreparedSession) session).setParameters(parameterized().right());
        }
        return session;
    }

    /**
     * SQL with positional {@code ?} placeholders, used by JDBC sessions which bind
     * parameters through {@link java.sql.PreparedStatement}. Non-JDBC sessions
     * consume the original SQL and interpolate values themselves.
     */
    public String executableSql() {
        Pair<String, List<Parameter>> pair = parameterized();
        if (pair == null || pair instanceof Pair.EmptyPair || pair.left() == null) {
            return this.sql;
        }
        return pair.left();
    }

    public Pair<String, List<Parameter>> parameterized() {
        return this.parameterized;
    }

    /**
     * Executes a DML statement through the session write method matching the
     * annotation. JDBC sessions receive positional SQL, while ES / Mongo sessions
     * receive the original statement so parameter values can be interpolated
     * before it is parsed as CQL.
     */
    protected Result invokeDml(Invocation iv, DmlOperation operation) {
        Session session = getSession();
        try {
            java.lang.Number affected;
            String sql = dmlSql(session);
            switch (operation) {
                case INSERT:
                    affected = session.executeInsert(sql);
                    break;
                case UPDATE:
                    affected = session.executeUpdate(sql);
                    break;
                case DELETE:
                    affected = session.executeDelete(sql);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported DML operation: " + operation);
            }
            Result result = new Result();
            result.setValue(mapAffectedRows(affected, iv.returnType()));
            return result;
        } finally {
            session.close();
        }
    }

    private String dmlSql(Session session) {
        if (session.lang() == Language.MYSQL) {
            return executableSql();
        }
        // Non-JDBC sessions interpolate #{param} values into the original CQL
        // statement and parse it themselves.
        return this.sql();
    }

    private Object mapAffectedRows(java.lang.Number affected, Class<?> returnType) {
        if (void.class.equals(returnType) || Void.class.equals(returnType)) {
            return null;
        }
        if (affected == null) {
            return null;
        }
        if (int.class.equals(returnType) || Integer.class.equals(returnType)) {
            return affected.intValue();
        }
        if (long.class.equals(returnType) || Long.class.equals(returnType)) {
            return affected.longValue();
        }
        if (short.class.equals(returnType) || Short.class.equals(returnType)) {
            return affected.shortValue();
        }
        if (boolean.class.equals(returnType) || Boolean.class.equals(returnType)) {
            return affected.intValue() > 0;
        }
        return affected;
    }
}
