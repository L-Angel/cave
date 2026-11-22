package fun.langel.cql.node;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class ValueUnescapeTest {

    @Test
    public void escapedSingleQuoteIsRestored() {
        Assert.assertEquals("O'Reilly", Value.of("'O\\'Reilly'").value());
    }

    @Test
    public void doubledSingleQuoteIsRestored() {
        Assert.assertEquals("O'Reilly", Value.of("'O''Reilly'").value());
    }

    @Test
    public void escapedBackslashIsRestored() {
        Assert.assertEquals("a\\b", Value.of("'a\\\\b'").value());
    }

    @Test
    public void plainStringIsUntouched() {
        Assert.assertEquals("cave", Value.of("'cave'").value());
    }

    @Test
    public void booleanLiteralIsTyped() {
        Value value = Value.of("true");

        Assert.assertEquals(Value.Type.BOOLEAN, value.type());
        Assert.assertEquals(Boolean.TRUE, value.value());
    }

    @Test
    public void doubleQuotedStringIsUntouched() {
        Assert.assertEquals("cave", Value.of("\"cave\"").value());
    }
}
