package fun.langel.cql.bind;

import fun.langel.cql.spring.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 9:10 下午
 **/
public class CaveRegister {

    private final Map<Class<?>, CaveProxyFactory> knownCaves = new ConcurrentHashMap<>();

    private final Configuration configuration;

    public CaveRegister(final Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getCave(final Class<?> klass) {
        CaveProxyFactory<T> factory = knownCaves.get(klass);
        if (factory == null) {
            return null;
        }
        return factory.newInstance();
    }

    public void addCave(Class<?> klass) {
        if (!klass.isInterface()) {
            return;
        }
        knownCaves.computeIfAbsent(klass, m -> new CaveProxyFactory(this.configuration, klass));
    }

    public boolean hasCave(final Class<?> klass) {
        return knownCaves.containsKey(klass);
    }

}
