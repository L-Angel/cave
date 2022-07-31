package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/31 20:37
 **/
public class From implements Node {

    private Expr where;

    private Table table;

    public From(Table table, Expr where) {
        this.table = table;
        this.where = where;
    }

    public Table table() {
        return this.table;
    }

    public Expr where() {
        return this.where;
    }
}
