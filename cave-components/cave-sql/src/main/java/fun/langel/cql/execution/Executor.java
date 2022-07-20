package fun.langel.cql.execution;

import fun.langel.cql.invoke.ExecutionContext;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/17 22:17
 **/
public interface Executor {

    Result execute(ExecutionContext execCtx);
}
