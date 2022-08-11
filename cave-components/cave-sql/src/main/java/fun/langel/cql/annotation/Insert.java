package fun.langel.cql.annotation;

import java.lang.annotation.*;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/7/21 12:45 上午
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Insert {
    
    String sql();
    /**
     * 指定数据源名称
     *
     * @return
     */
    String direct() default "";

    Class<?> returnType() default Object.class;

}
