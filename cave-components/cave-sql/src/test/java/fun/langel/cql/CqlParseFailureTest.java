package fun.langel.cql;

import fun.langel.cql.exception.SqlException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class CqlParseFailureTest {

    @Test(expected = SqlException.class)
    public void syntaxErrorMustNotBeSilentlyRecovered() {
        Cql.parse("select * from t where");
    }

    @Test(expected = SqlException.class)
    public void nullStatementIsRejected() {
        Cql.parse(null);
    }

    @Test
    public void emptyStatementYieldsEmptyStatements() {
        Assert.assertTrue(Cql.parse("").statements().isEmpty());
        Assert.assertTrue(Cql.parse("  ").statements().isEmpty());
    }

    @Test
    public void validStatementStillParses() {
        Assert.assertEquals(1, Cql.parse("select f from t where f = 1").statements().size());
    }
}
