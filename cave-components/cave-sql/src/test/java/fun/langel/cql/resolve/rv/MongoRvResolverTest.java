package fun.langel.cql.resolve.rv;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.func.C_KeyValue;
import fun.langel.cql.rv.Rows;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class MongoRvResolverTest {

    @Test
    public void aliasIsAppliedToDocumentRows() {
        List<Bson> docs = Collections.<Bson>singletonList(new Document("f", "value"));

        Rows rows = (Rows) new MongoRvResolver().resolve(docs,
                Collections.singletonList(Column.of("f", "g")));

        Assert.assertEquals("value", rows.getValue().get(0).getValue().get("g").getValue());
        Assert.assertFalse(rows.getValue().get(0).getValue().containsKey("f"));
    }

    @Test
    public void keyValueIsAppliedToDocumentRows() {
        List<Bson> docs = Collections.<Bson>singletonList(new Document("attrs", "color:red;size:L"));

        Column column = Column.of(C_KeyValue.of(Column.of("attrs"), "'color'"), "color");
        Rows rows = (Rows) new MongoRvResolver().resolve(docs, Collections.singletonList(column));

        Assert.assertEquals("red", rows.getValue().get(0).getValue().get("color").getValue());
    }
}
