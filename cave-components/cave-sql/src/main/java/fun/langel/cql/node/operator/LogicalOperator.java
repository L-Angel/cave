package fun.langel.cql.node.operator;

import fun.langel.cql.util.StringUtil;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/27 14:39
 **/
public enum LogicalOperator implements Operator {

    NONE,
    AND,
    OR;

    public static LogicalOperator of(String o) {
        if (StringUtil.isEmpty(o)) {
            return NONE;
        }
        o = StringUtil.trim(o);
        if ("and".equalsIgnoreCase(o)) {
            return AND;
        }
        if ("or".equalsIgnoreCase(o) || "||".equalsIgnoreCase(o)) {
            return OR;
        }

        return NONE;
    }

}
