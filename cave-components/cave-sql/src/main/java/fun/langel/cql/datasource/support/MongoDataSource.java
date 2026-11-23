package fun.langel.cql.datasource.support;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import com.mongodb.connection.ServerSettings;
import fun.langel.cql.Cql;
import fun.langel.cql.Language;
import fun.langel.cql.constant.Const;
import fun.langel.cql.datasource.*;
import fun.langel.cql.dialect.MongoQDL;
import fun.langel.cql.enums.Order;
import fun.langel.cql.exception.DataSourceException;
import fun.langel.cql.exception.SqlException;
import fun.langel.cql.node.Assignment;
import fun.langel.cql.node.Column;
import fun.langel.cql.node.Expr;
import fun.langel.cql.node.Function;
import fun.langel.cql.node.GroupBy;
import fun.langel.cql.node.Limit;
import fun.langel.cql.node.OrderBy;
import fun.langel.cql.node.Table;
import fun.langel.cql.node.Value;
import fun.langel.cql.node.func.Avg;
import fun.langel.cql.node.func.C_KeyValue;
import fun.langel.cql.node.func.Count;
import fun.langel.cql.node.func.Max;
import fun.langel.cql.node.func.Min;
import fun.langel.cql.node.func.Sum;
import fun.langel.cql.resolve.dialect.MongoQDLDialectResolver;
import fun.langel.cql.resolve.rv.MongoRvResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Rows;
import fun.langel.cql.statement.DeleteStatement;
import fun.langel.cql.statement.InsertStatement;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.statement.UpdateStatement;
import fun.langel.cql.util.AggregationUtil;
import fun.langel.cql.util.OrderByUtil;
import fun.langel.cql.util.Props;
import fun.langel.cql.util.StringUtil;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/10 16:07
 **/
public class MongoDataSource implements DataSource {
    @Override
    public DataSourceType type() {
        return DataSourceType.MONGO;
    }

    private volatile Connection connection;


    @Override
    public Connection getConnection() {
        if (this.connection == null) {
            synchronized (this) {
                if (this.connection == null) {
                    String hosts = Props.getProperty(Const.CAVE_MONGODB_HOST);
                    String dbName = Props.getProperty(Const.CAVE_MONGODB_DBNAME);
                    String username = Props.getProperty(Const.CAVE_MONGODB_USERNAME);
                    String password = Props.getProperty(Const.CAVE_MONGODB_PASSWORD);

                    if (StringUtil.isEmpty(hosts)) {
                        throw new DataSourceException("Missing datasource property: " + Const.CAVE_MONGODB_HOST);
                    }
                    if (StringUtil.isEmpty(dbName)) {
                        throw new DataSourceException("Missing datasource property: " + Const.CAVE_MONGODB_DBNAME);
                    }

                    MongoClientSettings.Builder builder = MongoClientSettings.builder()
                            .applyConnectionString(new ConnectionString(hosts));
                    if (!StringUtil.isEmpty(username) && !StringUtil.isEmpty(password)) {
                        builder.credential(MongoCredential.createCredential(username, dbName, password.toCharArray()));
                    }

                    MongoClient client = MongoClients.create(builder.build());
                    this.connection = createConnection(client.getDatabase(dbName));
                }
            }
        }
        return this.connection;
    }

    private MongoConnection createConnection(MongoDatabase mongoDatabase) {
        return new MongoConnection(mongoDatabase);
    }
}

class MongoConnection implements Connection {

    private final MongoDatabase mongoDatabase;

    public MongoConnection(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    @Override
    public Session getSession() {
        return new MongoSession(mongoDatabase);
    }

}

class MongoSession extends PreparedSession {

    private static final Logger LOG = LoggerFactory.getLogger(MongoSession.class);

    private MongoQDLDialectResolver dialectResolver;

    private MongoRvResolver rvResolver;

    private MongoDatabase mongoDatabase;

    public MongoSession(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
        this.dialectResolver = new MongoQDLDialectResolver();
        this.rvResolver = new MongoRvResolver();
    }

