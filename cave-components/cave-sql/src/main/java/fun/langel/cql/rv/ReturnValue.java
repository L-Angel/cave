package fun.langel.cql.rv;

import java.io.Serializable;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/22 8:34 下午
 **/
public interface ReturnValue<T> extends Serializable {

    T getValue();
}
