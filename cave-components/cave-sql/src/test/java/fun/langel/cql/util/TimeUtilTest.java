package fun.langel.cql.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.LocalDateTime;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class TimeUtilTest {

    private static final LocalDateTime EXPECTED = LocalDateTime.of(2024, 1, 2, 3, 4, 5);

    @Test
    public void parsesSpaceSeparatedDateTime() {
        Assert.assertEquals(EXPECTED, TimeUtil.toLocalDateTime("2024-01-02 03:04:05"));
    }

    @Test
    public void parsesFractionalSeconds() {
        Assert.assertEquals(EXPECTED.withNano(123_000_000),
                TimeUtil.toLocalDateTime("2024-01-02 03:04:05.123"));
    }

    @Test
    public void parsesIsoDateTime() {
        Assert.assertEquals(EXPECTED, TimeUtil.toLocalDateTime("2024-01-02T03:04:05"));
    }

    @Test
    public void parsesOffsetDateTime() {
        LocalDateTime expected = OffsetDateTime.parse("2024-01-02T03:04:05+08:00")
                .atZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
        Assert.assertEquals(expected, TimeUtil.toLocalDateTime("2024-01-02T03:04:05+08:00"));
    }

    @Test
    public void parsesDateOnly() {
        Assert.assertEquals(LocalDateTime.of(2024, 1, 2, 0, 0),
                TimeUtil.toLocalDateTime("2024-01-02"));
    }
}
