package fun.langel.cql.dialect;

import fun.langel.cql.Cql;
import fun.langel.cql.capability.Capabilities;
import fun.langel.cql.resolve.dialect.ElasticSearchQDLDialectResolver;
import fun.langel.cql.resolve.dialect.MongoQDLDialectResolver;
import fun.langel.cql.statement.SelectStatement;
import org.junit.Assert;
import org.junit.Test;

/**
 * Conformance suite that pins the shared logical behavior of every dialect and
 * makes capability drift visible. Each new CQL feature should be added here for
 * both Elasticsearch and MongoDB.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class CapabilityConformanceTest {

    @Test
    public void capabilityMatrixIsExplicit() {
        Assert.assertTrue(Capabilities.ELASTICSEARCH.aggregations());
        Assert.assertTrue(Capabilities.ELASTICSEARCH.groupBy());
        Assert.assertTrue(Capabilities.ELASTICSEARCH.having());
        Assert.assertTrue(Capabilities.ELASTICSEARCH.cScript());

        Assert.assertTrue(Capabilities.MONGO.aggregations());
        Assert.assertTrue(Capabilities.MONGO.groupBy());
        Assert.assertTrue(Capabilities.MONGO.having());
        Assert.assertFalse(Capabilities.MONGO.cScript());
    }

    @Test
    public void bothSupportBasicFilteredQueries() {
        elasticsearch("select a from t where a = 1 order by a desc limit 10");
        mongo("select a from t where a = 1 order by a desc limit 10");
    }

    @Test
    public void bothSupportAggregates() {
        elasticsearch("select avg(x) as ax, count(*) as c from t");
        mongo("select avg(x) as ax, count(*) as c from t");
    }

    @Test
    public void bothSupportCExists() {
        elasticsearch("select a from t where c_exists('b')");
        mongo("select a from t where c_exists('b')");
    }

    @Test
    public void elasticsearchRejectsDistinctWhileMongoSupportsIt() {
        assertUnsupported(() -> elasticsearch("select distinct a from t"));
        mongo("select distinct a from t");
    }

    @Test
    public void elasticsearchRejectsFieldComparisonWhileMongoSupportsIt() {
        assertUnsupported(() -> elasticsearch("select a from t where a > b"));
        mongo("select a from t where a > b");
    }

    @Test
    public void bothSupportLikeEscape() {
        elasticsearch("select a from t where a like '%x!_%' escape '!'");
        mongo("select a from t where a like '%x!_%' escape '!'");
    }

    @Test
    public void bothSupportOrderByOrdinal() {
        elasticsearch("select a, b from t order by 2 desc");
        mongo("select a, b from t order by 2 desc");
    }

    @Test
    public void bothRejectMixedAggregateAndPlainColumnsWithoutGroupBy() {
        assertUnsupported(() -> elasticsearch("select a, avg(x) as ax from t"));
        assertUnsupported(() -> mongo("select a, avg(x) as ax from t"));
    }

    @Test
    public void bothSupportGroupByAndHaving() {
        elasticsearch("select a, count(*) as c from t group by a");
        elasticsearch("select a, count(*) as c from t group by a having c > 1 order by c desc");
        mongo("select a, count(*) as c from t group by a");
        mongo("select a, count(*) as c from t group by a having c > 1 order by c desc");
    }

    @Test
    public void mongoSupportsHavingWithoutGroupBy() {
        mongo("select count(*) as c from t having c > 1");
    }

    @Test
    public void elasticsearchRejectsHavingWithoutGroupBy() {
        assertUnsupported(() -> elasticsearch("select count(*) as c from t having c > 1"));
    }

    @Test
    public void mongoRejectsCScript() {
        assertUnsupported(() -> mongo("select a from t where c_script('1 == 1')"));
    }

    @Test
    public void elasticsearchSupportsCScript() {
        elasticsearch("select a from t where c_script('1 == 1')");
    }

    @Test
    public void bothRejectJoinAndSubqueriesAtParseTime() {
        assertUnsupported(() -> Cql.parseSelectStatement("select a from t join u on t.a = u.b"));
        assertUnsupported(() -> Cql.parseSelectStatement("select a from (select b from u) x"));
        assertUnsupported(() -> Cql.parseSelectStatement("select a from t where a in (select b from u)"));
    }

    private void elasticsearch(String sql) {
        SelectStatement statement = Cql.parseSelectStatement(sql);
        new ElasticSearchQDLDialectResolver().resolve(statement);
    }

    private void mongo(String sql) {
        SelectStatement statement = Cql.parseSelectStatement(sql);
        new MongoQDLDialectResolver().resolve(statement);
    }

    private void assertUnsupported(Runnable runnable) {
        try {
            runnable.run();
            Assert.fail("expected UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
            // expected
        }
    }
}
