package fun.langel.cql.bind;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 9:10 下午
 **/
public class CaveRegister {

    private final Map<Class<?>, CaveProxyFactory> knownCaves = new ConcurrentHashMap<>();

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
        knownCaves.computeIfAbsent(klass, m -> new CaveProxyFactory(klass));
    }

    public boolean hasCave(final Class<?> klass) {
        return knownCaves.containsKey(klass);
    }

}
