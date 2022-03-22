package fun.langel.cql.datasource;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 17:07
 **/
public class ElasticSearchDataSource implements DataSource {
    @Override
    public DataSourceType type() {
        return DataSourceType.ELASTIC_SEARCH;
    }
}
