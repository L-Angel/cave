/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fun.langel.cql.node.func;

import fun.langel.cql.node.Column;
import fun.langel.cql.node.Function;
import fun.langel.cql.node.Node;
import fun.langel.cql.util.FeatureUtil;
import fun.langel.cql.util.StringUtil;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/21 00:56
 **/
public class C_KeyValue implements Function {

    private final Column column;

    private final String key;

    private C_KeyValue(final Column column, final String key) {
        this.column = column;
        this.key = StringUtil.stripQuote(key);
    }

    @Override
    public Node executable() {
        return new KeyValueExecutable(this.column, this.key);
    }

    public Column column() {
        return this.column;
    }

    public String key() {
        return this.key;
    }


    public static C_KeyValue of(Column column, String key) {
        return new C_KeyValue(column, key);
    }


    public static class KeyValueExecutable implements Executable<String, String> {

        private final Column column;

        private final String key;

        private KeyValueExecutable(Column column, String key) {
            this.column = column;
            this.key = key;
        }


        @Override
        public String execute(String value) {
            return FeatureUtil.fromString(value).get(this.key);
        }

        public Column column() {
            return this.column;
        }

        public String key() {
            return this.key;
        }
    }

}
