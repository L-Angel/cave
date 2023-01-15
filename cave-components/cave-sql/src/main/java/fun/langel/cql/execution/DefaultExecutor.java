package fun.langel.cql.execution;

import fun.langel.cql.invoke.ExecutionContext;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/20 15:16
 **/
public class DefaultExecutor implements Executor {

    @Override
    public Result execute(ExecutionContext execCtx) {

        return Result.EMPTY;
    }
}
