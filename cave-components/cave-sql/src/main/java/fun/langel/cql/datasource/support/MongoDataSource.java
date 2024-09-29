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
import fun.langel.cql.node.Column;
import fun.langel.cql.node.GroupBy;
import fun.langel.cql.node.Limit;
import fun.langel.cql.node.OrderBy;
import fun.langel.cql.resolve.dialect.MongoDialectResolver;
import fun.langel.cql.resolve.dialect.MongoQDLDialectResolver;
import fun.langel.cql.resolve.rv.MongoRvResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.Props;
import fun.langel.cql.util.StringUtil;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private Connection connection;


    @Override
    public Connection getConnection() {

        // Properties props = new Properties();
        String hosts = Props.getProperty(Const.CAVE_MONGODB_HOST);
        String dbName = Props.getProperty(Const.CAVE_MONGODB_DBNAME);
        String username = Props.getProperty(Const.CAVE_MONGODB_USERNAME);
        String password = Props.getProperty(Const.CAVE_MONGODB_PASSWORD);

        MongoClientSettings.Builder builder = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(hosts));
        if (!StringUtil.isEmpty(username) && !StringUtil.isEmpty(password)) {
            builder.credential(MongoCredential.createCredential(username, dbName, password.toCharArray()));
        }


        MongoClient client = MongoClients.create(builder.build());
        MongoDatabase mongoDatabase = client.getDatabase(dbName);
        if (this.connection == null) {
            synchronized (MongoDataSource.class) {
                if (this.connection == null) {
                    this.connection = createConnection(mongoDatabase);
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

    private MongoDialectResolver<SelectStatement, Bson> dialectResolver;

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
        MongoCollection collection = this.mongoDatabase.getCollection(dialect.collection());

        List<Column> columns = dialect.columns();
        List<Bson> pipline = new LinkedList<>();

        if (columns.isEmpty() || (columns.size() > 0 && "*".equalsIgnoreCase(columns.get(0).name()))) {
            // ignore
        } else {
            pipline.add(project(columnsBson(columns)));
        }
        if (dialect.content() != null) {
            pipline.add(match(dialect.content()));
        }
        Limit limit = dialect.limit();
        if (limit != null) {
            if (limit.offset() > 0) {
                pipline.add(skip(limit.offset()));
            }
            if (limit.fetch() > 0) {
                pipline.add(limit(limit.fetch()));
            }
        }
        OrderBy orderBy = dialect.orderBy();
        if (orderBy != null) {
            pipline.add(sort(sortBson(orderBy.columns())));
        }
        GroupBy groupBy = dialect.groupBy();
        if (groupBy != null) {
            // todo
        }

        MongoCursor cursor = collection.aggregate(pipline).iterator();
        List<Bson> array = new LinkedList<>();
        while (cursor.hasNext()) {
            Bson bson = (Bson) cursor.next();
            array.add(bson);
        }
        return this.rvResolver.resolve(array, select.columns());

    }

    private BsonDocument columnsBson(List<Column> columns) {
        BsonDocument bson = new BsonDocument();
        columns.forEach(c -> bson.put(c.name(), new BsonInt32(1)));
        return bson;
    }

    private BsonDocument sortBson(List<Column> columns) {
        BsonDocument bson = new BsonDocument();
        columns.forEach(c -> {
            if (c.order() == Order.DESC) {
                bson.put(c.name(), new BsonInt32(-1));
            } else {
                bson.put(c.name(), new BsonInt32(1));
            }
        });
        return bson;
    }

    @Override
    public Number executeUpdate(String sql) {
        return null;
    }

    @Override
    protected Number executeUpdate0(String sql) {
        return null;
    }

    @Override
    public Number executeDelete(String sql) {
        return null;
    }

    @Override
    protected Number executeDelete0(String sql) {
        return null;
    }
}