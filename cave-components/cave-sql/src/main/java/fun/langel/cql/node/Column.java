package fun.langel.cql.node;

import fun.langel.cql.enums.Order;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/26 14:38
 **/
public class Column implements Terminal, Expr, Node {

    private final String name;

    private final String alias;

    private Order order = Order.NONE;

    private Column(final String name, final String alias) {
        this.name = name;
        this.alias = alias;
    }

    private Column(final String name, final String alias, final Order order) {
        this.name = name;
        this.alias = alias;
        this.order = order;
    }

    public static Column of(final String name) {
        return new Column(name, null);
    }

    public static Column of(final String name, final String alias) {
        return new Column(name, alias);
    }

    public static Column of(final String name, final String alias, final Order order) {
        return new Column(name, alias, order);
    }


    public String name() {
        return this.name;
    }

    public String alias() {
        return this.alias;
    }

    public Order order() {
        return this.order;
    }
}
