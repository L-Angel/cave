package fun.langel.cql.invoke;

import fun.langel.cql.parameter.Target;


/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/20 2:51 下午
 **/
public abstract class AbstractInvoker implements Invoker {

    protected final Target target;

    protected final String sql;

    public AbstractInvoker(final Target target,
                           final String sql) {
        this.target = target;
        this.sql = sql;
    }
}
