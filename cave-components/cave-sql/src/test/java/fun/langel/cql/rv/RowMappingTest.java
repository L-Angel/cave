package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class RowMappingTest {

    @Test
    public void listOfStringsIsMappedFromSingleColumnRows() {
        Rows rows = new Rows();
        rows.add(row("f", String.of("a")));
        rows.add(row("f", String.of("b")));

        Object mapped = rows.mapTo(java.lang.String.class);

        Assert.assertEquals(Arrays.asList("a", "b"), mapped);
    }

    @Test
    public void listOfIntegersIsMappedFromSingleColumnRows() {
        Rows rows = new Rows();
        rows.add(row("n", String.of("7")));

        List<?> mapped = (List<?>) rows.mapTo(Integer.class);

        Assert.assertEquals(7, mapped.get(0));
    }

    @Test
    public void listOfMapsIsSupported() {
        Rows rows = new Rows();
        Row row = new Row();
        row.put("a", String.of("x"));
        row.put("b", Number.of(3));
        rows.add(row);

        List<?> mapped = (List<?>) rows.mapTo(Map.class);

        Map<?, ?> map = (Map<?, ?>) mapped.get(0);
        Assert.assertEquals("x", map.get("a"));
        Assert.assertEquals(3, ((Number) map.get("b")).intValue());
    }

    @Test(expected = MappingException.class)
    public void scalarMappingRejectsMultipleColumns() {
        Row row = new Row();
        row.put("a", String.of("x"));
        row.put("b", String.of("y"));

        row.mapTo(java.lang.String.class);
    }

    private Row row(java.lang.String name, ReturnValue<?> value) {
        Row row = new Row();
        row.put(name, value);
        return row;
    }
}
