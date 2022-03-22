package fun.langel.cql.node;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 04:48
 **/
public class Select implements Node {

    private final List<Column> columns;

    private final Node where;

    private String from;

    private OrderBy orderBy;

    private Limit limit;

    public Select(List<Column> columns, final String from, Node where, OrderBy orderBy, Limit limit) {
        this.columns = columns;
        this.where = where;
        this.from = from;
        this.orderBy = orderBy;
        this.limit = limit;
    }

    public List<Column> columns() {
        return this.columns;
    }

    public Node where() {
        return this.where;
    }

    public OrderBy orderBy() {
        return this.orderBy;
    }

    public Select orderByWith(OrderBy ob) {
        this.orderBy = ob;
        return this;
    }

    public Limit limit() {
        return this.limit;
    }

    public Select limitWith(final Limit limit) {
        this.limit = limit;
        return this;
    }

    public String from() {
        return from;
    }

    @Override
    public NodeKind kind() {
        return NodeKind.SELECT;
    }
}
