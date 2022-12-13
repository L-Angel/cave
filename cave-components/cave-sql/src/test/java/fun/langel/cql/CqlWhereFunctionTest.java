package fun.langel.cql;

import fun.langel.cql.statement.SelectStatement;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/12 21:53
 **/
public class CqlWhereFunctionTest {

    @Test
    public void testParseSelect_cqlWhereFunction() {
        String sql = "select fielda from tablea where c_exists('fieldb') and fieldc > fieldd and c_exists('fielde')";

        SelectStatement selectStatement = Cql.parseSelectStatement(sql);
        Assert.assertNotNull(selectStatement);

    }

    @Test
    public void testCqlWhereFunction_cscript() {
        String sql = "select fielda from tablea where c_exists('fieldb') and c_script(`doc['SafeInventory@lowerLimit'].value>doc['available_quantity'].value`)";
        SelectStatement selectStatement = Cql.parseSelectStatement(sql);
        Assert.assertNotNull(selectStatement);

    }
}
