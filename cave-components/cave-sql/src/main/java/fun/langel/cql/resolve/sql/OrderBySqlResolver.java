package fun.langel.cql.resolve.sql;

import fun.langel.cql.node.*;
import fun.langel.cql.resolve.SqlResolver;
import fun.langel.cql.util.LongUtil;
import org.apache.calcite.sql.SqlOrderBy;
import org.apache.calcite.sql.SqlSelect;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 15:31
 **/
public class OrderBySqlResolver implements SqlResolver<SqlOrderBy, Select> {

    private final SelectSqlResolver selectResolver;

    public OrderBySqlResolver() {
        this.selectResolver = new SelectSqlResolver();
    }

    @Override
    public Select resolve(SqlOrderBy orderBy) {
        Select select = (Select) selectResolver.resolve((SqlSelect) orderBy.query);
        List<Column> columns = new LinkedList<>();
        orderBy.orderList.forEach(ol -> columns.add(new Column(NodeKind.FIELD, ol.toString(), ol.toString(), Column.SortRule.NONE)));
        select.orderByWith(new OrderBy(columns));
        select.limitWith(new Limit(LongUtil.tryParse(orderBy.offset.toString()), LongUtil.tryParse(orderBy.fetch.toString())));
        return select;
    }
}
