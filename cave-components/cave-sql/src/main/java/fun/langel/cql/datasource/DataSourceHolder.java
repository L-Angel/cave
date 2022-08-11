package fun.langel.cql.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/9 20:35
 **/
public class DataSourceHolder {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceHolder.class);
    private DataSource dataSource;

    private final String name;

    private final Class<?> klass;

    private final int priority;

    public DataSourceHolder(final Class<?> klass,
                            final String name,
                            final int priority) {
        this.klass = klass;
        this.name = name;
        this.priority = priority;
    }

    public int priority() {
        return this.priority;
    }

    public DataSource getDataSource() {

        if (this.dataSource == null) {
            synchronized (this) {
                if (this.dataSource == null) {
                    lazy();
                }
            }
        }
        return this.dataSource;
    }

    private DataSource lazy() {
        this.dataSource = create();
        return this.dataSource;
    }

    private DataSource create() {
        Class<?> kls = this.klass;
        try {
            return (DataSource) kls.newInstance();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    public String name() {
        return this.name;
    }

    public Class<?> klass() {
        return this.klass;
    }

}