    @Override
    public Language lang() {
        return Language.MONGO;
    }


    @Override
    protected ReturnValue<?> executeQuery0(String sql) {
        SelectStatement select = Cql.parseSelectStatement(sql);
        MongoQDL dialect = (MongoQDL) this.dialectResolver.resolve(select);
        if (isEmptyLimit(dialect)) {
            return new Rows();
        }

        MongoCollection<org.bson.Document> collection = this.mongoDatabase.getCollection(dialect.collection());
        List<Bson> pipeline = buildPipeline(dialect, select);
        try (MongoCursor<org.bson.Document> cursor = collection.aggregate(pipeline).iterator()) {
            List<Bson> array = new LinkedList<>();
            while (cursor.hasNext()) {
                array.add(cursor.next());
            }
            if (array.isEmpty() && dialect.groupBy() == null && hasAggregates(select.columns())) {
                array.add(syntheticAggregateRow(select.columns()));
            }
            return this.rvResolver.resolve(array, select.columns());
        }
    }

    static boolean isEmptyLimit(MongoQDL dialect) {
        return dialect.limit() != null && dialect.limit().fetch() == 0;
    }

    /**
     * MongoDB aggregation stages carry order semantics. SQL evaluates
     * WHERE -> GROUP BY -> HAVING -> SELECT -> ORDER BY -> OFFSET/LIMIT, so the
     * pipeline is:
     *
     * <pre>
     * $match -> $group -> $project -> $match(HAVING) -> $sort -> $skip -> $limit
     * </pre>
     *
     * Non-aggregate queries keep the previous shape:
     * <pre>
     * $match -> $sort -> $skip -> $limit -> $project
     * </pre>
     */
    static List<Bson> buildPipeline(MongoQDL dialect, SelectStatement select) {
        List<Column> columns = dialect.columns() == null ? Collections.emptyList() : dialect.columns();
        boolean aggregate = select.groupBy() != null || select.distinct() || hasAggregates(columns);
        if (select.having() != null && !aggregate) {
            throw new UnsupportedOperationException("HAVING requires GROUP BY or an aggregate function.");
        }

        List<Bson> pipeline = new LinkedList<>();
        if (dialect.content() != null) {
            pipeline.add(match(dialect.content()));
        }
        if (aggregate) {
            validateAggregate(select, columns);
            pipeline.add(groupStage(select, columns));
            pipeline.add(aggregateProjection(select, columns));
            if (select.having() != null) {
                Bson having = new MongoQDLDialectResolver().filter(select.having());
                if (having != null) {
                    pipeline.add(match(having));
                }
            }
        }
        if (dialect.orderBy() != null) {
            pipeline.add(sort(sortBson(dialect.orderBy().columns(), columns, aggregate)));
        }
        Limit limit = dialect.limit();
        if (limit != null) {
            if (limit.offset() > 0) {
                pipeline.add(skip(limit.offset()));
            }
            if (limit.fetch() > 0) {
                pipeline.add(limit(limit.fetch()));
            }
        }
        if (!aggregate && !columns.isEmpty()) {
            pipeline.add(project(projectionBson(columns)));
        }
        return pipeline;
    }

    private static boolean hasAggregates(List<Column> columns) {
        return columns != null && columns.stream().anyMatch(AggregationUtil::isAggregateColumn);
    }

