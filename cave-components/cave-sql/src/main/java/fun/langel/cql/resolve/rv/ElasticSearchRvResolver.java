package fun.langel.cql.resolve.rv;

import fun.langel.cql.node.Function;
import fun.langel.cql.resolve.RvResolver;
import fun.langel.cql.rv.Number;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/4 19:28
 **/
public class ElasticSearchRvResolver implements RvResolver<SearchResponse> {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchRvResolver.class);

    @Override
    public ReturnValue<?> resolve(SearchResponse from,
                                  List<Function> functionCall) {
        if (from == null) {
            return null;
        }
        if (from.getHits() == null) {
            return null;
        }
        SearchHits hits = from.getHits();
        if (hits == null) {
            return null;
        }
        SearchHit[] hitArray = hits.getHits();
        if (hitArray == null || hitArray.length == 0) {
            return null;
        }
        Rows rows = new Rows();
        for (SearchHit hit : hitArray) {
            Map<String, Object> fieldMap = hit.getSourceAsMap();
            rows.add(resolve(fieldMap));
        }
        return rows;
    }

    private Row resolve(Map<String, Object> r) {
        Row row = new Row();
        for (Map.Entry<String, Object> entry : r.entrySet()) {
            final String key = entry.getKey();
            Object value = entry.getValue();
            row.put(key, resolveObj(value));
        }
        return row;
    }


}
