package fun.langel.cql;

import fun.langel.cql.node.Expr;
import fun.langel.cql.node.GroupedExpr;
import fun.langel.cql.node.NotExpr;
import fun.langel.cql.node.operator.BetweenOperator;
import fun.langel.cql.node.operator.LogicalOperator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class CqlExpressionTest {

    @Test
    public void parenthesesAreParsedAndPreserved() {
        Expr where = where("select a from t where a = 1 and (b = 2 or c = 3)");

        Assert.assertEquals(LogicalOperator.AND, where.operator());
        Assert.assertTrue(where.right() instanceof GroupedExpr);
        Assert.assertEquals(LogicalOperator.OR, ((Expr) where.right()).operator());
    }

    @Test
    public void andHasHigherPrecedenceThanOr() {
        Expr where = where("select a from t where a = 1 or b = 2 and c = 3");

        Assert.assertEquals(LogicalOperator.OR, where.operator());
        Assert.assertEquals(LogicalOperator.AND, ((Expr) where.right()).operator());
    }

    @Test
    public void notIsParsed() {
        Expr where = where("select a from t where not (a = 1 and b = 2)");

        Assert.assertTrue(where instanceof NotExpr);
        Assert.assertTrue(((NotExpr) where).expression() instanceof GroupedExpr);
    }

    @Test
    public void doubleAmpersandIsAnd() {
        Expr where = where("select a from t where a = 1 && b = 2");

        Assert.assertEquals(LogicalOperator.AND, where.operator());
    }

    @Test
    public void notBetweenIsNegated() {
        Expr where = where("select a from t where a not between 1 and 2");

        Assert.assertTrue(where instanceof NotExpr);
        Assert.assertEquals(BetweenOperator.BETWEEN, ((NotExpr) where).expression().operator());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void xorFailsFast() {
        where("select a from t where a = 1 xor b = 2");
    }

    @Test
    public void distinctIsParsed() {
        Assert.assertTrue(Cql.parseSelectStatement("select distinct a from t").distinct());
    }

    @Test
    public void groupByIsParsed() {
        Assert.assertNotNull(Cql.parseSelectStatement("select a from t group by a").groupBy());
    }

    @Test
    public void havingIsParsed() {
        Assert.assertNotNull(Cql.parseSelectStatement("select a from t having a = 1").having());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void multipleTablesFailFast() {
        Cql.parseSelectStatement("select a from t, u");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void joinFailsFast() {
        Cql.parseSelectStatement("select a from t join u on t.a = u.b");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void fromSubqueryFailsFast() {
        Cql.parseSelectStatement("select a from (select b from u) x");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void inSubqueryFailsFast() {
        Cql.parseSelectStatement("select a from t where a in (select b from u)");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void selectExpressionFailsFast() {
        Cql.parseSelectStatement("select 1 from t");
    }

    @Test
    public void columnToColumnComparisonIsParsed() {
        Expr where = where("select a from t where a = b");

        Assert.assertTrue(where.right() instanceof fun.langel.cql.node.Column);
        Assert.assertEquals("b", ((fun.langel.cql.node.Column) where.right()).name());
    }

    @Test
    public void likeEscapeIsParsed() {
        Expr where = where("select a from t where a like '%x!_%' escape '!'");

        Assert.assertEquals(Character.valueOf('!'), where.escape());
    }

    @Test
    public void orderByOrdinalIsParsed() {
        Assert.assertEquals("1",
                Cql.parseSelectStatement("select a from t order by 1").orderBy().columns().get(0).name());
    }

    private Expr where(String sql) {
        Expr where = Cql.parseSelectStatement(sql).where();
        Assert.assertNotNull(where);
        return where;
    }
}
