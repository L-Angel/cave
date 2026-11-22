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
    public Number executeDelete(String sql) {
        return executeDelete0(prepareSql(sql));
    }

    protected abstract Number executeDelete0(final String sql);

    private String prepareSql(final String sql) {
        if (sql == null) {
            return null;
        }
        String s = sql;
        for (Parameter p : parameters) {
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
            if (p.getName() == null) {
                continue;
            }
            // Pattern.quote protects the parameter name, Matcher.quoteReplacement
            // protects the value: values containing $ or \ must not be treated as
            // regex groups/escapes by replaceAll.
            s = s.replaceAll("[#$]\\{" + Pattern.quote(p.getName()) + "}",
                    Matcher.quoteReplacement(ev));
        }
        return s;
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
