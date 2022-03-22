package fun.langel.cql.invoke.support;

import fun.langel.cql.invoke.AbstractInvoker;
import fun.langel.cql.invoke.Invocation;
import fun.langel.cql.invoke.Result;
import fun.langel.cql.parameter.Target;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:04 下午
 **/
public class SelectInvoker extends AbstractInvoker {


    public SelectInvoker(Target target, String sql) {
        super(target, sql);
    }

    @Override
    public Result invoke(Invocation iv) {
        Result result = new Result();
        result.setValue("Select Demo invoke");
        return result;
    }

}
