/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql;

import fun.langel.cql.bind.Arg;
import fun.langel.cql.exception.SqlException;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.parameter.ParameterResolver;
import fun.langel.cql.util.Pair;
import org.junit.Assert;
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

    private final ParameterResolver resolver = new ParameterResolver();

    @Test
    public void resolvesByParamAlias() {
        Arg[] args = {new Arg("arg0", "value1", "a", String.class)};

        Pair<String, List<Parameter>> pair = resolver.resolve(
                "select * from t where field1 = #{value1}", args);

        Assert.assertEquals("select * from t where field1 = ?", pair.left());
        Assert.assertEquals(1, pair.right().size());
        Assert.assertEquals("a", pair.right().get(0).getValue());
    }

    @Test
    public void resolvesByParameterNameWhenNoAliasIsPresent() {
        Arg[] args = {new Arg("value2", null, 2, Integer.class)};

        Pair<String, List<Parameter>> pair = resolver.resolve(
                "select * from t where field2 = ${value2}", args);

        Assert.assertEquals(Integer.valueOf(2), pair.right().get(0).getValue());
    }

    @Test
    public void explicitNullValueIsAllowed() {
        Arg[] args = {new Arg("value1", null, null, String.class)};

        Pair<String, List<Parameter>> pair = resolver.resolve(
                "select * from t where field1 = #{value1}", args);

        Assert.assertNull(pair.right().get(0).getValue());
    }

    @Test
    public void noPlaceholdersProducesEmptyPair() {
        Pair<String, List<Parameter>> pair = resolver.resolve(
                "select * from t", new Arg[]{new Arg("value1", null, "a", String.class)});

        Assert.assertEquals("select * from t", pair.left());
        Assert.assertTrue(pair.right().isEmpty());
    }

    @Test
    public void missingArgumentFailsFast() {
        try {
            resolver.resolve("select * from t where field1 = #{value1}",
                    new Arg[]{new Arg("arg0", null, "a", String.class)});
            Assert.fail("expected SqlException for an unmatched placeholder");
        } catch (SqlException ex) {
            Assert.assertTrue(ex.getMessage().contains("value1"));
            Assert.assertTrue(ex.getMessage().contains("arg0"));
            Assert.assertTrue(ex.getMessage().contains("-parameters"));
        }
    }

    @Test(expected = SqlException.class)
    public void placeholdersWithoutAnyArgumentsFailFast() {
        resolver.resolve("select * from t where field1 = #{value1}", new Arg[0]);
    }
}
