package fun.langel.cql;

import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.parameter.ParameterResolver;
import fun.langel.cql.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/17 15:20
 **/
@RunWith(JUnit4.class)
public class ParameterResolverTest {


    @Test
    public void resolve() {
        final String sql = "select * from table1 where field1 = ${value1} and field2 = ${value2} " +
                " and gmt_created > ${value3}";
        // ParameterResolver resolver = new ParameterResolver();
        // Pair<String, List<Parameter>> pair = resolver.resolve(sql);
        //
        // System.out.println(pair.left());
        // System.out.println(pair.right());
    }

}
