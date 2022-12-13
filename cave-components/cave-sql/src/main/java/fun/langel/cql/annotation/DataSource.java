package fun.langel.cql.annotation;

import java.lang.annotation.*;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/4/28 11:16
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface DataSource {

    String name() default "";

    Class<? extends fun.langel.cql.datasource.DataSource> klass() default fun.langel.cql.datasource.DataSource.class;

    int priority() default 0;

}
