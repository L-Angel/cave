package fun.langel.cql.resolve.rv;

import fun.langel.cql.resolve.RvResolver;
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
public class ElasticSearchRvResolver<D> implements RvResolver<SearchResponse, D> {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchRvResolver.class);

    @Override
    public D resolve(SearchResponse from, Class<D> klass) {
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
        for (SearchHit hit : hitArray) {
            Map<String, DocumentField> fieldMap = hit.getFields();
            return resolve(fieldMap, klass);
        }
        return null;
    }

    private D resolve(Map<String, DocumentField> r, Class<D> klass) {
        try {
            Object rv = klass.newInstance();
            Field[] fields = klass.getDeclaredFields();
            for (Field f : fields) {
                DocumentField df = r.get(f.getName());
                f.setAccessible(true);
                f.set(rv, df.getValue() == null ? null : df.getValue());
            }
            return (D) rv;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
