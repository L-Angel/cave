package fun.langel.cql.node;

import fun.langel.cql.enums.Order;
import fun.langel.cql.util.StringUtil;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/26 14:38
 **/
public class Column implements Terminal, Expr, Node {

    private String name;

    private Function function;

    private final String alias;

    private Order order = Order.NONE;

    private Column(final String name, final String alias) {
        this.name = StringUtil.stripQuote(name);
        this.alias = alias;
    }

    private Column(final String name, final String alias, final Order order) {
        this.name = StringUtil.stripQuote(name);
        this.alias = alias;
        this.order = order;
    }

    private Column(final Function func, final String alias, final Order order) {
        this.function = func;
        this.alias = alias;
        this.order = order == null ? Order.NONE : order;
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

    public static Column of(final Function func, final String alias, final Order order) {
        return new Column(func, alias, order);
    }

    public static Column of(final Function func, final String alias) {
        return of(func, alias, Order.NONE);
    }

    public static Column of(final Function func) {
        return of(func, null);
    }

    @Override
    public String toString() {
        return StringUtil.isEmpty(this.alias) ? this.name : this.alias;
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

    public boolean isFunction() {
        return this.function != null;
    }

    public Function function() {
        return this.function;
    }

}
