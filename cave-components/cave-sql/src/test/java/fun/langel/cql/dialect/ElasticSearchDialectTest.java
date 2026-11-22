package fun.langel.cql.dialect;

import fun.langel.cql.Cql;
import fun.langel.cql.resolve.dialect.ElasticSearchQDLDialectResolver;
import fun.langel.cql.statement.SelectStatement;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class ElasticSearchDialectTest {

    @Test
    public void isNullUsesMustNotExists() {
        String source = source("select a from t where a is null");

        Assert.assertTrue(source, source.contains("must_not"));
        Assert.assertTrue(source, source.contains("exists"));
    }

    @Test
    public void isNotNullUsesExists() {
        String source = source("select a from t where a is not null");

        Assert.assertTrue(source, source.contains("exists"));
        Assert.assertFalse(source, source.contains("must_not"));
    }

    @Test
    public void comparisonWithNullMatchesNothing() {
        String source = source("select a from t where a = null");

        Assert.assertTrue(source, source.contains("must_not"));
        Assert.assertTrue(source, source.contains("match_all"));
    }

    @Test
    public void likeUsesWildcardQuery() {
        String source = source("select a from t where a like '%ab_c%'");

        Assert.assertTrue(source, source.contains("wildcard"));
        Assert.assertTrue(source, source.contains("a.keyword"));
        Assert.assertTrue(source, source.contains("*ab?c*"));
    }

    @Test
    public void booleanLiteralUsesTheFieldDirectly() {
        String source = source("select a from t where a = true");

        Assert.assertTrue(source, source.contains("\"a\""));
        Assert.assertTrue(source, source.contains("\"value\":true"));
    }

    @Test
    public void notEqualExcludesMissingFields() {
        String source = source("select a from t where a != 'x'");

        Assert.assertTrue(source, source.contains("exists"));
        Assert.assertTrue(source, source.contains("must_not"));
    }

    @Test
    public void notInExcludesMissingFields() {
        String source = source("select a from t where a not in ('x', 'y')");

        Assert.assertTrue(source, source.contains("exists"));
        Assert.assertTrue(source, source.contains("must_not"));
    }

    @Test
    public void notLikeUsesExistsAndWildcardNegation() {
        String source = source("select a from t where a not like '%x%'");

        Assert.assertTrue(source, source.contains("exists"));
        Assert.assertTrue(source, source.contains("wildcard"));
        Assert.assertTrue(source, source.contains("must_not"));
    }

    @Test
    public void noLimitUsesConfigurableDefaultSize() {
        String source = source("select a from t");

        Assert.assertTrue(source, source.contains("\"size\":10000"));
    }

    @Test
    public void limitIsApplied() {
        String source = source("select a from t limit 2, 5");

        Assert.assertTrue(source, source.contains("\"from\":2"));
        Assert.assertTrue(source, source.contains("\"size\":5"));
    }

    @Test
    public void orderByAliasResolvesToSourceField() {
        String source = source("select f as g from t order by g desc");

        Assert.assertTrue(source, source.contains("\"sort\":[{\"f\""));
        Assert.assertFalse(source, source.contains("\"sort\":[{\"g\""));
    }

    @Test
    public void orderByOrdinalResolvesToSelectedField() {
        String source = source("select f from t order by 1 desc");

        Assert.assertTrue(source, source.contains("\"sort\":[{\"f\""));
    }

    @Test
    public void likeEscapeIsApplied() {
        String source = source("select a from t where a like '%x!_%' escape '!'");

        Assert.assertTrue(source, source.contains("wildcard"));
        Assert.assertTrue(source, source.contains("*x_*"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void fieldToFieldComparisonFailsFast() {
        source("select a from t where a > b");
    }

    @Test
    public void aggregateFunctionsAreTranslated() {
        String source = source("select avg(x) as ax, sum(y) as sy, min(z) as mz, max(w) as mw, "
                + "count(distinct v) as cv from t");

        Assert.assertTrue(source, source.contains("\"avg\""));
        Assert.assertTrue(source, source.contains("\"sum\""));
        Assert.assertTrue(source, source.contains("\"min\""));
        Assert.assertTrue(source, source.contains("\"max\""));
        Assert.assertTrue(source, source.contains("\"cardinality\""));
        Assert.assertTrue(source, source.contains("\"size\":0"));
    }

    @Test
    public void singleCountStarNeedsNoAggregation() {
        String source = source("select count(*) from t");

        Assert.assertTrue(source, source.contains("\"size\":0"));
        Assert.assertFalse(source, source.contains("aggregations"));
    }

    @Test
    public void countStarCanBeCombinedWithOtherAggregates() {
        String source = source("select count(*) as c, avg(x) as ax from t");

        Assert.assertTrue(source, source.contains("filter"));
        Assert.assertTrue(source, source.contains("match_all"));
        Assert.assertTrue(source, source.contains("\"avg\""));
    }

    @Test
    public void groupByUsesCompositeAggregation() {
        String source = source("select a, count(*) as c, avg(x) as ax from t "
                + "group by a having c > 1 order by c desc limit 10");

        Assert.assertTrue(source, source.contains("composite"));
        Assert.assertTrue(source, source.contains("\"k0\""));
        Assert.assertTrue(source, source.contains("\"field\":\"a\""));
        Assert.assertTrue(source, source.contains("\"avg\":{\"field\":\"x\""));
        Assert.assertTrue(source, source.contains("\"size\":0"));
        Assert.assertFalse(source, source.contains("\"sort\""));
    }

    @Test
    public void groupBySupportsMetricSubAggregations() {
        String source = source("select a, count(x) as c from t group by a");

        Assert.assertTrue(source, source.contains("\"value_count\""));
        Assert.assertTrue(source, source.contains("\"field\":\"x\""));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void aggregateAndPlainColumnFailFast() {
        source("select f, avg(x) as ax from t");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void scalarDateFunctionsFailFast() {
        source("select date_format(curdate(), '%Y') from t");
    }

    private String source(String sql) {
        SelectStatement statement = Cql.parseSelectStatement(sql);
        return new ElasticSearchQDLDialectResolver().resolve(statement).content().source().toString();
    }
}
