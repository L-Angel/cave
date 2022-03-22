package fun.langel.cql.datasource.support;

import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.DataSourceType;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/20 2:48 下午
 **/
public class EmptyDataSource implements DataSource {

    @Override
    public DataSourceType type() {
        return DataSourceType.MYSQL;
    }
}
