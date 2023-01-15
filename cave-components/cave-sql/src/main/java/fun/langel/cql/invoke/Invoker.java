package fun.langel.cql.invoke;


import fun.langel.cql.invoke.support.EmptyInvoker;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:03 下午
 **/
public interface Invoker {

    Invoker EMPTY = new EmptyInvoker();

    Result invoke(Invocation iv);
}
