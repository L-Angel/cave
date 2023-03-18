package fun.langel.cql.parameter;

import fun.langel.cql.bind.Arg;
import fun.langel.cql.util.Pair;
import fun.langel.cql.util.StringUtil;

import java.util.Collections;
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

    public Pair<String, List<Parameter>> parameterized(final String sql) {
        if (sql == null) {
            return Pair.EMPTY;
        }
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(sql);
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
        if (args == null || args.length == 0) {
            return pair;
        }
        for (Parameter p : pair.right()) {
            Arg arg = matchArg(p.getName(), args);
            if (arg == null) {
                continue;
            }
            p.setValue(arg.value());
            p.setKlass(arg.klass());
        }
        return pair;
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
