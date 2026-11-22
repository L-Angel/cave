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
        if (value != null && (isQuoted(value, '\'') || isQuoted(value, '"'))) {
            this.value = unescape(value.substring(1, value.length() - 1));
            this.type = Type.STRING;
        } else if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
            this.value = Boolean.parseBoolean(value);
            this.type = Type.BOOLEAN;
        } else if (NumberUtil.isNumber(value)) {
            this.value = value;
            this.type = Type.NUMBER;
        } else {
            this.value = value;
            this.type = Type.STRING;
        }
        if (this.value instanceof String && "?".equalsIgnoreCase((String) this.value)) {
            this.type = Type.PARAM;
        }
        this.isNull = isNull;
        this.notNull = notNull;
    }

    private static boolean isQuoted(final String value, final char quote) {
        return value.length() >= 2 && value.charAt(0) == quote && value.charAt(value.length() - 1) == quote;
    }

    public static Value of(final String value) {
        return new Value(value, "null".equalsIgnoreCase(value), !"null".equalsIgnoreCase(value));
    }

    /**
     * Reverses the escaping applied when parameter values are interpolated into
     * a CQL statement (see PreparedSession#quote). Handles both backslash escapes
     * and doubled single quotes.
     */
    private static String unescape(final String s) {
        if (s.indexOf('\\') < 0 && s.indexOf("''") < 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (int idx = 0; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            if (c == '\\' && idx + 1 < s.length()) {
                char next = s.charAt(++idx);
                switch (next) {
                    case '0':
                        sb.append('\0');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    default:
                        sb.append(next);
                }
            } else if (c == '\'' && idx + 1 < s.length() && s.charAt(idx + 1) == '\'') {
                sb.append('\'');
                idx++;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
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
        NUMBER,
        BOOLEAN;
    }
}
