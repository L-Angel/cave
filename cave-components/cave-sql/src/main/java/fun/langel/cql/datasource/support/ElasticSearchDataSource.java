package fun.langel.cql.datasource.support;


import fun.langel.cql.Cql;
import fun.langel.cql.Language;
import fun.langel.cql.constant.Const;
import fun.langel.cql.datasource.*;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.resolve.DialectResolver;
import fun.langel.cql.resolve.RvResolver;
import fun.langel.cql.resolve.dialect.ElasticSearchQDLDialectResolver;
import fun.langel.cql.resolve.rv.ElasticSearchRvResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.Props;
import fun.langel.cql.util.StringUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 17:07
 **/
public class ElasticSearchDataSource implements DataSource {

    private Connection connection;

    @Override
    public DataSourceType type() {
        return DataSourceType.ELASTIC_SEARCH;
    }

    @Override
    public Connection getConnection() {
        Properties props = new Properties();
        String host = StringUtil.isEmpty(Props.getProperty(Const.CAVE_ELASTICSEARCH_HOST)) ?
                Props.getProperty(Const.ES_HOST) : Props.getProperty(Const.CAVE_ELASTICSEARCH_HOST);
        props.setProperty(Const.CAVE_ELASTICSEARCH_HOST, host);
        String username = StringUtil.isEmpty(Props.getProperty(Const.CAVE_ELASTICSEARCH_USERNAME)) ?
                Props.getProperty(Const.ES_USERNAME) : Props.getProperty(Const.CAVE_ELASTICSEARCH_USERNAME);

        props.setProperty(Const.CAVE_ELASTICSEARCH_USERNAME, username == null ? "" : username);
        String password = StringUtil.isEmpty(Props.getProperty(Const.CAVE_ELASTICSEARCH_PASSWORD)) ?
                Props.getProperty(Const.ES_PASSWORD) : Props.getProperty(Const.CAVE_ELASTICSEARCH_PASSWORD);
        props.setProperty(Const.CAVE_ELASTICSEARCH_PASSWORD, password == null ? "" : password);
        if (this.connection == null) {
            synchronized (ElasticSearchDataSource.class) {
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
            StringBuilder host = new StringBuilder(hostArray[idx]);
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

    private final RestHighLevelClient restClient;

    private final Properties props;

    private final DialectResolver<SelectStatement, SearchRequest> dialectResolver;

    private final RvResolver<SearchResponse> rvResolver;

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
        Dialect<SearchRequest> dialect = this.dialectResolver.resolve(select);
        try {
            SearchResponse esResp = this.restClient.search(dialect.content(), RequestOptions.DEFAULT);
            return this.rvResolver.resolve(esResp, select.columns());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Number executeUpdate0(String sql) {
        return null;
    }

    @Override
    public Number executeDelete0(String sql) {
        return null;
    }
}
