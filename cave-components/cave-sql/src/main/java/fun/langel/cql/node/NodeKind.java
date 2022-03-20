package fun.langel.cql.node;

import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlOrderBy;

import java.util.Objects;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 14:51
 **/
public class NodeKind {

    public static NodeKind SELECT = new NodeKind("select", false);

    public static NodeKind UPDATE = new NodeKind("select", false);

    public static NodeKind DELETE = new NodeKind("select", false);

    public static NodeKind INSERT = new NodeKind("select", false);

    public static NodeKind NONE = new NodeKind("none", false);

    private final String kind;

    private final boolean ordered;

    public NodeKind(final String kind,
                    final boolean ordered) {
        this.kind = kind;
        this.ordered = ordered;
    }


    public String kindName() {
        return this.kind;
    }

    public boolean ordered() {
        return this.ordered;
    }

    public static NodeKind of(final SqlNode node) {
        if (node.getKind() == SqlKind.SELECT) {
            return new NodeKind("select", false);
        }
        if (node.getKind() == SqlKind.ORDER_BY) {
            SqlOrderBy sob = (SqlOrderBy) node;
            return new NodeKind("select", true);
        }
        if (node.getKind() == SqlKind.INSERT) {
            return new NodeKind("insert", false);
        }
        if (node.getKind() == SqlKind.UPDATE) {
            return new NodeKind("update", false);
        }
        if (node.getKind() == SqlKind.DELETE) {
            return new NodeKind("delete", false);
        }
        return NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeKind nodeKind = (NodeKind) o;
        return Objects.equals(kind, nodeKind.kind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind);
    }
}
