package fun.langel.cql.datasource;

import fun.langel.cql.Language;
import fun.langel.cql.bind.Arg;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.parameter.ParameterResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.util.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/12 14:59
 **/
public abstract class PreparedSession implements Session {

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("[#$]\\{.*?}");

    protected List<Parameter> parameters = new LinkedList<>();

    private final ParameterResolver resolver = new ParameterResolver();


    public void setParameters(final List<Parameter> parameters) {
        if (parameters != null) {
            this.parameters.addAll(parameters);
        }
    }


    @Override
    public ReturnValue<?> executeQuery(String sql) {
        return this.executeQuery0(prepareSql(sql));
    }

    protected abstract ReturnValue<?> executeQuery0(final String sql);

    @Override
    public Number executeUpdate(String sql) {
        return executeUpdate0(prepareSql(sql));
    }

    protected abstract Number executeUpdate0(final String sql);

    @Override
    public Number executeInsert(String sql) {
        return executeInsert0(prepareSql(sql));
    }

    /**
     * Legacy sessions can keep routing inserts through their update path; the
     * built-in JDBC, Elasticsearch and MongoDB sessions override this.
     */
    protected Number executeInsert0(final String sql) {
        return executeUpdate0(sql);
    }

    @Override
    public Number executeDelete(String sql) {
        return executeDelete0(prepareSql(sql));
    }

    protected abstract Number executeDelete0(final String sql);

    private String prepareSql(final String sql) {
        if (sql == null || this.parameters.isEmpty()) {
            return sql;
        }
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(sql);
        StringBuffer prepared = new StringBuffer(sql.length());
        while (matcher.find()) {
            String placeholder = matcher.group();
            String name = placeholder.substring(2, placeholder.length() - 1);
            Parameter parameter = findParameter(name);
            // Single pass over the original SQL: a value that itself contains
            // #{...} must never be treated as a placeholder.
            matcher.appendReplacement(prepared,
                    Matcher.quoteReplacement(parameter == null ? placeholder : render(parameter)));
        }
        matcher.appendTail(prepared);
        return prepared.toString();
    }

    private Parameter findParameter(final String name) {
        for (Parameter parameter : this.parameters) {
            if (name.equals(parameter.getName())) {
                return parameter;
            }
        }
        return null;
    }

    private String render(final Parameter p) {
        Object sv = p.getValue();
        String ev;
        boolean collectionTyped = p.getKlass() != null
                && (List.class.isAssignableFrom(p.getKlass()) || Set.class.isAssignableFrom(p.getKlass()));
        if (collectionTyped || sv instanceof Collection) {
            if (sv == null || ((Collection<?>) sv).isEmpty()) {
                ev = "(null)";
            } else {
                List<String> c = new LinkedList<>();
                for (Object v : (Collection<?>) sv) {
                    if (v == null) {
                        continue;
                    }
                    c.add(v instanceof Number ? v.toString() : quote(String.valueOf(v)));
                }
                ev = c.isEmpty() ? "(null)" : "(" + String.join(",", c) + ")";
            }
        } else if (sv == null) {
            ev = "null";
        } else if (sv instanceof Boolean) {
            ev = sv.toString();
        } else if (sv instanceof Number) {
            ev = String.valueOf(sv);
        } else {
            ev = quote(String.valueOf(sv));
        }
        return ev;
    }

    /**
     * Quotes a string value for CQL/MySQL literals. Control characters,
     * backslashes and single quotes are escaped so a value cannot terminate the
     * literal and inject CQL.
     */
    private static String quote(final String value) {
        StringBuilder sb = new StringBuilder(value.length() + 2).append('\'');
        for (int idx = 0; idx < value.length(); idx++) {
            char c = value.charAt(idx);
            switch (c) {
                case '\0':
                    sb.append("\\0");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\'':
                    sb.append("\\'");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.append('\'').toString();
    }
}
