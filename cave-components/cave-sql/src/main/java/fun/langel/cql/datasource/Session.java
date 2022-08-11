package fun.langel.cql.datasource;

import fun.langel.cql.Language;
import fun.langel.cql.rv.ReturnValue;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/7 06:01
 **/
public interface Session {
    Language lang();

    ReturnValue<?> executeQuery(String sql);

    Number executeUpdate(String sql);

    Number executeDelete(String sql);
}
