package fun.langel.cql.resolve.rv;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.func.C_KeyValue;
import fun.langel.cql.rv.Row;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class ElasticSearchRvAliasTest {

    @Test
    public void selectAliasRenamesTheResultField() {
        Map<String, Object> source = new HashMap<>();
        source.put("f", "value");

        Row row = new ElasticSearchRvResolver().resolve(source,
                Collections.singletonList(Column.of("f", "g")));

        Assert.assertEquals("value", row.getValue().get("g").getValue());
        Assert.assertFalse(row.getValue().containsKey("f"));
    }

    @Test
    public void keyValueFunctionIsEvaluated() {
        Map<String, Object> source = new HashMap<>();
        source.put("attrs", "color:red;size:L");

        Column column = Column.of(C_KeyValue.of(Column.of("attrs"), "'color'"), "color");
        Row row = new ElasticSearchRvResolver().resolve(source, Collections.singletonList(column));

        Assert.assertEquals("red", row.getValue().get("color").getValue());
    }

    @Test
    public void keyValueAndAliasCanShareTheSameSourceField() {
        Map<String, Object> source = new HashMap<>();
        source.put("attrs", "color:red");

        Row row = new ElasticSearchRvResolver().resolve(source, java.util.Arrays.asList(
                Column.of("attrs", "raw"),
                Column.of(C_KeyValue.of(Column.of("attrs"), "'color'"), "color")));

        Assert.assertEquals("color:red", row.getValue().get("raw").getValue());
        Assert.assertEquals("red", row.getValue().get("color").getValue());
    }
}
