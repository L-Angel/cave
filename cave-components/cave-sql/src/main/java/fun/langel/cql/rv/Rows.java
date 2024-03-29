package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/4 11:31 上午
 **/
public class Rows implements ReturnValue<List<Row>> {

    private final List<Row> rows = new LinkedList<>();

    private long size;

    public static final Rows EMPTY = new Rows();

    public Rows() {
        this(null);
    }

    public Rows(final List<Row> rows) {
        if (rows == null || rows.isEmpty()) {
            return;
        }
        this.rows.addAll(rows);
    }

    public void add(final Row row) {
        this.rows.add(row);
    }

    public void setSize(final long size) {
        this.size = size;
    }

    public long size() {
        if (this.size != 0) {
            return this.size;
        }
        if (this.rows == null || this.rows.isEmpty()) {
            return 0;
        }
        return this.rows.size();
    }

    @Override
    public List<Row> getValue() {
        return this.rows;
    }

    @Override
    public Object mapTo(Class<?> klass) throws MappingException {
        if (this.rows == null) {
            return null;
        }
        final List<Object> v = new LinkedList<>();
        for (Row row : this.rows) {
            v.add(row.mapTo(klass));
        }
        return v;
    }
}
