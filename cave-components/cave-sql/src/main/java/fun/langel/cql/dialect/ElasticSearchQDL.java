package fun.langel.cql.dialect;

import fun.langel.cql.Language;
import org.elasticsearch.action.search.SearchRequest;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 16:45
 **/
public class ElasticSearchQDL implements Dialect<SearchRequest> {

    private final SearchRequest content;

    public ElasticSearchQDL(final SearchRequest content) {
        this.content = content;
    }

    @Override
    public Language type() {
        return Language.ELASTIC_SEARCH;
    }

    @Override
    public SearchRequest content() {
        return this.content;
    }
}
