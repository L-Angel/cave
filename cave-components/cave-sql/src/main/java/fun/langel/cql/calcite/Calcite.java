package fun.langel.cql.calcite;

import fun.langel.cql.calcite.config.Lex;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.SqlParserImplFactory;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.validate.SqlConformance;
import org.apache.calcite.sql.validate.SqlConformanceEnum;

import java.util.Objects;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/29 9:15 下午
 **/
public class Calcite {



    public static CqlConfigBuilder configBuilder() {
        return new CqlConfigBuilder();
    }

    public static class CqlConfigBuilder {
        private Casing quotedCasing;
        private Casing unquotedCasing;
        private Quoting quoting;
        private int identifierMaxLength;
        private boolean caseSensitive;
        private SqlConformance conformance;
        private SqlParserImplFactory parserFactory;

        private CqlConfigBuilder() {
            this.quotedCasing = org.apache.calcite.config.Lex.ORACLE.quotedCasing;
            this.unquotedCasing = org.apache.calcite.config.Lex.ORACLE.unquotedCasing;
            this.quoting = org.apache.calcite.config.Lex.ORACLE.quoting;
            this.identifierMaxLength = 128;
            this.caseSensitive = org.apache.calcite.config.Lex.ORACLE.caseSensitive;
            this.conformance = SqlConformanceEnum.DEFAULT;
            this.parserFactory = SqlParserImpl.FACTORY;
        }

        public CqlConfigBuilder setConfig(SqlParser.Config config) {
            this.quotedCasing = config.quotedCasing();
            this.unquotedCasing = config.unquotedCasing();
            this.quoting = config.quoting();
            this.identifierMaxLength = config.identifierMaxLength();
            this.conformance = config.conformance();
            this.parserFactory = config.parserFactory();
            return this;
        }

        public CqlConfigBuilder setQuotedCasing(Casing quotedCasing) {
            this.quotedCasing = (Casing) Objects.requireNonNull(quotedCasing);
            return this;
        }

        public CqlConfigBuilder setUnquotedCasing(Casing unquotedCasing) {
            this.unquotedCasing = (Casing) Objects.requireNonNull(unquotedCasing);
            return this;
        }

        public CqlConfigBuilder setQuoting(Quoting quoting) {
            this.quoting = (Quoting) Objects.requireNonNull(quoting);
            return this;
        }

        public CqlConfigBuilder setCaseSensitive(boolean caseSensitive) {
            this.caseSensitive = caseSensitive;
            return this;
        }

        public CqlConfigBuilder setIdentifierMaxLength(int identifierMaxLength) {
            this.identifierMaxLength = identifierMaxLength;
            return this;
        }

//        /** @deprecated */
//        @Deprecated
//        public CqlConfigBuilder setAllowBangEqual(final boolean allowBangEqual) {
//            if (allowBangEqual != this.conformance.isBangEqualAllowed()) {
//                this.setConformance(new SqlDelegatingConformance(this.conformance) {
//                    public boolean isBangEqualAllowed() {
//                        return allowBangEqual;
//                    }
//                });
//            }
//
//            return this;
//        }

        public CqlConfigBuilder setConformance(SqlConformance conformance) {
            this.conformance = conformance;
            return this;
        }

        public CqlConfigBuilder setParserFactory(SqlParserImplFactory factory) {
            this.parserFactory = (SqlParserImplFactory) Objects.requireNonNull(factory);
            return this;
        }

        public CqlConfigBuilder setLex(Lex lex) {
            this.setCaseSensitive(lex.caseSensitive);
            this.setUnquotedCasing(lex.unquotedCasing);
            this.setQuotedCasing(lex.quotedCasing);
            this.setQuoting(lex.quoting);
            return this;
        }

        public SqlParser.Config build() {
            return new CqlConfigImpl(this.identifierMaxLength, this.quotedCasing, this.unquotedCasing, this.quoting, this.caseSensitive, this.conformance, this.parserFactory);
        }
    }

    private static class CqlConfigImpl implements SqlParser.Config {

        private final int identifierMaxLength;
        private final boolean caseSensitive;
        private final SqlConformance conformance;
        private final Casing quotedCasing;
        private final Casing unquotedCasing;
        private final Quoting quoting;
        private final SqlParserImplFactory parserFactory;

        private CqlConfigImpl(int identifierMaxLength, Casing quotedCasing, Casing unquotedCasing, Quoting quoting, boolean caseSensitive, SqlConformance conformance, SqlParserImplFactory parserFactory) {
            this.identifierMaxLength = identifierMaxLength;
            this.caseSensitive = caseSensitive;
            this.conformance = (SqlConformance) Objects.requireNonNull(conformance);
            this.quotedCasing = (Casing) Objects.requireNonNull(quotedCasing);
            this.unquotedCasing = (Casing) Objects.requireNonNull(unquotedCasing);
            this.quoting = (Quoting) Objects.requireNonNull(quoting);
            this.parserFactory = (SqlParserImplFactory) Objects.requireNonNull(parserFactory);
        }

        @Override
        public int identifierMaxLength() {
            return this.identifierMaxLength;
        }

        @Override
        public Casing quotedCasing() {
            return this.quotedCasing;
        }

        @Override
        public Casing unquotedCasing() {
            return this.unquotedCasing;
        }

        @Override
        public Quoting quoting() {
            return this.quoting;
        }

        @Override
        public boolean caseSensitive() {
            return this.caseSensitive;
        }

        @Override
        public SqlConformance conformance() {
            return this.conformance;
        }

        @Override
        public boolean allowBangEqual() {
            return this.conformance.isBangEqualAllowed();
        }

        @Override
        public SqlParserImplFactory parserFactory() {
            return this.parserFactory;
        }
    }
}
