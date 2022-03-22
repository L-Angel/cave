package fun.langel.cql.parameter;

import fun.langel.cql.datasource.DataSource;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/20 2:20 下午
 **/
public interface Target {

    String name();

    DataSource dataSource();
}
