package fun.langel.cql.node.func;

import fun.langel.cql.exception.SqlException;
import fun.langel.cql.node.Function;
import fun.langel.cql.node.Node;
import fun.langel.cql.node.Value;
import fun.langel.cql.node.obj.Date;

import java.time.format.DateTimeFormatter;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/11/12 14:44
 **/
public class DateFormat implements Function {

    private Date date;
    private String pattern;

    private Function function;

    private DateFormat(Date date, String pattern) {
        this.date = date;
        this.pattern = pattern;
    }

    private DateFormat(Function function, String pattern) {
        this.function = function;
        this.pattern = pattern;
    }

    @Override
    public Node executable() {
        if (this.function != null) {
            Node resolved = this.function.executable();
            if (!(resolved instanceof Date)) {
                throw new SqlException("date_format requires a date expression but got: " + resolved);
            }
            return Value.of(((Date) resolved).getLocalDate().format(formatter()));
        }
        if (this.date == null) {
            throw new SqlException("date_format requires a date expression.");
        }
        return Value.of(date.getLocalDate().format(formatter()));
    }

    private DateTimeFormatter formatter() {
        if (pattern == null) {
            throw new SqlException("date_format requires a format pattern.");
        }
        return DateTimeFormatter.ofPattern(pattern.indexOf('%') >= 0
                ? toJavaPattern(pattern)
                : pattern);
    }

    /**
     * Converts MySQL date format specifiers ({@code %Y-%m-%d}) into a Java
     * {@link DateTimeFormatter} pattern ({@code yyyy-MM-dd}). Literal characters
     * are quoted so letters in the format are never interpreted as pattern
     * letters by Java.
     */
    private static String toJavaPattern(final String mysqlPattern) {
        StringBuilder out = new StringBuilder();
        StringBuilder literal = new StringBuilder();
        for (int idx = 0; idx < mysqlPattern.length(); idx++) {
            char c = mysqlPattern.charAt(idx);
            if (c == '%' && idx + 1 < mysqlPattern.length()) {
                char specifier = mysqlPattern.charAt(++idx);
                String mapped = javaSpecifier(specifier);
                if (mapped == null) {
                    literal.append(c).append(specifier);
                } else {
                    flushLiteral(out, literal);
                    out.append(mapped);
                }
            } else {
                literal.append(c);
            }
        }
        flushLiteral(out, literal);
        return out.toString();
    }

    private static void flushLiteral(StringBuilder out, StringBuilder literal) {
        if (literal.length() == 0) {
            return;
        }
        out.append('\'').append(literal.toString().replace("'", "''")).append('\'');
        literal.setLength(0);
    }

    private static String javaSpecifier(final char specifier) {
        switch (specifier) {
            case 'Y':
                return "yyyy";
            case 'y':
                return "yy";
            case 'm':
                return "MM";
            case 'c':
                return "M";
            case 'd':
                return "dd";
            case 'e':
                return "d";
            case 'H':
                return "HH";
            case 'k':
                return "H";
            case 'h':
            case 'I':
                return "hh";
            case 'l':
                return "h";
            case 'i':
                return "mm";
            case 's':
            case 'S':
                return "ss";
            case 'f':
                return "SSSSSS";
            case 'p':
                return "a";
            case 'M':
                return "MMMM";
            case 'b':
                return "MMM";
            case 'W':
                return "EEEE";
            case 'a':
                return "EEE";
            case 'j':
                return "DDD";
            case 'T':
                return "HH:mm:ss";
            case 'r':
                return "hh:mm:ss a";
            case 'D':
                return "MM/dd/yy";
            case 'F':
                return "yyyy-MM-dd";
            case '%':
                return "%";
            default:
                return null;
        }
    }

    public static DateFormat of(Date date, String pattern) {
        return new DateFormat(date, pattern);
    }

    public static DateFormat of(Function function, String pattern) {
        return new DateFormat(function, pattern);
    }
}
