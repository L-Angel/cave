package fun.langel.cql.datasource;


import fun.langel.cql.datasource.support.EmptyDataSource;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 17:05
 **/
public interface DataSource {

    DataSource EMPTY = new EmptyDataSource();

    DataSourceType type();

    Connection getConnection();
}
