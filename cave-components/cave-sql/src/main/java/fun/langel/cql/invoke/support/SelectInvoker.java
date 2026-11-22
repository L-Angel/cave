package fun.langel.cql.invoke.support;

import fun.langel.cql.Language;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.Session;
import fun.langel.cql.exception.DataSourceException;
import fun.langel.cql.invoke.AbstractInvoker;
import fun.langel.cql.invoke.Invocation;
import fun.langel.cql.invoke.Result;
import fun.langel.cql.bind.Target;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import fun.langel.cql.util.Pair;
import fun.langel.cql.util.TypeUtil;

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
        try {
            Result result = new Result();
            result.setValue(map(session.executeQuery(executableSql(session)), iv));
            return result;
        } finally {
            session.close();
        }
    }

    private String executableSql(Session session) {
        Language lang = session.lang();
        if (lang == Language.ELASTIC_SEARCH || lang == Language.MONGO) {
            // Non-JDBC sessions interpolate #{param} values into the CQL statement
            // and re-parse it, so they need the original statement.
            return this.sql();
        }
        if (lang == Language.MYSQL) {
            return executableSql();
        }
        throw new DataSourceException("Unsupported datasource language: " + lang);
    }

    private Object map(ReturnValue<?> rv, Invocation iv) {
        if (rv == null) {
            return null;
        }
        if (rv instanceof Rows && !List.class.isAssignableFrom(iv.returnType())) {
            List<Row> rows = ((Rows) rv).getValue();
            if (rows == null || rows.isEmpty()) {
                return null;
            }
            Row row = rows.get(0);
            if (row.getValue().size() == 1 && TypeUtil.isSimple(iv.actualType())) {
                ReturnValue<?> single = row.getValue().values().iterator().next();
                return single == null ? null : single.mapTo(iv.actualType());
            }
            return row.mapTo(iv.actualType());
        }
        return rv.mapTo(iv.actualType());
    }

}
