package fun.langel.cql.annotation;

import java.lang.annotation.*;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/4/28 11:16
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Datasource {

    Class<?> klass() default Object.class;

    int priority() default 0;
}
