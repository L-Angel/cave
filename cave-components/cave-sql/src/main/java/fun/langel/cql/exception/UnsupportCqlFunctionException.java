package fun.langel.cql.exception;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/12 22:16
 **/
public class UnsupportCqlFunctionException extends RuntimeException{

    public UnsupportCqlFunctionException() {
    }

    public UnsupportCqlFunctionException(String message) {
        super(message);
    }

    public UnsupportCqlFunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportCqlFunctionException(Throwable cause) {
        super(cause);
    }

    public UnsupportCqlFunctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
