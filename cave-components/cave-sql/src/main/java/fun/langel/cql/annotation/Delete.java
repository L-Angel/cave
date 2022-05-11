package fun.langel.cql.annotation;

import fun.langel.cql.enums.Mode;

import java.lang.annotation.*;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:19 下午
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Delete {
    String sql();

    Mode mode() default Mode.DIRECT;

    Class<?> direct() default Object.class;
}
