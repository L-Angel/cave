package fun.langel.cql.dialect;

import fun.langel.cql.annotation.CaveScan;
import fun.langel.cql.cave.CaveDemo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/26 19:13
 **/
@RunWith(SpringRunner.class)
@CaveScan(packages = {"fun.langel.cql.cave"},
        useDefaultElasticSearch = true)
public class MongoDialectTest {
    static {
        System.setProperty("cave.mongo.hosts", "127.0.0.1:27017");
        System.setProperty("cave.mongo.dbname", "demo");
    }

    @Autowired
    private CaveDemo caveDemo;

    public void test_queryMongo() {

    }

}
