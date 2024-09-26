package fun.langel.cql.dialect;

import fun.langel.cql.Language;
import fun.langel.cql.node.Column;
import fun.langel.cql.node.GroupBy;
import fun.langel.cql.node.Limit;
import fun.langel.cql.node.OrderBy;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/10 14:42
 **/
public class MongoQDL implements Dialect<Bson> {

    private String collection;

    private Bson document;

    private Limit limit;

    private OrderBy orderBy;

    private GroupBy groupBy;

    private List<Column> columns;

    public MongoQDL(final String collection, final Bson document,
                    final List<Column> columns,
                    final Limit limit,
                    final OrderBy orderBy,
                    final GroupBy groupBy) {
        this.collection = collection;
        this.document = document;
        this.columns = columns;
        this.limit = limit;
        this.orderBy = orderBy;
        this.groupBy = groupBy;
    }

    @Override
    public Language type() {
        return Language.MONGO;
    }

    @Override
    public Bson content() {
        return this.document;
    }

    public List<Column> columns() {
        return this.columns;
    }

    public String collection() {
        return this.collection;
    }

    public GroupBy groupBy() {
        return groupBy;
    }


    public OrderBy orderBy() {
        return orderBy;
    }


    public Limit limit() {
        return limit;
    }

}
