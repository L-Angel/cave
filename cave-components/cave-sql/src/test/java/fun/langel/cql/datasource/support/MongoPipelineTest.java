package fun.langel.cql.datasource.support;

import com.mongodb.MongoClientSettings;
import fun.langel.cql.Cql;
import fun.langel.cql.dialect.MongoQDL;
import fun.langel.cql.resolve.dialect.MongoQDLDialectResolver;
import fun.langel.cql.statement.SelectStatement;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class MongoPipelineTest {

    @Test
    public void pipelineFollowsSqlExecutionOrder() {
        List<String> stages = stages("select a as g from t where b = 1 order by b desc limit 2, 5");

        Assert.assertEquals(5, stages.size());
        Assert.assertTrue(stages.get(0), stages.get(0).contains("$match"));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("$sort"));
        Assert.assertTrue(stages.get(2), stages.get(2).contains("$skip"));
        Assert.assertTrue(stages.get(3), stages.get(3).contains("$limit"));
        Assert.assertTrue(stages.get(4), stages.get(4).contains("$project"));
        Assert.assertTrue(stages.get(4), stages.get(4).contains("\"g\": \"$a\""));
        Assert.assertTrue(stages.get(4), stages.get(4).contains("\"_id\": 0"));
    }

    @Test
    public void sortUsesSourceFieldForAlias() {
        List<String> stages = stages("select a as g from t order by g desc");

        Assert.assertEquals(2, stages.size());
        Assert.assertTrue(stages.get(0), stages.get(0).contains("$sort"));
        Assert.assertTrue(stages.get(0), stages.get(0).contains("\"a\": -1"));
    }

    @Test
    public void limitZeroReturnsEmptyWithoutPipeline() {
        SelectStatement statement = Cql.parseSelectStatement("select a from t limit 0");
        MongoQDL dialect = (MongoQDL) new MongoQDLDialectResolver().resolve(statement);

        Assert.assertTrue(MongoSession.isEmptyLimit(dialect));
    }

    @Test
    public void selectStarDoesNotProject() {
        List<String> stages = stages("select * from t");

        Assert.assertTrue(stages.isEmpty());
    }

    @Test
    public void aggregateWithoutGroupByUsesSingleGroupStage() {
        List<String> stages = stages("select count(*) as c, avg(x) as ax from t");

        Assert.assertEquals(2, stages.size());
        Assert.assertTrue(stages.get(0), stages.get(0).contains("$group"));
        Assert.assertTrue(stages.get(0), stages.get(0).contains("\"$sum\": 1"));
        Assert.assertTrue(stages.get(0), stages.get(0).contains("\"$avg\": \"$x\""));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("$project"));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("\"c\": 1"));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("\"_id\": 0"));
    }

    @Test
    public void countDistinctUsesSetDifference() {
        List<String> stages = stages("select count(distinct x) as u from t");

        Assert.assertEquals(2, stages.size());
        Assert.assertTrue(stages.get(0), stages.get(0).contains("$addToSet"));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("$size"));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("$setDifference"));
    }

    @Test
    public void groupByHavingAndOrderByFollowSqlOrder() {
        List<String> stages = stages("select a, count(*) as c, avg(x) as ax from t "
                + "where b = 1 group by a having c > 1 order by c desc limit 10");

        Assert.assertEquals(6, stages.size());
        Assert.assertTrue(stages.get(0), stages.get(0).contains("$match"));
        Assert.assertTrue(stages.get(0), stages.get(0).contains("\"b\""));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("$group"));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("\"k0\": \"$a\""));
        Assert.assertTrue(stages.get(2), stages.get(2).contains("$project"));
        Assert.assertTrue(stages.get(2), stages.get(2).contains("\"a\": \"$_id.k0\""));
        Assert.assertTrue(stages.get(2), stages.get(2).contains("\"c\": 1"));
        Assert.assertTrue(stages.get(3), stages.get(3).contains("$match"));
        Assert.assertTrue(stages.get(3), stages.get(3).contains("\"c\""));
        Assert.assertTrue(stages.get(4), stages.get(4).contains("$sort"));
        Assert.assertTrue(stages.get(4), stages.get(4).contains("\"c\": -1"));
        Assert.assertTrue(stages.get(5), stages.get(5).contains("$limit"));
        Assert.assertTrue(stages.get(5), stages.get(5).contains("10"));
    }

    @Test
    public void groupByWithoutAggregateWorks() {
        List<String> stages = stages("select a as g from t group by a");

        Assert.assertEquals(2, stages.size());
        Assert.assertTrue(stages.get(1), stages.get(1).contains("\"g\": \"$_id.k0\""));
    }

    @Test
    public void distinctIsLoweredToGroupBy() {
        List<String> stages = stages("select distinct a, b from t");

        Assert.assertEquals(2, stages.size());
        Assert.assertTrue(stages.get(0), stages.get(0).contains("$group"));
        Assert.assertTrue(stages.get(0), stages.get(0).contains("\"k0\": \"$a\""));
        Assert.assertTrue(stages.get(0), stages.get(0).contains("\"k1\": \"$b\""));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("\"a\": \"$_id.k0\""));
        Assert.assertTrue(stages.get(1), stages.get(1).contains("\"b\": \"$_id.k1\""));
    }

    @Test
    public void orderByOrdinalResolvesToSelectedColumn() {
        List<String> stages = stages("select a, b from t order by 2 desc");

        Assert.assertEquals(2, stages.size());
        Assert.assertTrue(stages.get(0), stages.get(0).contains("$sort"));
        Assert.assertTrue(stages.get(0), stages.get(0).contains("\"b\": -1"));
    }

    @Test
    public void aggregateOrderByOrdinalResolvesToOutputAlias() {
        List<String> stages = stages("select a, count(*) as c from t group by a order by 2 desc");

        Assert.assertTrue(stages.get(stages.size() - 1), stages.get(stages.size() - 1).contains("\"c\": -1"));
    }

    @Test
    public void likeEscapeIsApplied() {
        List<String> stages = stages("select a from t where a like '%x!_%' escape '!'");

        Assert.assertEquals(2, stages.size());
        Assert.assertTrue(stages.get(0), stages.get(0).contains("\\\\Qx_\\\\E"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void plainColumnMustBeGroupedOrAggregated() {
        stages("select a, count(*) as c from t");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void havingRequiresAggregationOrGroupBy() {
        stages("select a from t having a > 1");
    }

    @Test
    public void emptyAggregateResultUsesSqlDefaults() {
        SelectStatement statement = Cql.parseSelectStatement("select count(*) as c, avg(x) as ax from t");

        Document row = MongoSession.syntheticAggregateRow(statement.columns());

        Assert.assertEquals(0, row.get("c"));
        Assert.assertTrue(row.containsKey("ax"));
        Assert.assertNull(row.get("ax"));
    }

    private List<String> stages(String sql) {
        SelectStatement statement = Cql.parseSelectStatement(sql);
        MongoQDL dialect = (MongoQDL) new MongoQDLDialectResolver().resolve(statement);
        List<Bson> pipeline = MongoSession.buildPipeline(dialect, statement);
        List<String> stages = new ArrayList<>();
        for (Bson stage : pipeline) {
            stages.add(stage.toBsonDocument(BsonDocument.class,
                    MongoClientSettings.getDefaultCodecRegistry()).toJson());
        }
        return stages;
    }
}
