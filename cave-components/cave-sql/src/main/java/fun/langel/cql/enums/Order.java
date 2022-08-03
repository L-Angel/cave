package fun.langel.cql.enums;

import fun.langel.cql.util.StringUtil;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/1 20:38
 **/
public enum Order {

    NONE("none"),

    ASC("asc"),

    DESC("desc");

    private String v;

    Order(final String v) {
        this.v = v;
    }

    public String value() {
        return this.v;
    }

    public static Order of(final String v) {
        if (StringUtil.isEmpty(v)) {
            return DESC;
        }
        for (Order o : values()) {
            if (o.value().equalsIgnoreCase(v)) {
                return o;
            }
        }
        return DESC;
    }
}
