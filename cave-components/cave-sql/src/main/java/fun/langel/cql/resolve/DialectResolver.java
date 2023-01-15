package fun.langel.cql.resolve;


import fun.langel.cql.dialect.Dialect;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 05:04
 **/
public interface DialectResolver<IN, OUT> {
    default Dialect<OUT> resolve(IN statement) {
        return null;
    }
}
