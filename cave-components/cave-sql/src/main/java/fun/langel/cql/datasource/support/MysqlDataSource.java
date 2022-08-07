package fun.langel.cql.datasource.support;

import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.DataSourceType;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 17:06
 **/
public abstract class MysqlDataSource implements DataSource {
    @Override
    public DataSourceType type() {
        return DataSourceType.MYSQL;
    }
}
