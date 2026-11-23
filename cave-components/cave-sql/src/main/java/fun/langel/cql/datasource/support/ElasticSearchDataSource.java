package fun.langel.cql.datasource.support;


import fun.langel.cql.Cql;
import fun.langel.cql.Language;
import fun.langel.cql.constant.Const;
import fun.langel.cql.datasource.*;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.exception.DataSourceException;
import fun.langel.cql.exception.SqlException;
import fun.langel.cql.node.Assignment;
import fun.langel.cql.node.Column;
import fun.langel.cql.node.Table;
import fun.langel.cql.node.Value;
import fun.langel.cql.resolve.dialect.ElasticSearchQDLDialectResolver;
import fun.langel.cql.resolve.rv.ElasticSearchRvResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.statement.DeleteStatement;
import fun.langel.cql.statement.InsertStatement;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.statement.UpdateStatement;
import fun.langel.cql.util.Props;
import fun.langel.cql.util.StringUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 17:07
 **/
public class ElasticSearchDataSource implements DataSource {

    private volatile Connection connection;

    @Override
    public DataSourceType type() {
        return DataSourceType.ELASTIC_SEARCH;
    }

    @Override
    public Connection getConnection() {
        Properties props = new Properties();
        String host = StringUtil.isEmpty(Props.getProperty(Const.CAVE_ELASTICSEARCH_HOST)) ?
                Props.getProperty(Const.ES_HOST) : Props.getProperty(Const.CAVE_ELASTICSEARCH_HOST);
        if (StringUtil.isEmpty(host)) {
            throw new DataSourceException("Missing datasource property: " + Const.CAVE_ELASTICSEARCH_HOST);
        }
        props.setProperty(Const.CAVE_ELASTICSEARCH_HOST, host);
        String username = StringUtil.isEmpty(Props.getProperty(Const.CAVE_ELASTICSEARCH_USERNAME)) ?
                Props.getProperty(Const.ES_USERNAME) : Props.getProperty(Const.CAVE_ELASTICSEARCH_USERNAME);

        props.setProperty(Const.CAVE_ELASTICSEARCH_USERNAME, username == null ? "" : username);
        String password = StringUtil.isEmpty(Props.getProperty(Const.CAVE_ELASTICSEARCH_PASSWORD)) ?
                Props.getProperty(Const.ES_PASSWORD) : Props.getProperty(Const.CAVE_ELASTICSEARCH_PASSWORD);
        props.setProperty(Const.CAVE_ELASTICSEARCH_PASSWORD, password == null ? "" : password);
        if (this.connection == null) {
            synchronized (this) {
                if (this.connection == null) {
                    this.connection = createConnection(props);
                }
            }
        }
        return this.connection;
    }

    public Connection createConnection(Properties props) {
        return new ElasticSearchConnection(props);
    }

}

class ElasticSearchConnection implements Connection {

    private final Properties props;

    private HttpHost[] hosts = null;

    private RestHighLevelClient restClient;

    public ElasticSearchConnection(Properties props) {
        this.props = props;
        prepareArguments();
        prepareConnect();
    }

    private void prepareArguments() {
        String pHosts = this.props.getProperty(Const.CAVE_ELASTICSEARCH_HOST);
        String[] hostArray = pHosts.split(",");
        final HttpHost[] hosts = new HttpHost[hostArray.length];

        for (int idx = 0, len = hostArray.length; idx < len; idx++) {
            StringBuilder host = new StringBuilder(hostArray[idx].trim());
            if (host.length() == 0) {
                throw new DataSourceException("Illegal elasticsearch host list: " + pHosts);
            }
            if (host.indexOf(":") < 0) {
                host.append(":9200");
            }
            hosts[idx] = HttpHost.create(host.toString());
        }
        this.hosts = hosts;
    }

    private void prepareConnect() {
        // Create the low-level client
        // RestClient httpClient = RestClient.builder(this.hosts).build();

// Create the HLRC
//         this.restClient = new RestHighLevelClientBuilder(httpClient)
//                 .setApiCompatibilityMode(true)
//                 .build();
        String username = this.props.getProperty(Const.CAVE_ELASTICSEARCH_USERNAME);
        String password = this.props.getProperty(Const.CAVE_ELASTICSEARCH_PASSWORD);
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        if (!StringUtil.isEmpty(username) && !StringUtil.isEmpty(password)) {
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(username, password));
        }

