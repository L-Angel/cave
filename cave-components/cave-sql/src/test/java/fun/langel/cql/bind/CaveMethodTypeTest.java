package fun.langel.cql.bind;

import fun.langel.cql.annotation.Select;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class CaveMethodTypeTest {

    public interface Demo {
        @Select(sql = "select a from t")
        List<Map<String, Object>> maps();

        @Select(sql = "select a from t")
        List<String> strings();
    }

    @Test
    public void parameterizedMapTypeIsReducedToItsRawType() throws Exception {
        CaveMethod.MethodSignature signature = new CaveMethod.MethodSignature(
                Demo.class.getMethod("maps"));

        Assert.assertEquals(Map.class, signature.actualType());
    }

    @Test
    public void simpleGenericTypeIsPreserved() throws Exception {
        CaveMethod.MethodSignature signature = new CaveMethod.MethodSignature(
                Demo.class.getMethod("strings"));

        Assert.assertEquals(String.class, signature.actualType());
    }
}
