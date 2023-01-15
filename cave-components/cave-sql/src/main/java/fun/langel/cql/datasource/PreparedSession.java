package fun.langel.cql.datasource;

import fun.langel.cql.Language;
import fun.langel.cql.bind.Arg;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.parameter.ParameterResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        String s = sql;
        for (Parameter p : parameters) {
            Object sv = p.getValue();
            String ev = null;
            if (sv == null) {
                ev = "null";
            } else if (sv instanceof Number) {
                ev = String.valueOf(sv);
            } else {
                ev = "'" + String.valueOf(sv) + "'";
            }
            s = s.replaceAll("[\\$\\#]\\{" + p.getName() + "}", ev);
        }
        return s;
    }
}
