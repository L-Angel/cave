package fun.langel.cql.invoke.support;

import fun.langel.cql.Language;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.PreparedSession;
import fun.langel.cql.datasource.Session;
import fun.langel.cql.exception.DataSourceException;
import fun.langel.cql.invoke.AbstractInvoker;
import fun.langel.cql.invoke.Invocation;
import fun.langel.cql.invoke.Result;
import fun.langel.cql.bind.Target;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.util.Pair;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:04 下午
 **/
public class SelectInvoker extends AbstractInvoker {


    public SelectInvoker(Target target,
                         DataSource dataSource,
                         String sql,
                         Pair<String, List<Parameter>> parameterized) {
        super(target, dataSource, sql, parameterized);
    }

    @Override
    public Result invoke(Invocation iv) {
        Session session = getSession();
        Language lang = session.lang();
        ReturnValue<?> rv = null;
        if (lang == Language.ELASTIC_SEARCH) {
            rv = session.executeQuery(this.sql());
        }

        Result result = new Result();
        result.setValue(rv == null ? null : rv.mapTo((Class<?>) iv.actualType()));
        return result;
    }

}
