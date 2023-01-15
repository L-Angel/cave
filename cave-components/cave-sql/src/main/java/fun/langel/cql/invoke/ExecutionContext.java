package fun.langel.cql.invoke;

import fun.langel.cql.parameter.Parameter;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/17 22:02
 **/
public class ExecutionContext {

    private String origin;

    private List<Parameter> parameters;

    private String parameterized;

    public ExecutionContext(final String sql) {
        this.origin = sql;

    }

}
