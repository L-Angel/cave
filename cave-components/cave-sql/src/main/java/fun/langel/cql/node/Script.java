package fun.langel.cql.node;

import fun.langel.cql.util.StringUtil;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/12 22:46
 **/
public class Script implements Node {

    private final String raw;

    private Script(final String raw) {
        this.raw = StringUtil.stripQuote(raw);
    }

    public static Script of(final String script) {
        return new Script(script);
    }

    public String raw() {
        return this.raw;
    }

    @Override
    public String toString() {
        return this.raw;
    }
}
