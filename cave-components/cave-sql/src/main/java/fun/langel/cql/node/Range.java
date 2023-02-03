package fun.langel.cql.node;

import fun.langel.cql.util.ListUtil;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/2 14:22
 **/
public class Range implements Terminal, Node, Expr {

    private List<Value> values;

    private Value begin;

    private Value end;

    private Type type;

    private Range(final List<Value> values) {
        this.values = values;
        this.type = Type.EXHAUSTIVELY;
    }

    public Range(final Value begin, final Value end) {
        this.begin = begin;
        this.end = end;
        this.type = Type.BOUNDARY;
    }


    public static Range of(final List<Value> values) {
        return new Range(values);
    }

    public static Range of(final Value begin, final Value end) {
        return new Range(begin, end);
    }

    public Value.Type valueType() {
        if (ListUtil.isNullOrEmpty(this.values())) {
            return Value.Type.STRING;
        }
        return this.values().get(0).type();
    }

    public List<Value> values() {
        return this.values;
    }

    public Value begin() {
        return this.begin;
    }

    public Value end() {
        return this.end;
    }

    public static enum Type {
        BOUNDARY,
        EXHAUSTIVELY;
    }
}
