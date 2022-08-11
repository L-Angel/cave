package fun.langel.cql.invoke;

import fun.langel.cql.bind.Target;
import fun.langel.cql.datasource.Connection;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.Session;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.util.Pair;

import java.util.List;


/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/20 2:51 下午
 **/
public abstract class AbstractInvoker implements Invoker {

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
        return getConnection() == null ? null : getConnection().getSession();
    }

    public Pair<String, List<Parameter>> parameterized() {
        return this.parameterized;
    }
}
