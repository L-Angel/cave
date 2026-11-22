package fun.langel.cql.node.func;

import fun.langel.cql.Cql;
import fun.langel.cql.exception.SqlException;
import fun.langel.cql.node.Column;
import fun.langel.cql.node.Node;
import fun.langel.cql.node.Value;
import fun.langel.cql.node.obj.Date;
import fun.langel.cql.statement.SelectStatement;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class DateSubTest {

    private static final LocalDateTime BASE = LocalDateTime.of(2024, 1, 10, 12, 0, 0);

    @Test
    public void upperCaseIntervalUnitIsAccepted() {
        DateSub sub = DateSub.of(new Date(BASE), 2, "DAY");

        Node result = sub.executable();

        Assert.assertTrue(result instanceof Date);
        Assert.assertEquals(BASE.minusDays(2), ((Date) result).getLocalDate());
    }

    @Test
    public void mixedCaseIntervalUnitIsAccepted() {
        DateSub sub = DateSub.of(new Date(BASE), 3, "Month");

        Node result = sub.executable();

        Assert.assertEquals(BASE.minusMonths(3), ((Date) result).getLocalDate());
    }

    @Test(expected = SqlException.class)
    public void unsupportedUnitFailsFast() {
        DateSub.of(new Date(BASE), 1, "WEEK").executable();
    }

    @Test
    public void dateFormatRejectsNonDateExpression() {
        try {
            DateFormat.of(() -> fun.langel.cql.node.Value.of("not-a-date"), "%Y").executable();
            Assert.fail("expected SqlException");
        } catch (SqlException expected) {
            Assert.assertTrue(expected.getMessage().contains("date_format"));
        }
    }

    @Test
    public void mysqlTimePatternIsConverted() {
        DateFormat format = DateFormat.of(new Date(LocalDateTime.of(2024, 1, 2, 3, 4, 5)),
                "%H:%i:%s");

        Assert.assertEquals("03:04:05", ((Value) format.executable()).value().toString());
    }

    @Test
    public void parsedDateSubAndDateFormatAreEvaluated() {
        SelectStatement statement = Cql.parseSelectStatement(
                "select date_format(date_sub(curdate(), interval 2 DAY), '%Y-%m-%d') as d from t");

        Column column = statement.columns().get(0);
        Node value = column.function().executable();

        String expected = LocalDate.now().minusDays(2)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Assert.assertEquals(expected, ((Value) value).value().toString());
    }
}
