package fun.langel.cql.node;

import java.util.Arrays;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/1 21:42
 **/
public class OrderBy implements Node {

    private List<Column> columns;

    private OrderBy(final List<Column> columns) {
        this.columns = columns;
    }

    public static OrderBy of(final List<Column> columns) {
        return new OrderBy(columns);
    }

    public static OrderBy of(Column... columns) {
        return of(Arrays.asList(columns));
    }

    public List<Column> columns() {
        return this.columns;
    }
}