    private static void validateAggregate(SelectStatement select, List<Column> columns) {
        if (columns.isEmpty()) {
            throw new UnsupportedOperationException("GROUP BY / DISTINCT / aggregate queries require an explicit column list.");
        }
        if (select.distinct() && select.groupBy() == null) {
            for (Column column : columns) {
                if (column.isFunction() && !AggregationUtil.isAggregateColumn(column)) {
                    throw new UnsupportedOperationException("DISTINCT does not support computed columns yet: "
                            + column.function().getClass().getSimpleName());
                }
            }
        }
        if (select.having() != null && select.groupBy() == null && !hasAggregates(columns)) {
            throw new UnsupportedOperationException("HAVING requires GROUP BY or an aggregate function.");
        }
        for (Column column : columns) {
            if (AggregationUtil.isAggregateColumn(column)) {
                continue;
            }
            if (column.isFunction()) {
                if (column.function() instanceof C_KeyValue) {
                    String source = ((C_KeyValue) column.function()).column().name();
                    if (groupKey(select, source, columns) == null) {
                        throw new UnsupportedOperationException("c_keyvalue in an aggregate query requires its "
                                + "source column in GROUP BY: " + source);
                    }
                    continue;
                }
                throw new UnsupportedOperationException("Function " + column.function().getClass().getSimpleName()
                        + " is not supported in an aggregate query.");
            }
            if (groupKey(select, column.name(), columns) == null) {
                throw new UnsupportedOperationException("Column " + column.name()
                        + " must appear in GROUP BY or be aggregated.");
            }
        }
    }

    private static Document groupStage(SelectStatement select, List<Column> columns) {
        Document group = new Document();
        List<Column> groupColumns = groupColumns(select, columns);
        if (!groupColumns.isEmpty()) {
            Document id = new Document();
            int keyIndex = 0;
            for (Column groupColumn : groupColumns) {
                String source = resolveGroupSource(groupColumn.name(), columns);
                id.put("k" + keyIndex, "$" + source);
                keyIndex++;
            }
            group.put("_id", id);
        } else {
            group.put("_id", null);
        }
        for (int idx = 0; idx < columns.size(); idx++) {
            Column column = columns.get(idx);
            if (!AggregationUtil.isAggregateColumn(column)) {
                continue;
            }
            group.put(AggregationUtil.outputName(column, idx), accumulator(column.function()));
        }
        return new Document("$group", group);
    }

    /**
     * DISTINCT is lowered to GROUP BY on the selected plain columns, reusing the
     * same pipeline as an explicit GROUP BY.
     */
    private static List<Column> groupColumns(SelectStatement select, List<Column> columns) {
        if (select.groupBy() != null) {
            return select.groupBy().columns();
        }
        if (select.distinct()) {
            List<Column> distinctColumns = new LinkedList<>();
            for (Column column : columns) {
                if (column.isFunction()) {
                    continue; // aggregates are handled as metrics
                }
                distinctColumns.add(Column.of(column.name()));
            }
            return distinctColumns;
        }
        return Collections.emptyList();
    }

    private static Document accumulator(Function function) {
        if (function instanceof Count) {
            Count count = (Count) function;
            if (count.matchAll()) {
                return new Document("$sum", 1);
            }
            String field = "$" + AggregationUtil.fieldName(function);
            if (count.distinct()) {
                return new Document("$addToSet", field);
            }
            Document nonNull = new Document("$ne",
                    Arrays.asList(new Document("$ifNull", Arrays.asList(field, null)), null));
            return new Document("$sum", new Document("$cond", Arrays.asList(nonNull, 1, 0)));
        }
        String field = "$" + AggregationUtil.fieldName(function);
        if (function instanceof Avg) {
            return new Document("$avg", field);
        }
        if (function instanceof Sum) {
            return new Document("$sum", field);
        }
        if (function instanceof Min) {
            return new Document("$min", field);
        }
        if (function instanceof Max) {
            return new Document("$max", field);
        }
        throw new UnsupportedOperationException("Unsupported aggregate function: " + function.getClass().getSimpleName());
    }

    private static Document aggregateProjection(SelectStatement select, List<Column> columns) {
        Document project = new Document("_id", 0);
        for (int idx = 0; idx < columns.size(); idx++) {
            Column column = columns.get(idx);
            if (AggregationUtil.isAggregateColumn(column)) {
                String output = AggregationUtil.outputName(column, idx);
                if (column.function() instanceof Count && ((Count) column.function()).distinct()) {
                    // Remove NULL values before counting distinct values, matching SQL semantics.
                    project.put(output, new Document("$size", new Document("$setDifference",
                            Arrays.asList("$" + output, Collections.singletonList(null)))));
                } else {
                    project.put(output, 1);
                }
            } else if (column.isFunction() && column.function() instanceof C_KeyValue) {
                String source = ((C_KeyValue) column.function()).column().name();
                String key = groupKey(select, source, columns);
                if (key == null) {
                    throw new UnsupportedOperationException("c_keyvalue source column must appear in GROUP BY: " + source);
                }
                project.put(source, "$_id." + key);
            } else {
                String key = groupKey(select, column.name(), columns);
                if (key == null) {
                    throw new UnsupportedOperationException("Column " + column.name()
                            + " must appear in GROUP BY or be aggregated.");
                }
                String output = StringUtil.withDefault(column.alias(), column.name());
                project.put(output, "$_id." + key);
            }
        }
        return new Document("$project", project);
    }

