package fun.langel.cql.resolve.dialect;

import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.Mysql;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/5 14:06
 **/
public class MysqlDmlDialectResolver implements MysqlDialectResolver<String, String> {

    @Override
    public Dialect<String> resolve(String statement) {
        return new Mysql(statement);
    }
}
