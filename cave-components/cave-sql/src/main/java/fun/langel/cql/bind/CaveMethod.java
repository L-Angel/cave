package fun.langel.cql.bind;

import fun.langel.cql.annotation.*;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.DataSourceHolder;
import fun.langel.cql.datasource.DataSourceType;
import fun.langel.cql.exception.DataSourceException;
import fun.langel.cql.invoke.Invocation;
import fun.langel.cql.invoke.Invoker;
import fun.langel.cql.invoke.Result;
import fun.langel.cql.invoke.support.DeleteInvoker;
import fun.langel.cql.invoke.support.InsertInvoker;
import fun.langel.cql.invoke.support.SelectInvoker;
import fun.langel.cql.invoke.support.UpdateInvoker;
import fun.langel.cql.parameter.ParameterResolver;
import fun.langel.cql.spring.Configuration;
import fun.langel.cql.util.Pair;
import fun.langel.cql.util.StringUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 8:59 下午
 **/
public class CaveMethod {

    private static final ParameterResolver PARAMETER_RESOLVER = new ParameterResolver();

    private Class<?> klass;

    private MethodSignature signature;

    private Invocation invocation;

    private final Configuration configuration;

    public CaveMethod(final Configuration configuration,
                      final Class<?> klass, final Method method) {
        this.configuration = configuration;
        this.klass = klass;
        this.signature = new MethodSignature(method);
        this.invocation = new Invocation(this.signature);
    }

    public Object execute(Object... values) {
        Target target = target(args(this.signature.method.getParameters(), values));
        Invoker invoker;

        DataSourceHolder holder = resolveDataSource(this.signature.direct());
        if (holder == null) {
            throw new DataSourceException("Not matched any datasource " + this.signature.direct);
        }
        DataSource dataSource = holder.getDataSource();
        Pair<String, List<fun.langel.cql.parameter.Parameter>> pair =
                dataSource.type() == DataSourceType.MYSQL
                        ? PARAMETER_RESOLVER.resolveForJdbc(this.signature.sql(), target.args())
                        : PARAMETER_RESOLVER.resolve(this.signature.sql(), target.args());

        if (this.signature.isSelect()) {
            invoker = new SelectInvoker(target, dataSource, this.signature.sql(), pair);
        } else if (this.signature.isDelete()) {
            invoker = new DeleteInvoker(target, dataSource, this.signature.sql(), pair);
        } else if (this.signature.isInsert()) {
            invoker = new InsertInvoker(target, dataSource, this.signature.sql(), pair);
        } else if (this.signature.isUpdate()) {
            invoker = new UpdateInvoker(target, dataSource, this.signature.sql(), pair);
        } else {
            throw new IllegalArgumentException("Method " + this.signature.method
                    + " must be annotated with @Select, @Insert, @Update or @Delete.");
        }
        Result result = invoker.invoke(this.invocation);
        return result.getValue();
    }


    private Target target(final Arg[] args) {
        return () -> args;
    }

    private Arg[] args(final Parameter[] parameters, Object... values) {
        if (values == null || values.length == 0) {
            return new Arg[0];
        }
        if (values.length > parameters.length) {
            throw new IllegalArgumentException("Illegal argument size: expected at most "
                    + parameters.length + " but got " + values.length);
        }
        final Arg[] args = new Arg[values.length];
        for (int idx = 0, len = values.length; idx < len; idx++) {
            Parameter parameter = parameters[idx];
            Param param = parameter.getDeclaredAnnotation(Param.class);
            String alias = null;
            if (param != null) {
                alias = param.name();
            }
            args[idx] = new Arg(parameter.getName(), alias, values[idx], parameter.getType());
        }
        return args;
    }


    private DataSourceHolder resolveDataSource(final String direct) {
        if (StringUtil.isEmpty(direct)) {
            return this.configuration.getFirstDataSource();
        }
        return this.configuration.getDataSource(direct);
    }

    public static class MethodSignature {

        private Method method;

        private Select selectAnno;

        private Update updateAnno;

        private Delete deleteAnno;

        private Insert insertAnno;

        private String sql;

        // private Class<?> returnType;

        private String direct;

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

        public Method method() {
            return this.method;
        }

        public void parse() {
            if (selectAnno != null) {
                this.sql = this.selectAnno.sql();
                this.direct = this.selectAnno.direct();
            } else if (deleteAnno != null) {
                this.sql = this.deleteAnno.sql();
                this.direct = this.deleteAnno.direct();
            } else if (insertAnno != null) {
                this.sql = this.insertAnno.sql();
                this.direct = this.insertAnno.direct();
            } else if (updateAnno != null) {
                this.sql = this.updateAnno.sql();
                this.direct = this.updateAnno.direct();

            }
        }

        public String direct() {
            return this.direct;
        }

        public boolean isSelect() {
            return this.selectAnno != null;
        }

        public boolean isDelete() {
            return this.deleteAnno != null;
        }

        public boolean isUpdate() {
            return this.updateAnno != null;
        }

        public boolean isInsert() {
            return this.insertAnno != null;
        }

        public Class<?> actualType() {
            Class<?> returnType = returnType();
            if (List.class.isAssignableFrom(returnType)) {
                Type genericType = this.method.getGenericReturnType();
                if (genericType instanceof ParameterizedType) {
                    Type argument = ((ParameterizedType) genericType).getActualTypeArguments()[0];
                    if (argument instanceof Class) {
                        return (Class<?>) argument;
                    }
                    if (argument instanceof ParameterizedType) {
                        return (Class<?>) ((ParameterizedType) argument).getRawType();
                    }
                }
                return Object.class;
            }
            return returnType;
        }

        public Class<?> returnType() {
            return this.method.getReturnType();
        }
    }


}
