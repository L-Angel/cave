package fun.langel.cql.statement;

import fun.langel.cql.node.*;

import java.util.Collections;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/31 20:44
 **/
public interface SelectStatement extends Statement {

    default List<Column> columns() {
        return Collections.emptyList();
    }

    Expr where();

    List<Table> tables();

    default Limit limit() {
        return null;
    }

    default OrderBy orderBy() {
        return null;
    }

    default GroupBy groupBy() {
        return null;
    }
}
