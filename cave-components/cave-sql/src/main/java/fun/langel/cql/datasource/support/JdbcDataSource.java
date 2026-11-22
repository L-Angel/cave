package fun.langel.cql.datasource.support;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fun.langel.cql.Language;
import fun.langel.cql.constant.Const;
import fun.langel.cql.datasource.*;
import fun.langel.cql.datasource.Connection;
import fun.langel.cql.exception.DataSourceException;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.resolve.RvResolver;
import fun.langel.cql.resolve.rv.JdbcRvResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.util.Props;
import fun.langel.cql.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 17:06
 **/
public class JdbcDataSource implements DataSource, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcDataSource.class);

    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 10;
    private static final int DEFAULT_MINIMUM_IDLE = 2;
    private static final long DEFAULT_CONNECTION_TIMEOUT = 30000L;
    private static final long DEFAULT_IDLE_TIMEOUT = 600000L;
    private static final long DEFAULT_MAX_LIFETIME = 1800000L;

    private volatile HikariDataSource pool;

    static {
        try {
            String driverKlass = Props.getProperty(Const.CAVE_JDBC_DRIVER);
            // Class.forName triggers JDBC 4 auto-registration; registering a Driver
            // instance manually is redundant and leaks a driver registration.
            Class.forName(StringUtil.isEmpty(driverKlass) ? Driver.class.getName() : driverKlass);
        } catch (ClassNotFoundException e) {
            // Keep the datasource loadable for applications that only use the ES or
            // Mongo datasources and have excluded the MySQL driver.
            LOG.error("JDBC driver not found: " + e.getMessage(), e);
        }
    }


    @Override
    public DataSourceType type() {
        return DataSourceType.MYSQL;
    }

    @Override
    public Connection getConnection() {
        return new JdbcConnection(acquireConnection());
    }

    private java.sql.Connection acquireConnection() {
        try {
            return pool().getConnection();
        } catch (SQLException ex) {
            throw new DataSourceException("Failed to acquire a mysql connection from the pool.", ex);
        }
    }

    private HikariDataSource pool() {
        if (this.pool == null) {
            synchronized (this) {
                if (this.pool == null) {
                    this.pool = createPool();
                }
            }
        }
        return this.pool;
    }

    private HikariDataSource createPool() {
        final String url = jdbcUrl();
        final String userName = Props.getProperty(Const.CAVE_MYSQL_USERNAME);
        final String password = Props.getProperty(Const.CAVE_MYSQL_PASSWORD);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        if (!StringUtil.isEmpty(userName)) {
            config.setUsername(userName);
        }
        if (!StringUtil.isEmpty(password)) {
            config.setPassword(password);
        }
        int maximumPoolSize = Props.getInt(Const.CAVE_MYSQL_POOL_MAXIMUM_SIZE, DEFAULT_MAXIMUM_POOL_SIZE);
        int minimumIdle = Props.getInt(Const.CAVE_MYSQL_POOL_MINIMUM_IDLE, DEFAULT_MINIMUM_IDLE);
        config.setMaximumPoolSize(Math.max(1, maximumPoolSize));
        config.setMinimumIdle(Math.max(0, Math.min(minimumIdle, maximumPoolSize)));
        config.setConnectionTimeout(Props.getInt(Const.CAVE_MYSQL_POOL_CONNECTION_TIMEOUT,
                (int) DEFAULT_CONNECTION_TIMEOUT));
        config.setIdleTimeout(Props.getInt(Const.CAVE_MYSQL_POOL_IDLE_TIMEOUT, (int) DEFAULT_IDLE_TIMEOUT));
        config.setMaxLifetime(Props.getInt(Const.CAVE_MYSQL_POOL_MAX_LIFETIME, (int) DEFAULT_MAX_LIFETIME));
        config.setPoolName("cave-mysql");
        try {
            return new HikariDataSource(config);
        } catch (RuntimeException ex) {
            throw new DataSourceException("Failed to initialize the mysql connection pool: " + url, ex);
        }
    }

    /**
     * Builds the JDBC URL. {@code cave.mysql.url} takes precedence over
     * host/dbName; {@code cave.mysql.url-params} is appended as connection
     * properties (SSL, serverTimezone, allowPublicKeyRetrieval, ...).
     */
    static String jdbcUrl() {
        String url = Props.getProperty(Const.CAVE_MYSQL_URL);
        if (StringUtil.isEmpty(url)) {
            final String host = Props.getProperty(Const.CAVE_MYSQL_HOST);
            final String dbName = Props.getProperty(Const.CAVE_MYSQL_DBNAME);
            if (StringUtil.isEmpty(host)) {
                throw new DataSourceException("Missing datasource property: " + Const.CAVE_MYSQL_HOST
                        + " (or set " + Const.CAVE_MYSQL_URL + ")");
            }
            if (StringUtil.isEmpty(dbName)) {
                throw new DataSourceException("Missing datasource property: " + Const.CAVE_MYSQL_DBNAME
                        + " (or set " + Const.CAVE_MYSQL_URL + ")");
            }
            url = "jdbc:mysql://" + host + "/" + dbName;
        }
        final String params = Props.getProperty(Const.CAVE_MYSQL_URL_PARAMS);
        if (!StringUtil.isEmpty(params)) {
            url = url + (url.contains("?") ? "&" : "?") + params;
        }
        return url;
    }

    @Override
    public void close() {
        HikariDataSource current = this.pool;
        if (current != null) {
            current.close();
            this.pool = null;
        }
    }
}

