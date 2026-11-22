package fun.langel.cql.parameter;

import fun.langel.cql.bind.Arg;
import fun.langel.cql.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class JdbcParameterResolverTest {

    private final ParameterResolver resolver = new ParameterResolver();

    @Test
    public void dollarCollectionIsExpandedToPlaceholders() {
        Arg[] args = {new Arg("ids", "ids", Arrays.asList(1, 2, 3), List.class)};

        Pair<String, List<Parameter>> pair = resolver.resolveForJdbc(
                "select * from t where id in (${ids})", args);

        Assert.assertEquals("select * from t where id in (?,?,?)", pair.left());
        Assert.assertEquals(3, pair.right().size());
        Assert.assertEquals(1, pair.right().get(0).getValue());
        Assert.assertEquals(2, pair.right().get(1).getValue());
        Assert.assertEquals(3, pair.right().get(2).getValue());
        Assert.assertEquals(0, pair.right().get(0).pos());
        Assert.assertEquals(2, pair.right().get(2).pos());
    }

    @Test
    public void emptyCollectionBindsOneNull() {
        Arg[] args = {new Arg("ids", "ids", Collections.emptyList(), List.class)};

        Pair<String, List<Parameter>> pair = resolver.resolveForJdbc(
                "select * from t where id in (${ids})", args);

        Assert.assertEquals("select * from t where id in (?)", pair.left());
        Assert.assertEquals(1, pair.right().size());
        Assert.assertNull(pair.right().get(0).getValue());
    }

    @Test
    public void hashPlaceholderStaysASingleBoundValue() {
        Arg[] args = {new Arg("ids", "ids", Arrays.asList(1, 2), List.class)};

        Pair<String, List<Parameter>> pair = resolver.resolveForJdbc(
                "select * from t where id = #{ids}", args);

        Assert.assertEquals("select * from t where id = ?", pair.left());
        Assert.assertEquals(1, pair.right().size());
        Assert.assertEquals(Arrays.asList(1, 2), pair.right().get(0).getValue());
    }

    @Test
    public void mixedPlaceholdersKeepBindingOrder() {
        Arg[] args = {
                new Arg("a", "a", 7, Integer.class),
                new Arg("ids", "ids", Arrays.asList("x", "y"), List.class),
                new Arg("c", "c", "z", String.class)
        };

        Pair<String, List<Parameter>> pair = resolver.resolveForJdbc(
                "select * from t where a = ${a} and id in (${ids}) and c = #{c}", args);

        Assert.assertEquals("select * from t where a = ? and id in (?,?) and c = ?", pair.left());
        Assert.assertEquals(4, pair.right().size());
        Assert.assertEquals(7, pair.right().get(0).getValue());
        Assert.assertEquals("x", pair.right().get(1).getValue());
        Assert.assertEquals("y", pair.right().get(2).getValue());
        Assert.assertEquals("z", pair.right().get(3).getValue());
    }
}
