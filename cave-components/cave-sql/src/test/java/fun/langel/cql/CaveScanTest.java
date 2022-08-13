package fun.langel.cql;

import fun.langel.cql.annotation.CaveScan;
import fun.langel.cql.annotation.DataSource;
import fun.langel.cql.cave.CaveDemo;
import fun.langel.cql.cave.Model;
import fun.langel.cql.datasource.support.ElasticSearchDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/9 21:05
 **/
@RunWith(SpringRunner.class)
@CaveScan(packages = {"fun.langel.cql.cave"},
        datasource = {@DataSource(name = "defaultEs", klass = ElasticSearchDataSource.class, priority = 1)})
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
        List<Model> r = caveDemo.queryList("value1");

        System.out.println(r);
    }
}
