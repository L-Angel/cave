package fun.langel.cql.exception;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/22 19:13
 **/
public class SqlParseException extends RuntimeException {
    public SqlParseException(String message) {
        super(message);
    }
}
