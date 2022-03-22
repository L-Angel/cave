package fun.langel.cql.bind;

import fun.langel.cql.annotation.*;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.invoke.Invocation;
import fun.langel.cql.invoke.Invoker;
import fun.langel.cql.invoke.Result;
import fun.langel.cql.invoke.support.DeleteInvoker;
import fun.langel.cql.invoke.support.InsertInvoker;
import fun.langel.cql.invoke.support.SelectInvoker;
import fun.langel.cql.invoke.support.UpdateInvoker;
import fun.langel.cql.parameter.Target;

import java.lang.reflect.Method;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 8:59 下午
 **/
public class CaveMethod {

    private Class<?> klass;

    private MethodSignature signature;

    private Invocation invocation;

    public CaveMethod(final Class<?> klass, final Method method) {
        this.klass = klass;
        this.signature = new MethodSignature(method);
        this.invocation = new Invocation(this.signature);
    }

    public Object execute(Object... args) {
        checkArguments(args);

        Target target = target(args);

        Invoker invoker = Invoker.EMPTY;
        if (this.signature.isSelect()) {
            invoker = new SelectInvoker(target, this.signature.sql());
        } else if (this.signature.isDelete()) {
            invoker = new DeleteInvoker();
        } else if (this.signature.isInsert()) {
            invoker = new InsertInvoker();
        } else if (this.signature.isUpdate()) {
            invoker = new UpdateInvoker();
        }
        Result result = invoker.invoke(this.invocation);
        return result.getValue();
    }

    private void checkArguments(Object... args) {
        if (args.length < 0) {
            throw new IllegalArgumentException("illegal argument size.");
        }
    }

    private Target target(Object... args) {
        for (int idx = 0, len = args.length; idx < len; idx++) {
            Object arg = args[idx];
            if (arg.getClass().isAnnotationPresent(Meta.class)) {
                return (Target) arg;
            }
        }
        return new Target() {
            @Override
            public String name() {
                return null;
            }

            @Override
            public DataSource dataSource() {
                return DataSource.EMPTY;
            }
        };
    }

    public static class MethodSignature {

        private Method method;

        private Select selectAnno;

        private Update updateAnno;

        private Delete deleteAnno;

        private Insert insertAnno;

        private String sql;

        public MethodSignature(final Method method) {
            this.method = method;
            this.selectAnno = this.method.getDeclaredAnnotation(Select.class);
            this.updateAnno = this.method.getDeclaredAnnotation(Update.class);
            this.deleteAnno = this.method.getDeclaredAnnotation(Delete.class);
            this.insertAnno = this.method.getDeclaredAnnotation(Insert.class);
            parse();
        }

        public String sql() {
            return this.sql;
        }

        public void parse() {
            if (selectAnno != null) {
                this.sql = this.selectAnno.sql();
            } else if (deleteAnno != null) {
                this.sql = this.deleteAnno.sql();
            } else if (insertAnno != null) {
                this.sql = this.insertAnno.sql();
            } else if (updateAnno != null) {
                this.sql = this.updateAnno.sql();
            }
        }

        public boolean isSelect() {
            return this.selectAnno != null;
        }

        public boolean isDelete() {
            return this.deleteAnno != null;
        }

        public boolean isUpdate() {
            return this.deleteAnno != null;
        }

        public boolean isInsert() {
            return this.insertAnno != null;
        }
    }
}
