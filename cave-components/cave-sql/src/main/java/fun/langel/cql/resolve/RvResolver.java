package fun.langel.cql.resolve;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/4/26 17:16
 **/
public interface RvResolver<F, T> {
    T resolve(F from);
}
