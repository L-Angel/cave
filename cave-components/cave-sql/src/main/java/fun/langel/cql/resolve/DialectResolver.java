package fun.langel.cql.resolve;



/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 05:04
 **/
public interface DialectResolver<IN, OUT> {
    OUT resolve(IN sqlNode);
}
