package fun.langel.cql.resolve;

import fun.langel.cql.node.Node;
import org.apache.calcite.sql.SqlNode;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 15:25
 **/
public interface SqlResolver<F extends SqlNode, O extends Node> {
    O resolve(F select);
}
