package fun.langel.cql.invoke.support;

import fun.langel.cql.invoke.Invocation;
import fun.langel.cql.invoke.Invoker;
import fun.langel.cql.invoke.Result;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:27 下午
 **/
public class EmptyInvoker implements Invoker {
    @Override
    public Result invoke(Invocation iv) {
        return new Result();
    }
}
