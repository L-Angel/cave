package fun.langel.cql.node;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/31 20:37
 **/
public class From implements Node {

    private Expr where;

    private List<Table> tables;

    public From(List<Table> tables, Expr where) {
        this.tables = tables;
        this.where = where;
    }

    public List<Table> tables() {
        return this.tables;
    }

    public Expr where() {
        return this.where;
    }
}
