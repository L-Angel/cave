package fun.langel.cql.datasource;


import fun.langel.cql.datasource.support.EmptyDatasource;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 17:05
 **/
public interface Datasource {

    Datasource EMPTY = new EmptyDatasource();

    DatasourceType type();
}
