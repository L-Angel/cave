package fun.langel.cql.resolve.rv;

import fun.langel.cql.Cql;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import fun.langel.cql.statement.SelectStatement;
import org.elasticsearch.search.aggregations.Aggregations;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class ElasticSearchGroupResultTest {

    @Test
    public void mapsBucketsAndAppliesHavingOrderAndLimit() {
        SelectStatement statement = Cql.parseSelectStatement(
                "select a, count(*) as c from t group by a having c > 1 order by c desc limit 1");

        List<ElasticSearchRvResolver.GroupBucket> buckets = new ArrayList<>();
        buckets.add(bucket("x", 2));
        buckets.add(bucket("y", 3));
        buckets.add(bucket("z", 1));

        Rows rows = new ElasticSearchRvResolver().resolveGroupBuckets(buckets, statement);

        Assert.assertEquals(1, rows.getValue().size());
        Row row = rows.getValue().get(0);
        Assert.assertEquals("y", row.getValue().get("a").getValue());
        Assert.assertEquals(3, ((fun.langel.cql.rv.Number) row.getValue().get("c")).intValue());
    }

    @Test
    public void mapsMultipleGroupKeys() {
        SelectStatement statement = Cql.parseSelectStatement(
                "select a, b, count(*) as c from t group by a, b order by a asc");

        Map<String, Object> key = new HashMap<>();
        key.put("k0", "x");
        key.put("k1", 7);
        List<ElasticSearchRvResolver.GroupBucket> buckets = Collections.singletonList(
                new ElasticSearchRvResolver.GroupBucket(key, 2, new Aggregations(Collections.emptyList())));

        Rows rows = new ElasticSearchRvResolver().resolveGroupBuckets(buckets, statement);

        Row row = rows.getValue().get(0);
        Assert.assertEquals("x", row.getValue().get("a").getValue());
        Assert.assertEquals(7, ((fun.langel.cql.rv.Number) row.getValue().get("b")).intValue());
        Assert.assertEquals(2, ((fun.langel.cql.rv.Number) row.getValue().get("c")).intValue());
    }

    private ElasticSearchRvResolver.GroupBucket bucket(String key, long docCount) {
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put("k0", key);
        return new ElasticSearchRvResolver.GroupBucket(keyMap, docCount,
                new Aggregations(Collections.emptyList()));
    }
}
