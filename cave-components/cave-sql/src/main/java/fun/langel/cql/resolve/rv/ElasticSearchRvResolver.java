package fun.langel.cql.resolve.rv;

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
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/4 19:28
 **/
public class ElasticSearchRvResolver implements RvResolver<SearchResponse> {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchRvResolver.class);

    @Override
    public ReturnValue<?> resolve(SearchResponse from) {
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
            Map<String, DocumentField> fieldMap = hit.getFields();
            rows.add(resolve(fieldMap));
        }
        return rows;
    }

    private Row resolve(Map<String, DocumentField> r) {
        Row row = new Row();
        for (Map.Entry<String, DocumentField> entry : r.entrySet()) {
            final String key = entry.getKey();
            DocumentField value = entry.getValue();
            row.put(key, resolveObj(value));
        }
        return row;
    }

    private ReturnValue<?> resolveObj(Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof String) {
            return fun.langel.cql.rv.String.of((String) v);
        }
        if (int.class.isAssignableFrom(v.getClass()) || (v instanceof Integer)) {
            return Number.of((Integer) v);
        }
        if (long.class.isAssignableFrom(v.getClass()) || (v instanceof Long)) {
            return Number.of((Long) v);
        }
        if (float.class.isAssignableFrom(v.getClass()) || (v instanceof Float)) {
            return Number.of((Float) v);
        }
        if (double.class.isAssignableFrom(v.getClass()) || (v instanceof Double)) {
            return Number.of((Double) v);
        }
        return fun.langel.cql.rv.String.of(String.valueOf(v));
    }
}
