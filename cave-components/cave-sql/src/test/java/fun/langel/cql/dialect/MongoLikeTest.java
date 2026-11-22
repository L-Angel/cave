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
public class MongoLikeTest {

    @Test
    public void percentIsTranslatedToRegexWildcard() {
        String json = jsonFor("'%ab_c%'");

        Assert.assertTrue(json, json.contains(".*"));
        Assert.assertTrue(json, json.contains("\\\\Qab\\\\E"));
        Assert.assertTrue(json, json.contains("\\\\E.\\\\Q"));
        Assert.assertFalse(json, json.contains("%"));
    }

    @Test
    public void likeWithoutWildcardsIsAnchored() {
        String json = jsonFor("'abc'");

        Assert.assertTrue(json, json.contains("^(\\\\Qabc\\\\E)$"));
    }

    @Test
    public void regexCharactersAreQuoted() {
        String json = jsonFor("'a.b*'");

        Assert.assertTrue(json, json.contains("\\\\Qa.b*\\\\E"));
    }

    @Test
    public void notLikeUsesNegationInsteadOfUnescapedLookAhead() {
        String json = jsonForLike("not like", "'%abc%'");

        Assert.assertTrue(json, json.contains("$not") || json.contains("$nor"));
        Assert.assertTrue(json, json.contains(".*"));
        Assert.assertTrue(json, json.contains("abc"));
    }

    private String jsonFor(String literal) {
        return jsonForLike("like", literal);
    }

    private String jsonForLike(String operator, String literal) {
        SelectStatement statement = Cql.parseSelectStatement(
                "select f from t where f " + operator + " " + literal);
        Bson content = new MongoQDLDialectResolver().resolve(statement).content();
        return content.toBsonDocument(BsonDocument.class,
                MongoClientSettings.getDefaultCodecRegistry()).toJson();
    }
}