class JdbcConnection implements Connection {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcConnection.class);

    private final java.sql.Connection connection;

    JdbcConnection(java.sql.Connection connection) {
        this.connection = connection;
    }

    @Override
    public Session getSession() {
        return new JdbcPreparedSession(this.connection);

    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            LOG.warn("Failed to close JDBC connection.", ex);
        }
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
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            return this.rvResolver.resolve(resultSet, null);
        } catch (SQLException e) {
            throw new DataSourceException("Failed to execute query: " + sql, e);
        }
    }

    @Override
    public Number executeUpdate(String sql) {
        return executeUpdate0(sql);
    }

    @Override
    public Number executeDelete(String sql) {
        return executeUpdate0(sql);
    }

    private Number executeUpdate0(String sql) {
        try {
            return (Number) this.rvResolver.resolve(this.statement.executeUpdate(sql), null);
        } catch (SQLException e) {
            throw new DataSourceException("Failed to execute update: " + sql, e);
        }
    }

    @Override
    public void close() {
        try {
            this.statement.close();
        } catch (SQLException ex) {
            LOG.warn("Failed to close JDBC statement.", ex);
        }
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
        try (PreparedStatement stat = this.connection.prepareStatement(sql)) {
            prepare(stat);
            try (ResultSet resultSet = stat.executeQuery()) {
                return this.rvResolver.resolve(resultSet, null);
            }
        } catch (SQLException ex) {
            throw new DataSourceException("Failed to execute query: " + sql, ex);
        }
    }

    @Override
    public Number executeUpdate(String sql) {
        return runUpdate(sql);
    }

    @Override
    public Number executeDelete(String sql) {
        return runUpdate(sql);
    }

    private Number runUpdate(String sql) {
        try (PreparedStatement stat = this.connection.prepareStatement(sql)) {
            prepare(stat);
            return (Number) this.rvResolver.resolve(stat.executeUpdate(), null);
        } catch (SQLException ex) {
            throw new DataSourceException("Failed to execute update: " + sql, ex);
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

    private void prepare(PreparedStatement stat) throws SQLException {
        List<Parameter> parameters = this.parameters;
        for (Parameter p : parameters) {
            if (p.pos() < 0) {
                continue;
            }
            // Parameter positions start at 0, JDBC binds are 1-based.
            try {
                stat.setObject(p.pos() + 1, p.getValue());
            } catch (SQLException ex) {
                throw new DataSourceException("Failed to bind parameter '" + p.getName() + "' at position "
                        + (p.pos() + 1), ex);
            }
        }
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            LOG.warn("Failed to close JDBC connection.", ex);
        }
    }
}
