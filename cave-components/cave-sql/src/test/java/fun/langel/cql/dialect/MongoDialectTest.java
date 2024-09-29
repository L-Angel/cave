package fun.langel.cql.dialect;

import fun.langel.cql.annotation.CaveScan;
import fun.langel.cql.annotation.DataSource;
import fun.langel.cql.cave.CaveDemo;
import fun.langel.cql.cave.Model;
import fun.langel.cql.datasource.support.MongoDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/26 19:13
 **/
@RunWith(SpringRunner.class)
@CaveScan(packages = {"fun.langel.cql.cave"},
        datasource = {
                @DataSource(name = "mongodb", klass = MongoDataSource.class)
        },

        useDefaultElasticSearch = false)
public class MongoDialectTest {
    static {
        System.setProperty("cave.mongodb.host", "mongodb://127.0.0.1:27017");
        System.setProperty("cave.mongodb.dbname", "demo");
    }

    @Autowired
    private CaveDemo caveDemo;

    @Test
    public void test_queryMongo() {
        // List<Model> models = caveDemo.queryDemoMongo();
        // System.out.println(models);

        List<Model> models2 = caveDemo.queryDemoMongo2("value");
        System.out.println(models2);
    }

}
