package fun.langel.cql.node;

import fun.langel.cql.util.StringUtil;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 19:57
 **/
public class Value implements Node {

    private String v;

    public Value(String v) {
        this.v = StringUtil.strip(v, "'");
    }

    public String value() {
        return this.v;
    }

    @Override
    public NodeKind kind() {
        return NodeKind.VALUE;
    }
}
