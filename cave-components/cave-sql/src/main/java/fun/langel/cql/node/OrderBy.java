package fun.langel.cql.node;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 04:49
 **/
public class OrderBy implements Node {

    private LinkedList<Column> columns = new LinkedList<>();

    public OrderBy(List<Column> cols) {
        this.columns.addAll(cols);
    }

    public void addOrderBy(Column col) {
        this.columns.add(col);
    }

    public LinkedList<Column> getColumns() {
        return columns;
    }

    @Override
    public NodeKind kind() {
        return NodeKind.ORDER_BY;
    }
}
