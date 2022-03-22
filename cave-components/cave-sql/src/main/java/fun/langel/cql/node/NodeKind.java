package fun.langel.cql.node;

import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlNode;

import java.util.EnumSet;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 14:51
 **/
public enum NodeKind {

    SELECT,

    UPDATE,

    DELETE,

    INSERT,

    NONE,

    BETWEEN,

    LIMIT,

    ORDER_BY,

    FIELD,

    COUNT,

    AVG,

    AND,

    OR,

    LESS_THAN("<"),

    LESS_THAN_OR_EQUAL("<="),

    GREATER_THAN(">"),

    GREATER_THAN_OR_EQUAL(">="),

    EQUALS("="),

    NOT_EQUALS("<>"),

    LIKE,

    IN,

    NOT_IN,

    VALUE;


    public static final EnumSet<NodeKind> COMPARISON = EnumSet.of(LESS_THAN, LESS_THAN_OR_EQUAL, GREATER_THAN, GREATER_THAN_OR_EQUAL, EQUALS, NOT_EQUALS);

    private String sql;

    NodeKind() {
        this.sql = null;
    }

    NodeKind(final String sql) {
        this.sql = sql;
    }

    public String sql() {
        return this.sql;
    }


    public static NodeKind of(final SqlKind sk) {
        for (NodeKind nk : values()) {
            if (nk.name().equalsIgnoreCase(sk.lowerName)) {
                return nk;
            }
        }
        return NONE;
    }

    public static NodeKind of(final SqlNode node) {
        for (NodeKind nk : values()) {
            if (nk.name().equalsIgnoreCase(node.getKind().lowerName)) {
                return nk;
            }
        }
        return NONE;
    }
}
