package fun.langel.cql.exception;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/10 16:14
 **/
public class DataSourceException extends RuntimeException {

    public DataSourceException() {
    }

    public DataSourceException(String message) {
        super(message);
    }
}
