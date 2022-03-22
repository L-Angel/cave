package fun.langel.cql.resolve.sql;

import fun.langel.cql.node.NoneNode;
import fun.langel.cql.resolve.SqlResolver;
import org.apache.calcite.sql.SqlNode;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 15:33
 **/
public class DefaultSqlResolver implements SqlResolver<SqlNode, NoneNode> {
    @Override
    public NoneNode resolve(SqlNode select) {
        return NoneNode.EMPTY;
    }
}
