package fun.langel.cql.dialect;

import com.mongodb.MongoClientSettings;
import fun.langel.cql.Cql;
import fun.langel.cql.resolve.dialect.MongoQDLDialectResolver;
import fun.langel.cql.statement.SelectStatement;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class MongoFilterTest {

    @Test
    public void isNullUsesNullFilter() {
        String filter = filter("select a from t where a is null");

        Assert.assertTrue(filter, filter.contains("\"a\": null"));
    }

    @Test
    public void isNotNullUsesNeNull() {
        String filter = filter("select a from t where a is not null");

        Assert.assertTrue(filter, filter.contains("$ne"));
        Assert.assertTrue(filter, filter.contains("null"));
    }

    @Test
    public void comparisonWithNullMatchesNothing() {
        String filter = filter("select a from t where a = null");

        Assert.assertTrue(filter, filter.contains("$expr"));
        Assert.assertTrue(filter, filter.contains("$eq"));
    }

    @Test
    public void notEqualNullMatchesNothing() {
        String filter = filter("select a from t where a != null");

        Assert.assertTrue(filter, filter.contains("$expr"));
    }

    @Test
    public void inDropsNullValues() {
        String filter = filter("select a from t where a in (1, null, 2)");

        Assert.assertTrue(filter, filter.contains("$in"));
        Assert.assertFalse(filter, filter.contains("\"null\""));
    }

    @Test
    public void notWrapsTheInnerFilter() {
        String filter = filter("select a from t where not (a = 1 or b = 2)");

        Assert.assertTrue(filter, filter.contains("$nor"));
        Assert.assertTrue(filter, filter.contains("$or"));
    }

    @Test
    public void likeIsAnchoredRegex() {
        String filter = filter("select a from t where a like '%ab_c%'");

        Assert.assertTrue(filter, filter.contains("$regex"));
        Assert.assertTrue(filter, filter.contains(".*"));
        Assert.assertTrue(filter, filter.contains(".")
                && filter.contains("ab"));
    }

    @Test
    public void booleanLiteralIsEncodedAsBoolean() {
        String filter = filter("select a from t where a = true");

        Assert.assertTrue(filter, filter.contains("\"a\": true"));
    }

    @Test
    public void notEqualExcludesMissingFields() {
        String filter = filter("select a from t where a != 'x'");

        Assert.assertTrue(filter, filter.contains("$exists"));
        Assert.assertTrue(filter, filter.contains("$ne"));
    }

    @Test
    public void fieldToFieldComparisonUsesExpr() {
        String filter = filter("select a from t where a > b");

        Assert.assertTrue(filter, filter.contains("$expr"));
        Assert.assertTrue(filter, filter.contains("$gt"));
        Assert.assertTrue(filter, filter.contains("$a"));
        Assert.assertTrue(filter, filter.contains("$b"));
    }

    @Test
    public void likeEscapeIsApplied() {
        String filter = filter("select a from t where a like '%x!_%' escape '!'");

        Assert.assertTrue(filter, filter.contains("$regex"));
        Assert.assertTrue(filter, filter.contains("\\\\Qx_\\\\E"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void scalarFunctionsFailFast() {
        filter("select date_format(curdate(), '%Y') from t");
    }

    @Test
    public void distinctIsSupportedByMongoDialect() {
        SelectStatement statement = Cql.parseSelectStatement("select distinct a from t");

        new MongoQDLDialectResolver().resolve(statement);
    }

    @Test
    public void cExistsIsTranslated() {
        String filter = filter("select a from t where c_exists('b')");

        Assert.assertTrue(filter, filter.contains("$exists"));
    }

    private String filter(String sql) {
        SelectStatement statement = Cql.parseSelectStatement(sql);
        Bson content = new MongoQDLDialectResolver().resolve(statement).content();
        return content.toBsonDocument(BsonDocument.class,
                MongoClientSettings.getDefaultCodecRegistry()).toJson();
    }
}
