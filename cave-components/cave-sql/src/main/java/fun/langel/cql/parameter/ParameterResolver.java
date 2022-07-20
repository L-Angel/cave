package fun.langel.cql.parameter;

import fun.langel.cql.util.Pair;

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

    public Pair<String, List<Parameter>> resolve(final String sql) {
        if (sql == null) {
            return Pair.EMPTY;
        }
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(sql);
        final LinkedList<Parameter> parameters = new LinkedList<>();
        while (matcher.find()) {
            String g = matcher.group();
            parameters.add(new Parameter(g.substring(2, g.length() - 1), null));
        }
        String parameterized = matcher.replaceAll("?");
        return Pair.of(parameterized, parameters);
    }
}
