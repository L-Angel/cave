package fun.langel.cql.datasource.support;

import fun.langel.cql.Cql;
import fun.langel.cql.exception.DataSourceException;
import fun.langel.cql.statement.DeleteStatement;
import fun.langel.cql.statement.InsertStatement;
import fun.langel.cql.statement.UpdateStatement;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Verifies the Elasticsearch write request translation. The high-level client's
 * write methods are final, so the package-private request builders are tested
 * directly.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class ElasticSearchDmlTest {

    @Test
    public void insertBuildsIndexRequestsAndUsesIdColumn() {
        InsertStatement statement = Cql.parseInsertStatement(
                "insert into Users(_id, name, score) values('u1', 'alice', 3.5), ('u2', 'bob', 4)");

        List<IndexRequest> requests = ElasticSearchSession.insertRequests(statement);

        Assert.assertEquals(2, requests.size());
        Assert.assertEquals("users", requests.get(0).index());
        Assert.assertEquals("_doc", requests.get(0).type());
        Assert.assertEquals("u1", requests.get(0).id());
        Assert.assertEquals(DocWriteRequest.OpType.CREATE, requests.get(0).opType());
        Assert.assertEquals("alice", requests.get(0).sourceAsMap().get("name"));
        Assert.assertEquals(3.5D, ((Number) requests.get(0).sourceAsMap().get("score")).doubleValue(), 0D);
        Assert.assertFalse(requests.get(0).sourceAsMap().containsKey("_id"));
        Assert.assertEquals("u2", requests.get(1).id());
        Assert.assertNull(requests.get(0).validate());
    }

    @Test
    public void insertWithoutIdLetsElasticsearchGenerateOne() {
        InsertStatement statement = Cql.parseInsertStatement("insert into users(id, name) values(1, 'alice')");

        List<IndexRequest> requests = ElasticSearchSession.insertRequests(statement);

        Assert.assertNull(requests.get(0).id());
        Assert.assertEquals(DocWriteRequest.OpType.INDEX, requests.get(0).opType());
        Assert.assertEquals(WriteRequest.RefreshPolicy.NONE, requests.get(0).getRefreshPolicy());
        Assert.assertEquals(1L, ((Number) requests.get(0).sourceAsMap().get("id")).longValue());
    }

    @Test
    public void bulkInsertSetsRefreshOnTheBulkRequestOnly() {
        List<IndexRequest> requests = ElasticSearchSession.insertRequests(
                Cql.parseInsertStatement("insert into users(id) values(1), (2)"));

        BulkRequest bulk = ElasticSearchSession.insertBulkRequest(requests);

        Assert.assertEquals(WriteRequest.RefreshPolicy.IMMEDIATE, bulk.getRefreshPolicy());
        Assert.assertNull(bulk.validate());
    }

    @Test
    public void updateBuildsPainlessScriptAndWhereQuery() {
        UpdateStatement statement = Cql.parseUpdateStatement(
                "update Users set name = 'alice', score = 5 where id = 7");

        UpdateByQueryRequest request = ElasticSearchSession.updateRequest(statement);

        Assert.assertArrayEquals(new String[]{"users"}, request.indices());
        Assert.assertTrue(request.isRefresh());
        Assert.assertFalse(request.isAbortOnVersionConflict());
        Script script = request.getScript();
        Assert.assertEquals("ctx._source['name'] = params.p0;ctx._source['score'] = params.p1;",
                script.getIdOrCode());
        Map<String, Object> params = script.getParams();
        Assert.assertEquals("alice", params.get("p0"));
        Assert.assertEquals(5L, params.get("p1"));
        String query = request.getSearchRequest().source().query().toString();
        Assert.assertTrue(query, query.contains("id"));
        Assert.assertTrue(query, query.contains("7"));
    }

    @Test
    public void deleteWithoutWhereUsesMatchAll() {
        DeleteStatement statement = Cql.parseDeleteStatement("delete from Users");

        DeleteByQueryRequest request = ElasticSearchSession.deleteRequest(statement);

        Assert.assertTrue(request.isRefresh());
        QueryBuilder query = request.getSearchRequest().source().query();
        Assert.assertTrue(query.toString(), query.toString().contains("match_all"));
    }

    @Test
    public void deleteWithWhereUsesTranslatedFilter() {
        DeleteStatement statement = Cql.parseDeleteStatement("delete from users where name = 'alice'");

        DeleteByQueryRequest request = ElasticSearchSession.deleteRequest(statement);

        String query = request.getSearchRequest().source().query().toString();
        Assert.assertTrue(query, query.contains("name.keyword"));
        Assert.assertTrue(query, query.contains("alice"));
    }

    @Test
    public void successfulBulkByScrollResponsePasses() {
        BulkByScrollResponse response = mock(BulkByScrollResponse.class);
        when(response.getBulkFailures()).thenReturn(Collections.emptyList());
        when(response.getSearchFailures()).thenReturn(Collections.emptyList());

        ElasticSearchSession.ensureSuccess(response, "UPDATE");
    }

    @Test(expected = DataSourceException.class)
    public void timedOutBulkByScrollResponseFails() {
        BulkByScrollResponse response = mock(BulkByScrollResponse.class);
        when(response.getBulkFailures()).thenReturn(Collections.emptyList());
        when(response.getSearchFailures()).thenReturn(Collections.emptyList());
        when(response.isTimedOut()).thenReturn(true);

        ElasticSearchSession.ensureSuccess(response, "UPDATE");
    }

    @Test(expected = DataSourceException.class)
    public void bulkFailureFails() {
        BulkByScrollResponse response = mock(BulkByScrollResponse.class);
        when(response.getBulkFailures()).thenReturn(Collections.singletonList(
                new BulkItemResponse.Failure("users", "_doc", "1", new RuntimeException("boom"))));
        when(response.getSearchFailures()).thenReturn(Collections.emptyList());

        ElasticSearchSession.ensureSuccess(response, "UPDATE");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updatingIdIsRejected() {
        ElasticSearchSession.updateRequest(Cql.parseUpdateStatement("update users set _id = 'x'"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updatingOtherMetadataFieldsIsRejected() {
        ElasticSearchSession.updateRequest(Cql.parseUpdateStatement("update users set _routing = 'x'"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void insertingMetadataFieldsIsRejected() {
        ElasticSearchSession.insertRequests(
                Cql.parseInsertStatement("insert into users(_index, name) values('x', 'alice')"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void insertRequiresColumnList() {
        ElasticSearchSession.insertRequests(Cql.parseInsertStatement("insert into users values(1)"));
    }
}
