package fun.langel.cql.resolve;

import fun.langel.cql.rv.ReturnValue;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/4/26 17:16
 **/
public interface RvResolver<F> {
    ReturnValue<?> resolve(F from);
}
