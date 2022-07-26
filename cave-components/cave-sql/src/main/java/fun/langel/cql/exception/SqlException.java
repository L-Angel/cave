package fun.langel.cql.exception;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/26 22:09
 **/
public class SqlException extends RuntimeException {

    public SqlException(final String message) {
        super(message);
    }
}
