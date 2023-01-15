package fun.langel.cql.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/10 16:37
 **/
public class Method {

    private java.lang.reflect.Method actual;

    private Method(final java.lang.reflect.Method m) {
        this.actual = m;
        this.actual.setAccessible(true);
    }

    public static Method of(java.lang.reflect.Method m) {
        return new Method(m);
    }

    public java.lang.reflect.Method actual() {
        return this.actual;
    }

    public Object invoke(final Object o, final Object... args) throws InvocationTargetException, IllegalAccessException {
        return this.actual.invoke(o, args);
    }

    public Class<?> returnType() {
        return actual().getReturnType();
    }

    public Parameter[] parameters() {
        return actual().getParameters();
    }

    public String getName() {
        return actual().getName();
    }
}
