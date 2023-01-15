package fun.langel.cql.annotation;

import fun.langel.cql.enums.Mode;

import java.lang.annotation.*;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/7/21 12:44 上午
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Select {

    String sql();

    Mode mode() default Mode.DIRECT;

    /**
     * 指定数据源名称
     *
     * @return
     */
    String direct() default "";

    Class<?> returnType() default Object.class;
}
