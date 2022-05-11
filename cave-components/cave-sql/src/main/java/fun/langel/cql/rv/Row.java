package fun.langel.cql.rv;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/22 8:35 下午
 **/
public class Row implements ReturnValue<Map<String, Object>> {

    private final Map<String, Object> columns = new HashMap<>();

    public Row() {
        this(null);
    }

    public Row(final Map<String, Object> cols) {
        if (cols == null || cols.isEmpty()) {
            return;
        }
        this.columns.putAll(cols);
    }

    public void put(final String k, Object v) {
        this.columns.put(k, v);
    }

    @Override
    public Map<String, Object> getValue() {
        return this.columns;
    }
}
