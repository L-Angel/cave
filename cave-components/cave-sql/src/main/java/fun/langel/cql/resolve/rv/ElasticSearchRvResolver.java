package fun.langel.cql.resolve.rv;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Function;
import fun.langel.cql.resolve.RvResolver;
import fun.langel.cql.rv.Number;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import fun.langel.cql.util.ListUtil;
import fun.langel.cql.util.MapUtil;
import fun.langel.cql.util.Pair;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedList;
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
                                  List<Column> columns) {
        if (from == null) {
            return null;
        }
        if (from.getHits() == null) {
            return null;
        }

        SearchHits hits = from.getHits();
        Aggregations aggregations = from.getAggregations();
        if (hits == null && aggregations == null) {
            return null;
        }
        Map<String, Object> aggMap = Pair.asMap(aggregationMap(aggregations));
        SearchHit[] hitArray = hits.getHits();
        if (columns.size() == 1 && aggMap.size() == 1) {
            Object v = ListUtil.toLinkedList(aggMap.values()).get(0);
            if (v == null) {
                return null;
            } else if (v instanceof Double) {
                return Number.of((double) v);
            } else if (v instanceof Integer) {
                return Number.of((int) v);
            } else if (v instanceof Long) {
                return Number.of((int) v);
            }
            return null;
        }
        Rows rows = new Rows();
        if (hitArray == null || hitArray.length == 0) {
            if (!aggMap.isEmpty()) {
                rows.add(resolve(aggMap));
            }
            return rows;
        }

        for (SearchHit hit : hitArray) {
            Map<String, Object> fieldMap = hit.getSourceAsMap();
            Map<String, Object> map = MapUtil.merge(fieldMap, aggMap);
            rows.add(resolve(map));
        }
        return rows;
    }

    private List<Pair<String, Object>> aggregationMap(Aggregations aggregations) {
        final List<Pair<String, Object>> pairs = new LinkedList<>();
        for (Aggregation aggregation : aggregations) {
            pairs.add(Pair.of(aggregation.getName(), aggregation.getMetaData() == null ? 0 : aggregation.getMetaData().get("value")));
        }
        return pairs;
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
