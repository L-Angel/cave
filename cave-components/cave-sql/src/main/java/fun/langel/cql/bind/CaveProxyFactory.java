package fun.langel.cql.bind;

import fun.langel.cql.spring.Configuration;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 9:22 下午
 **/
public class CaveProxyFactory<T> {

    private Class<?> klass;

    private Configuration configuration;
    private final Map<Method, CaveMethod> methodCache = new HashMap<>();

    public CaveProxyFactory(final Configuration configuration,
                            final Class<?> klass) {
        this.configuration = configuration;
        this.klass = klass;
    }

    public T newInstance() {
        return (T) Proxy.newProxyInstance(this.klass.getClassLoader(), new Class[]{klass}, new CaveProxy(this.configuration, this.klass, methodCache));
    }
}
