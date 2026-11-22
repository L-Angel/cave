/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.capability;

/**
 * Built-in datasource capabilities.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public final class Capabilities {

    public static final DialectCapabilities ELASTICSEARCH = new Immutable("elasticsearch",
            true, true, true, false, true, true, true);

    public static final DialectCapabilities MONGO = new Immutable("mongodb",
            true, true, true, true, true, true, false);

    private Capabilities() {
    }

    private static final class Immutable implements DialectCapabilities {

        private final String dialect;
        private final boolean aggregations;
        private final boolean groupBy;
        private final boolean having;
        private final boolean distinct;
        private final boolean keyValue;
        private final boolean cExists;
        private final boolean cScript;

        private Immutable(String dialect, boolean aggregations, boolean groupBy, boolean having,
                          boolean distinct, boolean keyValue, boolean cExists, boolean cScript) {
            this.dialect = dialect;
            this.aggregations = aggregations;
            this.groupBy = groupBy;
            this.having = having;
            this.distinct = distinct;
            this.keyValue = keyValue;
            this.cExists = cExists;
            this.cScript = cScript;
        }

        @Override
        public String dialect() {
            return dialect;
        }

        @Override
        public boolean aggregations() {
            return aggregations;
        }

        @Override
        public boolean groupBy() {
            return groupBy;
        }

        @Override
        public boolean having() {
            return having;
        }

        @Override
        public boolean distinct() {
            return distinct;
        }

        @Override
        public boolean keyValue() {
            return keyValue;
        }

        @Override
        public boolean cExists() {
            return cExists;
        }

        @Override
        public boolean cScript() {
            return cScript;
        }
    }
}
