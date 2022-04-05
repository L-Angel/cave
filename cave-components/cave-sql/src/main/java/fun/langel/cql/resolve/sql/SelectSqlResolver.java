package fun.langel.cql.resolve.sql;

import fun.langel.cql.node.*;
import fun.langel.cql.resolve.SqlResolver;
import fun.langel.cql.util.StringUtil;
import org.apache.calcite.sql.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 15:27
 **/
public class SelectSqlResolver implements SqlResolver<SqlSelect, Select> {


    @Override
    public Select resolve(SqlSelect select) {

        return new Select(resolveColumns(select.getSelectList()), select.getFrom().toString(),
                resolveConditionTree((SqlBasicCall) select.getWhere()), null, null,
                resolveColumns(select.getGroup()), resolveConditionTree((SqlBasicCall) select.getHaving()));
    }

    private List<Column> resolveColumns(SqlNodeList nodes) {
        List<Column> cols = new LinkedList<>();
        nodes.forEach(s -> {
            String[] f = s.toString().split("AS");
            cols.add(new Column(f[0], f.length == 2 ? f[1] : f[0]));

        });
        return cols;
    }

    private Node resolveConditionTree(SqlBasicCall bc) {
        SqlKind sk = bc.getKind();
        if (sk == SqlKind.OR || sk == SqlKind.AND) {
            return new BinaryOpNode(NodeKind.of(sk), resolveConditionTree((SqlBasicCall) bc.getOperands()[0]),
                    resolveConditionTree((SqlBasicCall) bc.getOperands()[1]));
        } else if (sk == SqlKind.LESS_THAN || sk == SqlKind.LESS_THAN_OR_EQUAL
                || sk == SqlKind.GREATER_THAN || sk == SqlKind.GREATER_THAN_OR_EQUAL
                || sk == SqlKind.EQUALS) {
            String name = StringUtil.wrap(bc.getOperands()[0]);
            return new BinaryOpNode(NodeKind.of(sk), Column.of(name), new Value(StringUtil.wrap(bc.getOperands()[1])));
        } else if (sk == SqlKind.BETWEEN) {
            String name = StringUtil.wrap(bc.getOperands()[0]);
            return new Between(Column.of(name), StringUtil.wrap(bc.getOperands()[1]), StringUtil.wrap(bc.getOperands()[2]));
        } else if (sk == SqlKind.IN || sk == SqlKind.NOT_IN) {
            String name = StringUtil.wrap(bc.getOperands()[0]);
            return Range.of(NodeKind.of(sk), Column.of(name), (SqlNodeList) bc.getOperands()[1]);
        }
        return NoneNode.EMPTY;
    }
}
