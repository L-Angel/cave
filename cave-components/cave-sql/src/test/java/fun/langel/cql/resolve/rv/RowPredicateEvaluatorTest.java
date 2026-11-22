package fun.langel.cql.resolve.rv;

import fun.langel.cql.Cql;
import fun.langel.cql.node.Expr;
import fun.langel.cql.rv.Number;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.String;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class RowPredicateEvaluatorTest {

    @Test
    public void evaluatesNumericHavingComparison() {
        Expr having = Cql.parseSelectStatement(
                "select a, count(*) as c from t group by a having c > 1").having();

        Row row = new Row();
        row.put("a", String.of("x"));
        row.put("c", Number.of(2));
        Assert.assertTrue(RowPredicateEvaluator.evaluate(row, having));

        row.put("c", Number.of(1));
        Assert.assertFalse(RowPredicateEvaluator.evaluate(row, having));
    }

    @Test
    public void evaluatesLogicalHavingExpression() {
        Expr having = Cql.parseSelectStatement(
                "select a, count(*) as c, avg(x) as ax from t group by a "
                        + "having c > 1 and ax >= 10").having();

        Row row = new Row();
        row.put("c", Number.of(2));
        row.put("ax", Number.of(12.5));
        Assert.assertTrue(RowPredicateEvaluator.evaluate(row, having));

        row.put("ax", Number.of(9));
        Assert.assertFalse(RowPredicateEvaluator.evaluate(row, having));
    }

    @Test
    public void evaluatesGroupKeyHavingComparison() {
        Expr having = Cql.parseSelectStatement(
                "select a, count(*) as c from t group by a having a = 'x'").having();

        Row row = new Row();
        row.put("a", String.of("x"));
        row.put("c", Number.of(1));
        Assert.assertTrue(RowPredicateEvaluator.evaluate(row, having));
    }
}
