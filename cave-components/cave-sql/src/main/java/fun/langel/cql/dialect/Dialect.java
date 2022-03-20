package fun.langel.cql.dialect;

import fun.langel.cql.Language;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 16:43
 **/
public interface Dialect {

    Language type();

    String asString();
}
