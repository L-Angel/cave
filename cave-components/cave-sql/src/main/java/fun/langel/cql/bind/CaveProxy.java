package fun.langel.cql.bind;

import fun.langel.cql.spring.Configuration;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 8:53 下午
 **/
public class CaveProxy implements InvocationHandler, Serializable {

    private final Class<?> klass;
    private final Map<Method, CaveMethod> cache;

    private final Configuration configuration;

    public CaveProxy(final Configuration configuration,
                     final Class<?> interfaceKlass,
                     final Map<Method, CaveMethod> cache) {
        this.configuration = configuration;
        this.klass = interfaceKlass;
        this.cache = cache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return invokeObjectMethod(proxy, method, args);
        } else if (isDefaultMethod(method)) {
            return invokeDefaultMethod(proxy, method, args);
        }
        return cachedMethod(method).execute(args);
    }

    private Object invokeObjectMethod(Object proxy, Method method, Object[] args) throws Throwable {
        switch (method.getName()) {
            case "equals":
                return proxy == args[0];
            case "hashCode":
                return System.identityHashCode(proxy);
            case "toString":
                return "Cave proxy for " + this.klass.getName();
            default:
                return method.invoke(this, args);
        }
    }

    public CaveMethod cachedMethod(final Method method) {
        return cache.computeIfAbsent(method, (m) -> new CaveMethod(this.configuration, this.klass, m));
    }

    public Object invokeDefaultMethod(Object proxy, Method method, Object... args) throws Throwable {
        final Class<?> declaringClass = method.getDeclaringClass();
        final MethodHandles.Lookup lookup = defaultLookup(declaringClass);
        Object[] actualArgs = args == null ? new Object[0] : args;
        return lookup.unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(actualArgs);
    }

    private static MethodHandles.Lookup defaultLookup(Class<?> declaringClass) throws Exception {
        try {
            // Java 9+: the private Lookup constructor used below no longer exists.
            Method privateLookupIn = MethodHandles.class.getMethod("privateLookupIn",
                    Class.class, MethodHandles.Lookup.class);
            return (MethodHandles.Lookup) privateLookupIn.invoke(null, declaringClass,
                    MethodHandles.lookup());
        } catch (NoSuchMethodException notJava9) {
            final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                    .getDeclaredConstructor(Class.class, int.class);
            constructor.setAccessible(true);
            return constructor.newInstance(declaringClass,
                    MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                            | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC);
        }
    }

    public boolean isDefaultMethod(final Method method) {
        return (method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC
                && method.getDeclaringClass().isInterface();
    }
}
