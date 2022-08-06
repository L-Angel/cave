package fun.langel.cql.resolve.dialect;

import fun.langel.cql.resolve.DialectResolver;
import org.elasticsearch.action.ActionRequest;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/3 11:50
 **/
public interface ElasticSearchDialectResolver<IN, OUT extends ActionRequest> extends DialectResolver<IN, OUT> {
}