        this.restClient = new RestHighLevelClient(RestClient.builder(this.hosts)
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        //线程设置
                        // httpAsyncClientBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(10).build());
                        return httpAsyncClientBuilder;
                    }
                }));

    }

    @Override
    public Session getSession() {
        return new ElasticSearchSession(this.props, this.restClient);
    }
}

class ElasticSearchSession extends PreparedSession {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchSession.class);

    /** Elasticsearch 6.x still requires a document type on the index API. */
    private static final String DOC_TYPE = "_doc";

    private static final ElasticSearchQDLDialectResolver DML_DIALECT = new ElasticSearchQDLDialectResolver();

    private final RestHighLevelClient restClient;

    private final Properties props;

    private final ElasticSearchQDLDialectResolver dialectResolver;

    private final ElasticSearchRvResolver rvResolver;

    public ElasticSearchSession(final Properties props, final RestHighLevelClient restClient) {
        this.props = props;
        this.restClient = restClient;
        this.dialectResolver = new ElasticSearchQDLDialectResolver();
        this.rvResolver = new ElasticSearchRvResolver();
    }

    @Override
    public Language lang() {
        return Language.ELASTIC_SEARCH;
    }

    @Override
    public ReturnValue<?> executeQuery0(String sql) {
        SelectStatement select = Cql.parseSelectStatement(sql);
        if (select.limit() != null && select.limit().fetch() == 0) {
            return new fun.langel.cql.rv.Rows();
        }
        Dialect<SearchRequest> dialect = this.dialectResolver.resolve(select);
        try {
            SearchResponse esResp = this.restClient.search(dialect.content(), RequestOptions.DEFAULT);
            return this.rvResolver.resolve(esResp, select);
        } catch (IOException e) {
            throw new DataSourceException("Failed to execute elasticsearch query: " + sql, e);
        }
    }

    @Override
    public Number executeUpdate0(String sql) {
        UpdateByQueryRequest request = updateRequest(Cql.parseUpdateStatement(sql));
        try {
            BulkByScrollResponse response = this.restClient.updateByQuery(request, RequestOptions.DEFAULT);
            ensureSuccess(response, "UPDATE");
            // Match MongoDB's affected-row semantics: every matched document,
            // including noops where the script left the source unchanged.
            return fun.langel.cql.rv.Number.of(response.getTotal());
        } catch (IOException e) {
            throw new DataSourceException("Failed to execute elasticsearch update: " + sql, e);
        }
    }

    @Override
    public Number executeInsert0(String sql) {
        List<IndexRequest> requests = insertRequests(Cql.parseInsertStatement(sql));
        try {
            if (requests.size() == 1) {
                IndexRequest request = requests.get(0);
                request.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
                this.restClient.index(request, RequestOptions.DEFAULT);
            } else {
                BulkResponse response = this.restClient.bulk(insertBulkRequest(requests), RequestOptions.DEFAULT);
                if (response.hasFailures()) {
                    throw new DataSourceException(
                            "Failed to insert into elasticsearch: " + response.buildFailureMessage());
                }
            }
            return fun.langel.cql.rv.Number.of(requests.size());
        } catch (IOException e) {
            throw new DataSourceException("Failed to execute elasticsearch insert: " + sql, e);
        }
    }

    @Override
    public Number executeDelete0(String sql) {
        DeleteByQueryRequest request = deleteRequest(Cql.parseDeleteStatement(sql));
        try {
            BulkByScrollResponse response = this.restClient.deleteByQuery(request, RequestOptions.DEFAULT);
            ensureSuccess(response, "DELETE");
            return fun.langel.cql.rv.Number.of(response.getDeleted());
        } catch (IOException e) {
            throw new DataSourceException("Failed to execute elasticsearch delete: " + sql, e);
        }
    }

    static BulkRequest insertBulkRequest(List<IndexRequest> requests) {
        BulkRequest bulk = new BulkRequest();
        // Per-item refresh policies are rejected by BulkRequest#validate.
        bulk.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        for (IndexRequest request : requests) {
            bulk.add(request);
        }
        return bulk;
    }

    static List<IndexRequest> insertRequests(InsertStatement statement) {
        String index = indexName(statement.table());
        List<Column> columns = statement.into();
        if (columns == null || columns.isEmpty()) {
            throw new UnsupportedOperationException("INSERT into elasticsearch requires an explicit column list.");
        }
        for (Column column : columns) {
            if (column.name().startsWith("_") && !"_id".equals(column.name())) {
                throw new UnsupportedOperationException(
                        "Elasticsearch metadata field cannot be inserted: " + column.name());
            }
        }
        List<List<Value>> rows = statement.values();
        if (rows == null || rows.isEmpty()) {
            throw new SqlException("INSERT requires at least one VALUES row.");
        }
        List<IndexRequest> requests = new ArrayList<>(rows.size());
        for (List<Value> row : rows) {
            if (row.size() != columns.size()) {
                throw new SqlException("INSERT column count (" + columns.size()
                        + ") does not match value count (" + row.size() + ").");
            }
            Map<String, Object> document = new LinkedHashMap<>();
            for (int idx = 0; idx < columns.size(); idx++) {
                document.put(columns.get(idx).name(), value(row.get(idx)));
            }
            requests.add(indexRequest(index, document));
        }
        return requests;
    }

    private static IndexRequest indexRequest(String index, Map<String, Object> document) {
        IndexRequest request = new IndexRequest(index, DOC_TYPE);
        Object id = document.remove("_id");
        if (id != null) {
            // SQL INSERT semantics: an explicitly supplied id must not overwrite
            // an existing document.
            request.id(String.valueOf(id));
            request.opType(DocWriteRequest.OpType.CREATE);
        }
        request.source(document);
        return request;
    }

    static UpdateByQueryRequest updateRequest(UpdateStatement statement) {
        String index = indexName(statement.table());
        List<Assignment> assignments = statement.assignments();
        if (assignments == null || assignments.isEmpty()) {
            throw new SqlException("UPDATE requires at least one SET assignment.");
        }
        StringBuilder script = new StringBuilder();
        Map<String, Object> params = new LinkedHashMap<>();
        for (int idx = 0; idx < assignments.size(); idx++) {
            Assignment assignment = assignments.get(idx);
            String field = assignment.column().name();
            if (field.startsWith("_")) {
                throw new UnsupportedOperationException(
                        "Elasticsearch metadata field cannot be updated: " + field);
            }
            script.append("ctx._source['")
                    .append(escapeScript(field))
                    .append("'] = params.p")
                    .append(idx)
                    .append(';');
            params.put("p" + idx, value(assignment.value()));
        }
        UpdateByQueryRequest request = new UpdateByQueryRequest(index);
        request.setQuery(DML_DIALECT.filter(statement.where()));
        request.setScript(new Script(ScriptType.INLINE, "painless", script.toString(), params));
        request.setRefresh(true);
        // SQL UPDATE applies to every matched row; do not abort the whole request
        // because one document is being written concurrently.
        request.setAbortOnVersionConflict(false);
        return request;
    }

    static DeleteByQueryRequest deleteRequest(DeleteStatement statement) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(indexName(statement.table()));
        request.setQuery(DML_DIALECT.filter(statement.where()));
        request.setRefresh(true);
        request.setAbortOnVersionConflict(false);
        return request;
    }

    static void ensureSuccess(BulkByScrollResponse response, String operation) {
        if (response.isTimedOut()
                || !response.getBulkFailures().isEmpty()
                || !response.getSearchFailures().isEmpty()
                || response.getReasonCancelled() != null) {
            throw new DataSourceException(operation + " failed in elasticsearch: "
                    + "timedOut=" + response.isTimedOut()
                    + ", bulkFailures=" + response.getBulkFailures()
                    + ", searchFailures=" + response.getSearchFailures()
                    + ", cancelled=" + response.getReasonCancelled());
        }
    }

    private static String indexName(Table table) {
        if (table == null || StringUtil.isEmpty(table.getName())) {
            throw new SqlException("Elasticsearch DML requires a table name.");
        }
        return StringUtil.stripQuote(table.getName()).toLowerCase(Locale.ROOT);
    }

    private static Object value(Value value) {
        return value == null || value.isNull() ? null : value.value();
    }

    private static String escapeScript(String value) {
        return value.replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
