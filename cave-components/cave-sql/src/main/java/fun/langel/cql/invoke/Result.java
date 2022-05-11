package fun.langel.cql.invoke;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:03 下午
 **/
public class Result {

    private Object value;

    public Result() {
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {

        private Object value;


        private Builder() {
        }

        public Builder setValue(final Object value) {
            this.value = value;
            return this;
        }

        public Result build() {
            final Result r = new Result();
            r.setValue(this.value);
            return r;
        }
    }
}
