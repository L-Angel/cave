package fun.langel.cql.node.operator;

import fun.langel.cql.util.StringUtil;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/28 20:12
 **/
public enum RelOperator implements Operator {

    NONE,
    IS,
    EQUAL,
    NOT_EQUAL,
    GREATER,
    GREATER_OR_EQUALS,
    LESS,
    LESS_OR_EQUALS;

    public static RelOperator of(String o) {
        if (StringUtil.isEmpty(o)) {
            return NONE;
        }
        o = StringUtil.trim(o);
        if ("is".equalsIgnoreCase(o)) {
            return IS;
        }
        if ("=".equalsIgnoreCase(o)) {
            return EQUAL;
        }
        if ("<>".equalsIgnoreCase(o) || "!=".equalsIgnoreCase(o)) {
            return NOT_EQUAL;
        }
        if ("<=".equalsIgnoreCase(o)) {
            return LESS_OR_EQUALS;
        }
        if (">=".equalsIgnoreCase(o)) {
            return GREATER_OR_EQUALS;
        }
        if (">".equalsIgnoreCase(o)) {
            return GREATER;
        }
        if ("<".equalsIgnoreCase(o)) {
            return LESS;
        }
        return NONE;
    }
}