    private static String groupKey(SelectStatement select, String source, List<Column> columns) {
        int keyIndex = 0;
        for (Column groupColumn : groupColumns(select, columns)) {
            if (resolveGroupSource(groupColumn.name(), columns).equals(source)) {
                return "k" + keyIndex;
            }
            keyIndex++;
        }
        return null;
    }

    private static String resolveGroupSource(String name, List<Column> columns) {
        if (columns != null) {
            for (Column column : columns) {
                if (!column.isFunction() && column.alias() != null
                        && column.alias().equalsIgnoreCase(name)) {
                    return column.name();
                }
            }
        }
        return name;
    }

    static Document syntheticAggregateRow(List<Column> columns) {
        Document row = new Document();
        for (int idx = 0; idx < columns.size(); idx++) {
            Column column = columns.get(idx);
            if (!AggregationUtil.isAggregateColumn(column)) {
                continue;
            }
            String name = AggregationUtil.outputName(column, idx);
            row.put(name, column.function() instanceof Count ? 0 : null);
        }
        return row;
    }

    private static BsonDocument projectionBson(List<Column> columns) {
        BsonDocument bson = new BsonDocument();
        for (Column column : columns) {
            if (column.isFunction() && column.function() instanceof C_KeyValue) {
                bson.put(((C_KeyValue) column.function()).column().name(), new BsonInt32(1));
                continue;
            }
            if (column.name() == null) {
                throw new UnsupportedOperationException("Unsupported SELECT column: " + column);
            }
            if (column.alias() != null && !column.alias().equals(column.name())) {
                bson.put(column.alias(), new BsonString("$" + column.name()));
            } else {
                bson.put(column.name(), new BsonInt32(1));
            }
        }
        bson.put("_id", new BsonInt32(0));
        return bson;
    }

    private static BsonDocument sortBson(List<Column> columns, List<Column> selected, boolean aggregate) {
        BsonDocument bson = new BsonDocument();
        for (Column column : columns) {
            String field = aggregate
                    ? resolveOutputField(column.name(), selected)
                    : resolveField(column.name(), selected);
            if (column.order() == Order.DESC) {
                bson.put(field, new BsonInt32(-1));
            } else {
                bson.put(field, new BsonInt32(1));
            }
        }
        return bson;
    }

    private static String resolveField(String name, List<Column> selected) {
        Column ordinal = OrderByUtil.resolve(name, selected);
        if (ordinal != null) {
            if (ordinal.isFunction()) {
                throw new UnsupportedOperationException("ORDER BY a computed positional column is not supported: " + name);
            }
            return ordinal.name();
        }
        if (selected != null) {
            for (Column column : selected) {
                if (column.alias() != null && column.alias().equalsIgnoreCase(name)) {
                    if (column.isFunction()) {
                        throw new UnsupportedOperationException("ORDER BY a computed alias is not supported: " + name);
                    }
                    return column.name();
                }
            }
        }
        return name;
    }

