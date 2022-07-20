package fun.langel.cql.calcite;

import fun.langel.cql.Cql;
import fun.langel.cql.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/17 19:05
 **/
@RunWith(JUnit4.class)
public class CqlTest {

    @Test
    public void testParse() throws IOException {
        final String sql = "select field1 from table1 where field1 = 'a';";
        Cql.parse(sql, Language.QDL_ELASTIC_SEARCH);
    }

    @Test
    public void testParse_testCondition1() throws IOException {
        final String sql = "select field1 as f1 from table1 where f1='b'";
        Cql.parse(sql, Language.QDL_ELASTIC_SEARCH);
    }
}
