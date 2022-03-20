package fun.langel.cql.calcite.config;

import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/17 16:02
 **/
public enum Lex {

    ORACLE(Quoting.DOUBLE_QUOTE, Casing.TO_UPPER, Casing.UNCHANGED, true),
    MYSQL(Quoting.BACK_TICK, Casing.UNCHANGED, Casing.UNCHANGED, false),
    MYSQL_ANSI(Quoting.DOUBLE_QUOTE, Casing.UNCHANGED, Casing.UNCHANGED, false),
    SQL_SERVER(Quoting.BRACKET, Casing.UNCHANGED, Casing.UNCHANGED, false),
    JAVA(Quoting.BACK_TICK, Casing.UNCHANGED, Casing.UNCHANGED, true),
    CAVE_SQL(Quoting.BACK_TICK,Casing.UNCHANGED,Casing.TO_LOWER,false);

    public final Quoting quoting;
    public final Casing unquotedCasing;
    public final Casing quotedCasing;
    public final boolean caseSensitive;

    private Lex(Quoting quoting, Casing unquotedCasing, Casing quotedCasing, boolean caseSensitive) {
        this.quoting = quoting;
        this.unquotedCasing = unquotedCasing;
        this.quotedCasing = quotedCasing;
        this.caseSensitive = caseSensitive;
    }
}
