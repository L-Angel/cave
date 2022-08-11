package fun.langel.cql.reflect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/10 16:37
 **/
public class Klass {

    private final Class<?> actual;
    private List<Field> fields;

    private List<Method> methods;

    private Klass(final Class<?> cls) {
        this.actual = cls;
    }

    public static Klass of(final Class<?> cls) {
        return new Klass(cls);
    }

    public Class<?> actual() {
        return this.actual;
    }

    public List<Field> fields() {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
            java.lang.reflect.Field[] fs = this.actual().getDeclaredFields();
            for (java.lang.reflect.Field f : fs) {
                this.fields.add(Field.of(f));
            }
        }
        return this.fields;
    }

    public Field getField(final String name) {
        for (Field f : fields()) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
        }
        return null;
    }

    public List<Method> methods() {
        if (this.methods == null) {
            this.methods = new ArrayList<>();
            java.lang.reflect.Method[] ms = this.actual().getDeclaredMethods();
            for (java.lang.reflect.Method m : ms) {
                this.methods.add(Method.of(m));
            }
        }
        return this.methods;
    }


    public Object newInstance() throws InstantiationException, IllegalAccessException {
        return actual().newInstance();
    }

}
