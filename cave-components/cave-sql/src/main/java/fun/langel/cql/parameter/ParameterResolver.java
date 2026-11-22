package fun.langel.cql.parameter;

import fun.langel.cql.bind.Arg;
import fun.langel.cql.exception.SqlException;
import fun.langel.cql.util.Pair;
import fun.langel.cql.util.StringUtil;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/16 15:51
 **/
public class ParameterResolver {


    private static final String REGEX = "[#\\$]\\{.*?}";

    private static final Pattern PARAM_PATTERN = Pattern.compile(REGEX);

    public Pair<String, List<Parameter>> parameterized(final String sql) {
        if (sql == null) {
            return Pair.EMPTY;
        }
        Matcher matcher = PARAM_PATTERN.matcher(sql);
        final LinkedList<Parameter> parameters = new LinkedList<>();
        int pos = 0;
        while (matcher.find()) {
            String g = matcher.group();
            parameters.add(new Parameter(g.substring(2, g.length() - 1), null, pos, null));
            pos += 1;
        }
        String parameterized = matcher.replaceAll("?");
        return Pair.of(parameterized, parameters);
    }

    /**
     * Builds a JDBC-specific parameter list. {@code ${name}} placeholders whose
     * argument is a {@link Collection} are expanded to a matching number of
     * {@code ?} markers, which is what {@code IN (${ids})} needs on JDBC.
     * {@code #{name}} always remains a single bound value.
     */
    public Pair<String, List<Parameter>> resolveForJdbc(final String sql, Arg[] args) {
        if (sql == null) {
            return Pair.empty();
        }
        final Arg[] actualArgs = args == null ? new Arg[0] : args;
        Matcher matcher = PARAM_PATTERN.matcher(sql);
        final StringBuilder parameterized = new StringBuilder(sql.length());
        final List<Parameter> parameters = new LinkedList<>();
        int last = 0;
        int pos = 0;
        while (matcher.find()) {
            parameterized.append(sql, last, matcher.start());
            String placeholder = matcher.group();
            String name = placeholder.substring(2, placeholder.length() - 1);
            Arg arg = matchArg(name, actualArgs);
            if (arg == null) {
                throw unmatched(name, actualArgs);
            }
            boolean collectionPlaceholder = placeholder.charAt(0) == '$';
            Object value = arg.value();
            if (collectionPlaceholder && value instanceof Collection) {
                Collection<?> values = (Collection<?>) value;
                if (values.isEmpty()) {
                    parameterized.append('?');
                    parameters.add(new Parameter(name, null, pos++, arg.klass()));
                } else {
                    boolean first = true;
                    for (Object item : values) {
                        if (!first) {
                            parameterized.append(',');
                        }
                        parameterized.append('?');
                        parameters.add(new Parameter(name, item, pos++, arg.klass()));
                        first = false;
                    }
                }
            } else {
                parameterized.append('?');
                parameters.add(new Parameter(name, value, pos++, arg.klass()));
            }
            last = matcher.end();
        }
        parameterized.append(sql.substring(last));
        return Pair.of(parameterized.toString(), parameters);
    }

    /**
     * @param sql
     * @param args
     * @return value
     * left : origin sql
     * right : parameterized sql
     */
    public Pair<String, List<Parameter>> resolve(final String sql, Arg[] args) {
        Pair<String, List<Parameter>> pair = parameterized(sql);
        if (pair instanceof Pair.EmptyPair) {
            return Pair.empty();
        }
        final Arg[] actualArgs = args == null ? new Arg[0] : args;
        for (Parameter p : pair.right()) {
            Arg arg = matchArg(p.getName(), actualArgs);
            if (arg == null) {
                throw unmatched(p.getName(), actualArgs);
            }
            p.setValue(arg.value());
            p.setKlass(arg.klass());
        }
        return pair;
    }

    private SqlException unmatched(final String name, final Arg[] args) {
        return new SqlException("No argument matched CQL placeholder '" + name + "'. "
                + "Available parameters: " + availableNames(args)
                + ". Annotate the method parameter with @Param(name = \"" + name + "\") "
                + "or compile the application with the '-parameters' compiler flag.");
    }

    private String availableNames(Arg[] args) {
        StringBuilder names = new StringBuilder("[");
        for (int idx = 0, len = args.length; idx < len; idx++) {
            if (idx > 0) {
                names.append(", ");
            }
            Arg arg = args[idx];
            names.append(StringUtil.isEmpty(arg.alias()) ? arg.name() : arg.alias());
        }
        return names.append(']').toString();
    }

    private Arg matchArg(String name, Arg[] args) {
        for (int idx = 0, len = args.length; idx < len; idx++) {
            Arg arg = args[idx];
            final String n = !StringUtil.isEmpty(arg.alias()) ? arg.alias() : arg.name();
            if (n.equalsIgnoreCase(name)) {
                return arg;
            }
        }
        return null;
    }

}
