package fun.langel.cql.node;

import fun.langel.cql.util.NumberUtil;
import org.elasticsearch.common.Numbers;

import java.text.NumberFormat;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/28 21:29
 **/
public class Value implements Terminal, Node, Expr {

    private Type type;

    private Object value;

    private boolean isNull;

    private boolean notNull;


    public Value(final String value, boolean isNull, boolean notNull) {
        if (value != null && value.startsWith("'") && value.endsWith("'")) {
            this.value = value.substring(1, value.length() - 1);
            this.type = Type.STRING;
        } else if (NumberUtil.isNumber(value)) {
            this.value = value;
            this.type = Type.NUMBER;
        } else {
            this.value = value;
            this.type = Type.STRING;
        }
        if ("?".equalsIgnoreCase((String) this.value)) {
            this.type = Type.PARAM;
        }
        this.isNull = isNull;
        this.notNull = notNull;
    }

    public static Value of(final String value) {
        return new Value(value, false, false);
    }

    public boolean isNull() {
        return this.isNull;
    }

    public boolean notNull() {
        return this.notNull;
    }


    public Object value() {
        if (this.type == Type.NUMBER) {
            if (NumberUtil.isLongOrInteger((String) this.value)) {
                return Long.parseLong((String) this.value);
            }
            return Double.parseDouble((String) this.value);
        }
        return this.value;
    }

    public Type type() {
        return this.type;
    }


    public static enum Type {
        NONE,
        STRING,
        PARAM,
        NUMBER;
    }
}
