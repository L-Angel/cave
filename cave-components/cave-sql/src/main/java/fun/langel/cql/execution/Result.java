package fun.langel.cql.execution;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/17 22:22
 **/
public class Result {

    public static final Result EMPTY = new EmptyResult();

    private Object value;

    private Throwable exception;

    public static Builder builder() {
        return new Builder();
    }

    private Result() {
    }

    public static class Builder {

        private Object value;

        private Throwable exception;

        private Builder() {

        }

        public Builder setValue(final Object value) {
            this.value = value;
            return this;
        }

        public Builder setException(final Throwable exception) {
            this.exception = exception;
            return this;
        }

        public Result build() {
            Result r = new Result();
            r.exception = this.exception;
            r.value = this.value;
            return r;
        }
    }

    public Object getValue() {
        return value;
    }

    public Throwable getException() {
        return exception;
    }


    public static class EmptyResult extends Result {

        private EmptyResult() {
        }

    }
}
