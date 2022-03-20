package fun.langel.cql.dialect;

import fun.langel.cql.Language;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 16:45
 **/
public class ElasticSearchQDL implements Dialect {

    @Override
    public Language type() {
        return Language.QDL_ELASTIC_SEARCH;
    }

    @Override
    public String asString() {
        return null;
    }
}
