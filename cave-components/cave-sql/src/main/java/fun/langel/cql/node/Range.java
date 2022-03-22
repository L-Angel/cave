package fun.langel.cql.node;

import fun.langel.cql.util.StringUtil;
import org.apache.calcite.sql.SqlLiteral;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlNodeList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/22 11:35
 **/
public class Range implements Node {

    private NodeKind kind;

    private Column column;

    private List<Object> rangeValues = new LinkedList<>();

    private Range(NodeKind kind, Column column, Object... values) {
        this.kind = kind;
        this.column = column;
        this.addValues(values);
    }

    public static Range of(NodeKind kind, Column column, SqlNodeList values) {
        List<Object> vs = new LinkedList<>();
        for (SqlNode v : values) {
            vs.add(((SqlLiteral) v).toValue());
        }
        return new Range(kind, column, vs);
    }

    public static Range of(NodeKind kind, Column column, Object... values) {
        return new Range(kind, column, values);
    }

    public static Range in(Column column) {
        return new Range(NodeKind.IN, column);
    }

    public static Range notIn(Column column) {
        return new Range(NodeKind.NOT_IN, column);
    }

    public void addValues(Object... values) {
        if (values == null) {
            return;
        }
        Arrays.asList(values).forEach(v -> {
            if (v == null) {
                return;
            }
            if (v instanceof String) {
                rangeValues.add(StringUtil.strip((String) v, "'"));
            } else {
                rangeValues.add(v);
            }
        });
    }

    public Column column() {
        return this.column;
    }

    public List<Object> values() {
        return this.rangeValues;
    }

    @Override
    public NodeKind kind() {
        return this.kind;
    }
}
