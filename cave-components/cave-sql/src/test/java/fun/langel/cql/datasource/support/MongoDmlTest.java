package fun.langel.cql.datasource.support;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import fun.langel.cql.parameter.Parameter;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Verifies the MongoDB write translation without a live server.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class MongoDmlTest {

    @Test
    public void insertSingleDocument() {
        MongoCollection<Document> collection = collection("users");
        MongoSession session = session(collection);

        java.lang.Number affected = session.executeInsert(
                "insert into users(id, name, active) values(1, 'alice', true)");

        Assert.assertEquals(1, affected.intValue());
        ArgumentCaptor<Document> captor = ArgumentCaptor.forClass(Document.class);
        verify(collection).insertOne(captor.capture());
        Document document = captor.getValue();
        Assert.assertEquals(1L, document.get("id"));
        Assert.assertEquals("alice", document.get("name"));
        Assert.assertEquals(Boolean.TRUE, document.get("active"));
    }

    @Test
    public void insertMultipleDocuments() {
        MongoCollection<Document> collection = collection("users");
        MongoSession session = session(collection);

        java.lang.Number affected = session.executeInsert(
                "insert into users(id, name) values(1, 'alice'), (2, 'bob')");

        Assert.assertEquals(2, affected.intValue());
        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<Document>> captor = ArgumentCaptor.forClass(List.class);
        verify(collection).insertMany(captor.capture());
        Assert.assertEquals(2, captor.getValue().size());
        Assert.assertEquals(2L, captor.getValue().get(1).get("id"));
        Assert.assertEquals("bob", captor.getValue().get(1).get("name"));
    }

    @Test
    public void updateSetsFieldsAndReturnsMatchedCount() {
        MongoCollection<Document> collection = collection("users");
        when(collection.updateMany(any(Bson.class), any(Bson.class)))
                .thenReturn(UpdateResult.acknowledged(2, 2L, null));
        MongoSession session = session(collection);

        java.lang.Number affected = session.executeUpdate(
                "update users set name = 'bob', score = 5 where id = 1");

        Assert.assertEquals(2, affected.intValue());
        ArgumentCaptor<Bson> filterCaptor = ArgumentCaptor.forClass(Bson.class);
        ArgumentCaptor<Bson> updateCaptor = ArgumentCaptor.forClass(Bson.class);
        verify(collection).updateMany(filterCaptor.capture(), updateCaptor.capture());
        Assert.assertEquals(document(new Document("id", 1L)), bson(filterCaptor.getValue()));
        Assert.assertEquals(document(new Document("$set",
                new Document("name", "bob").append("score", 5L))), bson(updateCaptor.getValue()));
    }

    @Test
    public void deleteUsesWhereFilter() {
        MongoCollection<Document> collection = collection("users");
        when(collection.deleteMany(any(Bson.class))).thenReturn(DeleteResult.acknowledged(2));
        MongoSession session = session(collection);

        java.lang.Number affected = session.executeDelete("delete from users where active = false");

        Assert.assertEquals(2, affected.intValue());
        ArgumentCaptor<Bson> captor = ArgumentCaptor.forClass(Bson.class);
        verify(collection).deleteMany(captor.capture());
        Assert.assertEquals(document(new Document("active", false)), bson(captor.getValue()));
    }

    @Test
    public void deleteWithoutWhereMatchesEveryDocument() {
        MongoCollection<Document> collection = collection("users");
        when(collection.deleteMany(any(Bson.class))).thenReturn(DeleteResult.acknowledged(0));
        MongoSession session = session(collection);

        session.executeDelete("delete from users");

        ArgumentCaptor<Bson> captor = ArgumentCaptor.forClass(Bson.class);
        verify(collection).deleteMany(captor.capture());
        Assert.assertTrue(captor.getValue().toBsonDocument(Document.class,
                MongoClientSettings.getDefaultCodecRegistry()).isEmpty());
    }

    @Test
    public void insertInterpolatesStatementParameters() {
        MongoCollection<Document> collection = collection("users");
        MongoSession session = session(collection);
        session.setParameters(Collections.singletonList(
                new Parameter("name", "O'Reilly", 0, String.class)));

        session.executeInsert("insert into users(name) values(#{name})");

        ArgumentCaptor<Document> captor = ArgumentCaptor.forClass(Document.class);
        verify(collection).insertOne(captor.capture());
        Assert.assertEquals("O'Reilly", captor.getValue().get("name"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void insertRequiresColumnList() {
        session(collection("users")).executeInsert("insert into users values(1, 'alice')");
    }

    private static MongoSession session(MongoCollection<Document> collection) {
        MongoDatabase database = mock(MongoDatabase.class);
        when(database.getCollection("users")).thenReturn(collection);
        return new MongoSession(database);
    }

    @SuppressWarnings("unchecked")
    private static MongoCollection<Document> collection(String name) {
        return (MongoCollection<Document>) mock(MongoCollection.class);
    }

    private static BsonDocument bson(Bson value) {
        return value.toBsonDocument(Document.class, MongoClientSettings.getDefaultCodecRegistry());
    }

    private static BsonDocument document(Document value) {
        return value.toBsonDocument(Document.class, MongoClientSettings.getDefaultCodecRegistry());
    }
}
