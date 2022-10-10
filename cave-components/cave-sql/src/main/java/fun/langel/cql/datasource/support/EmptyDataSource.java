package fun.langel.cql.datasource.support;

import fun.langel.cql.datasource.Connection;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/20 2:48 下午
 **/
public class EmptyDataSource extends JdbcDataSource {
    @Override
    public Connection getConnection() {
        return null;
    }
}