    private static String resolveOutputField(String name, List<Column> selected) {
        Column ordinal = OrderByUtil.resolve(name, selected);
        if (ordinal != null) {
            int idx = selected.indexOf(ordinal);
            if (AggregationUtil.isAggregateColumn(ordinal)) {
                return AggregationUtil.outputName(ordinal, idx);
            }
            if (ordinal.isFunction()) {
                throw new UnsupportedOperationException("ORDER BY a computed positional column is not supported: " + name);
            }
            return StringUtil.withDefault(ordinal.alias(), ordinal.name());
        }
        if (selected != null) {
            for (int idx = 0; idx < selected.size(); idx++) {
                Column column = selected.get(idx);
                String output;
                if (AggregationUtil.isAggregateColumn(column)) {
                    output = AggregationUtil.outputName(column, idx);
                } else if (column.isFunction() && column.function() instanceof C_KeyValue) {
                    C_KeyValue keyValue = (C_KeyValue) column.function();
                    output = StringUtil.withDefault(column.alias(), keyValue.column().name() + "." + keyValue.key());
                    if (output.equalsIgnoreCase(name)) {
                        throw new UnsupportedOperationException("ORDER BY a computed alias is not supported: " + name);
                    }
                    continue;
                } else {
                    output = StringUtil.withDefault(column.alias(), column.name());
                }
                if (output.equalsIgnoreCase(name)) {
                    return output;
                }
            }
        }
        throw new UnsupportedOperationException("ORDER BY field is not part of the aggregation output: " + name);
    }

    @Override
    protected Number executeUpdate0(String sql) {
        UpdateStatement update = Cql.parseUpdateStatement(sql);
        List<Assignment> assignments = update.assignments();
        if (assignments == null || assignments.isEmpty()) {
            throw new SqlException("UPDATE requires at least one SET assignment.");
        }
        Document set = new Document();
        for (Assignment assignment : assignments) {
            String field = assignment.column().name();
            if ("_id".equals(field)) {
                throw new UnsupportedOperationException("_id cannot be updated for mongodb.");
            }
            set.put(field, value(assignment.value()));
        }
        MongoCollection<Document> collection = collection(update.table());
        return fun.langel.cql.rv.Number.of(collection
                .updateMany(filter(update.where()), new Document("$set", set))
                .getMatchedCount());
    }

    @Override
    protected Number executeInsert0(String sql) {
        InsertStatement insert = Cql.parseInsertStatement(sql);
        List<Document> documents = insertDocuments(insert);
        MongoCollection<Document> collection = collection(insert.table());
        if (documents.size() == 1) {
            collection.insertOne(documents.get(0));
        } else {
            collection.insertMany(documents);
        }
        return fun.langel.cql.rv.Number.of(documents.size());
    }

    @Override
    protected Number executeDelete0(String sql) {
        DeleteStatement delete = Cql.parseDeleteStatement(sql);
        MongoCollection<Document> collection = collection(delete.table());
        return fun.langel.cql.rv.Number.of(collection.deleteMany(filter(delete.where())).getDeletedCount());
    }

    static List<Document> insertDocuments(InsertStatement statement) {
        List<Column> columns = statement.into();
        if (columns == null || columns.isEmpty()) {
            throw new UnsupportedOperationException("INSERT into mongodb requires an explicit column list.");
        }
        List<List<Value>> rows = statement.values();
        if (rows == null || rows.isEmpty()) {
            throw new SqlException("INSERT requires at least one VALUES row.");
        }
        List<Document> documents = new LinkedList<>();
        for (List<Value> row : rows) {
            if (row.size() != columns.size()) {
                throw new SqlException("INSERT column count (" + columns.size()
                        + ") does not match value count (" + row.size() + ").");
            }
            Document document = new Document();
            for (int idx = 0; idx < columns.size(); idx++) {
                document.put(columns.get(idx).name(), value(row.get(idx)));
            }
            documents.add(document);
        }
        return documents;
    }

    private MongoCollection<Document> collection(Table table) {
        if (table == null || StringUtil.isEmpty(table.getName())) {
            throw new SqlException("MongoDB DML requires a collection name.");
        }
        return this.mongoDatabase.getCollection(StringUtil.stripQuote(table.getName()));
    }

    private Bson filter(Expr where) {
        if (where == null) {
            return new Document();
        }
        Bson filter = this.dialectResolver.filter(where);
        return filter == null ? new Document() : filter;
    }

    private static Object value(Value value) {
        return value == null || value.isNull() ? null : value.value();
    }
}
