package fun.langel.cql.bind;

import fun.langel.cql.annotation.Cave;
import fun.langel.cql.spring.Configuration;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class CaveProxyTest {

    @Cave
    public interface Demo {
        default String hello(String name) {
            return "hello " + name;
        }
    }

    private Demo demo() {
        return new CaveProxyFactory<Demo>(new Configuration(), Demo.class).newInstance();
    }

    @Test
    public void defaultMethodWorksOnCurrentJvm() {
        Assert.assertEquals("hello cave", demo().hello("cave"));
    }

    @Test
    public void objectMethodsFollowProxyIdentity() {
        Demo demo = demo();

        Assert.assertTrue(demo.toString().contains("Cave proxy"));
        Assert.assertTrue(demo.equals(demo));
        Assert.assertFalse(demo.equals(demo()));
        Assert.assertEquals(demo.hashCode(), demo.hashCode());
    }
}
