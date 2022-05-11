package fun.langel.cql.datasource;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 17:06
 **/
public abstract class MysqlDatasource implements Datasource {
    @Override
    public DatasourceType type() {
        return DatasourceType.MYSQL;
    }
}
