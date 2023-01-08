package fun.langel.cql;

import fun.langel.cql.dialect.Dialect;
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
        Cql.parse(sql, Language.ELASTIC_SEARCH);
    }

    @Test
    public void testParse_testCondition1() throws IOException {
        final String sql = "select field1 as f1 from table1 as ta,table2 t2 where f1='b' and f2 is not null and f3 <> 'c' and field4 between 1 and 2 and field5  in (6,7)  and field6 not in (10,11) and field7 like '%abc%' and field8 = ${param1} order by f1 desc ,f2 asc limit 0,100";
        Cql.parse(sql, Language.ELASTIC_SEARCH);
    }


    @Test
    public void test_demoAvg() {
        final String sql = "select avg('field1') as n_field from demo_index where c_exists('SafeInventory@lowerLimit')";
        Cql.parse(sql, Language.ELASTIC_SEARCH);
    }

    @Test
    public void test_multi_process() {
        final String sql = "select avg('field1') as n_field1,c_keyvalue(field2,'key1') as n_field2 " +
                "from demo_index where c_exists('SafeInventory@lowerLimit')";
        Dialect dialect = Cql.parse(sql, Language.ELASTIC_SEARCH);
        System.out.println(dialect);
    }




    public static class Model1 {
        private String v;

        public Model1(String v) {
            this.v = v;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }
}
