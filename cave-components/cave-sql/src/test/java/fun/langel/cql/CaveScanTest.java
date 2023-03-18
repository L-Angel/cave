package fun.langel.cql;

import fun.langel.cql.annotation.CaveScan;
import fun.langel.cql.annotation.Select;
import fun.langel.cql.cave.CaveDemo;
import fun.langel.cql.cave.Model;
import fun.langel.cql.cave.Model2;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.resolve.DialectResolver;
import fun.langel.cql.resolve.dialect.ElasticSearchQDLDialectResolver;
import fun.langel.cql.statement.SelectStatement;
import org.elasticsearch.action.search.SearchRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/9 21:05
 **/
@RunWith(SpringRunner.class)
@CaveScan(packages = {"fun.langel.cql.cave"},
        useDefaultElasticSearch = true)
public class CaveScanTest {

    static {
        System.setProperty("cave.elasticsearch.hosts", "127.0.0.1:9200");

    }

    @Autowired
    private CaveDemo caveDemo;

    @Test
    public void test_queryEs() {
        caveDemo.query("param_abc");
    }

    @Test
    public void test_queryEs_list() {
        // List<Model> r = caveDemo.queryList("value1");
        List<Model> r = caveDemo.queryListWithDefaultEs("value1");

        System.out.println(r);
    }

    @Test
    public void test_select_star() {
        // caveDemo.demoQuery("value2");
        caveDemo.demoQuery(null);
    }

    @Test
    public void test_select_count() {
        double count = caveDemo.demoCount();
        Assert.assertTrue(count > -2);
    }


    @Test
    public void test_select_es_parse() {
        final String sql = "select count(1) from es_activity where a_priority = null and csi_sku_code=null and a_type='2' and a_name=null and csi_title='快递箱2包装' and a_status=null and a_sub_type=null";
        int c = caveDemo.demoQuery();
        System.out.println(c);
    }

    @Test
    public void test_select_parse_es_dialect() {
        final DialectResolver<SelectStatement, SearchRequest> dialectResolver = new ElasticSearchQDLDialectResolver();

        final String sql = "select count(1) from es_activity where a_priority = null and csi_sku_code=null and a_type='2' and a_name=null and csi_title like null and a_status=null and a_sub_type=null" +
                " and status in (1,2,3)";
        SelectStatement select = Cql.parseSelectStatement(sql);
        Dialect<SearchRequest> dialect = dialectResolver.resolve(select);
        System.out.println(dialect.content().source());
    }

    @Test
    public void test_range_query() {

        final List<Integer> a = new LinkedList<Integer>();
        a.add(1);
        a.add(3);
        a.add(4);
        a.add(5);
        final List<String> c = new LinkedList<>();
        c.add("a");
        c.add("b");
        c.add("d");
        caveDemo.rangeQuery(c, a);
    }

    @Test
    public void test_range_query_with_null() {
        caveDemo.rangeQuery(null, null);
        final List<String> c = new LinkedList<>();

        caveDemo.rangeQuery(c, null);

    }

    @Test
    public void test_parse() {
        final String sql = "select count('fieldC') from demo_index where fieldc in (null) and fielda in (null)";
        SelectStatement statement = Cql.parseSelectStatement(sql);
        Assert.assertNotNull(statement);
    }

}
