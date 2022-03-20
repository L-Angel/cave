package fun.langel.cql.calcite.config;

import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.SqlParserImplFactory;
import org.apache.calcite.sql.validate.SqlConformance;

import java.util.Objects;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/17 16:07
 **/
public class CqlConfigImpl implements SqlParser.Config {

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
