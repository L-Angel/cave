package fun.langel.cql.datasource;

import fun.langel.cql.Language;
import fun.langel.cql.rv.ReturnValue;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/7 06:01
 **/
public interface Session {
    Language lang();

    default ReturnValue<?> executeQuery(String sql) {
        throw new UnsupportedOperationException();
    }

    default Number executeUpdate(String sql) {
        throw new UnsupportedOperationException();
    }

    default Number executeDelete(String sql) {
        throw new UnsupportedOperationException();
    }


    default Number executeInsert(String sql) {
        throw new UnsupportedOperationException();
    }
}
