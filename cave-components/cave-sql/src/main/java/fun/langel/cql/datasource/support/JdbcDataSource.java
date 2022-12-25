package fun.langel.cql.datasource.support;

import com.mysql.cj.jdbc.Driver;
import fun.langel.cql.Language;
import fun.langel.cql.constant.Const;
import fun.langel.cql.datasource.*;
import fun.langel.cql.datasource.Connection;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.resolve.RvResolver;
import fun.langel.cql.resolve.rv.JdbcRvResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 17:06
 **/
public class JdbcDataSource implements DataSource {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcDataSource.class);

    static {
        try {
            String driverKlass = System.getProperty(Const.CAVE_JDBC_DRIVER);
            if (StringUtil.isEmpty(driverKlass)) {
                DriverManager.registerDriver(new Driver());
            } else {
                Class.forName(driverKlass);
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    @Override
    public DataSourceType type() {
        return DataSourceType.MYSQL;
    }

    @Override
    public Connection getConnection() {

        final String host = System.getProperty(Const.CAVE_MYSQL_HOST);
        final String dbName = System.getProperty(Const.CAVE_MYSQL_DBNAME);
        final String userName = System.getProperty(Const.CAVE_MYSQL_USERNAME);
        final String password = System.getProperty(Const.CAVE_MYSQL_PASSWORD);
        Properties props = new Properties();
        props.setProperty(Const.CAVE_MYSQL_HOST, host);
        props.setProperty(Const.CAVE_MYSQL_DBNAME, dbName);
        props.setProperty(Const.CAVE_MYSQL_USERNAME, userName);
        props.setProperty(Const.CAVE_MYSQL_PASSWORD, password);
        try {
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dbName, userName, password);
            return new JdbcConnection(connection);
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }
}

class JdbcConnection implements Connection {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcConnection.class);

    private java.sql.Connection connection;

    JdbcConnection(java.sql.Connection connection) {
        this.connection = connection;
    }

    @Override
    public Session getSession() {
        return new JdbcPreparedSession(this.connection);

    }
}

class JdbcSession implements Session {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcSession.class);

    private final RvResolver<Object> rvResolver = new JdbcRvResolver();
    private final Statement statement;

    public JdbcSession(Statement statement) {
        this.statement = statement;
    }

    @Override
    public Language lang() {
        return Language.MYSQL;
    }

    @Override
    public ReturnValue<?> executeQuery(String sql) {
        try {
            this.rvResolver.resolve(this.statement.executeQuery(sql), null);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Number executeUpdate(String sql) {
        try {
            return (Number) this.rvResolver.resolve(this.statement.executeUpdate(sql), null);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Number executeDelete(String sql) {
        try {
            return (Number) this.rvResolver.resolve(this.statement.executeUpdate(sql), null);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
}

class JdbcPreparedSession extends PreparedSession {
    private static final Logger LOG = LoggerFactory.getLogger(JdbcPreparedSession.class);

    private final RvResolver<Object> rvResolver = new JdbcRvResolver();
    private final java.sql.Connection connection;

    public JdbcPreparedSession(java.sql.Connection connection) {
        this.connection = connection;
    }

    @Override
    public ReturnValue<?> executeQuery(String sql) {
        try {
            PreparedStatement stat = this.connection.prepareStatement(sql);
            prepare(stat);
            return this.rvResolver.resolve(stat.executeQuery(), null);
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public Number executeUpdate(String sql) {
        try {
            PreparedStatement stat = this.connection.prepareStatement(sql);
            prepare(stat);
            return (Number) this.rvResolver.resolve(stat.executeUpdate(), null);
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public Number executeDelete(String sql) {
        try {
            PreparedStatement stat = this.connection.prepareStatement(sql);
            prepare(stat);
            return (Number) this.rvResolver.resolve(stat.executeUpdate(), null);
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    protected ReturnValue<?> executeQuery0(String sql) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Number executeUpdate0(String sql) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Number executeDelete0(String sql) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Language lang() {
        return Language.MYSQL;
    }

    private void prepare(PreparedStatement stat) {
        List<Parameter> parameters = this.parameters;
        for (Parameter p : parameters) {
            try {
                stat.setObject(p.pos(), p.getValue());
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
    }
}