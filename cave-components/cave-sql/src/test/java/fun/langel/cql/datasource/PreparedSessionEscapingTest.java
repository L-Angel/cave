package fun.langel.cql.datasource;

import fun.langel.cql.Language;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.rv.ReturnValue;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class PreparedSessionEscapingTest {

    @Test
    public void quotesAndBackslashesAreEscaped() {
        CapturingSession session = new CapturingSession();
        session.setParameters(Collections.singletonList(
                new Parameter("p", "O'Reilly $1\\2", 0, String.class)));

        session.executeQuery("select f from t where f = #{p}");

        Assert.assertEquals("select f from t where f = 'O\\'Reilly $1\\\\2'", session.sql);
    }

    @Test
    public void dollarInValueIsNotTreatedAsRegexGroup() {
        CapturingSession session = new CapturingSession();
        session.setParameters(Collections.singletonList(
                new Parameter("p", "$100", 0, String.class)));

        session.executeQuery("select f from t where f = ${p}");

        Assert.assertEquals("select f from t where f = '$100'", session.sql);
    }

    @Test
    public void collectionsAreRenderedAsQuotedLists() {
        CapturingSession session = new CapturingSession();
        session.setParameters(Collections.singletonList(
                new Parameter("p", Arrays.asList("a", "b'c"), 0, java.util.List.class)));

        session.executeQuery("select f from t where f in (${p})");

        Assert.assertEquals("select f from t where f in (('a','b\\'c'))", session.sql);
    }

    @Test
    public void missingParameterValueDoesNotThrowNpe() {
        CapturingSession session = new CapturingSession();
        session.setParameters(Collections.singletonList(new Parameter("p", null, 0, null)));

        session.executeQuery("select f from t where f = #{p}");

        Assert.assertEquals("select f from t where f = null", session.sql);
    }

    private static class CapturingSession extends PreparedSession {

        private String sql;

        @Override
        public Language lang() {
            return Language.MONGO;
        }

        @Override
        protected ReturnValue<?> executeQuery0(String sql) {
            this.sql = sql;
            return null;
        }

        @Override
        protected Number executeUpdate0(String sql) {
            return null;
        }

        @Override
        protected Number executeDelete0(String sql) {
            return null;
        }
    }
}
