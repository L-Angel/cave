package fun.langel.cql.invoke.support;

import fun.langel.cql.bind.Target;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.invoke.AbstractInvoker;
import fun.langel.cql.invoke.Invocation;
import fun.langel.cql.invoke.Invoker;
import fun.langel.cql.invoke.Result;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.util.Pair;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:29 下午
 **/
public class DeleteInvoker extends AbstractInvoker implements Invoker {

    public DeleteInvoker(Target target,
                         DataSource dataSource,
                         String sql,
                         Pair<String, List<Parameter>> parameterized) {
        super(target, dataSource, sql, parameterized);
    }

    @Override
    public Result invoke(Invocation iv) {
        final Result result = new Result();
        result.setValue("Empty implement!");
        return result;
    }
}
