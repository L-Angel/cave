package fun.langel.cql.rv;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/4 11:31 上午
 **/
public class Rows implements ReturnValue<List<Row>> {

    private final List<Row> rows = new LinkedList<>();


    public Rows() {
        this(null);
    }

    public Rows(final List<Row> rows) {
        if (rows == null || rows.isEmpty()) {
            return;
        }
        this.rows.addAll(rows);
    }

    @Override
    public List<Row> getValue() {
        return this.rows;
    }
}
