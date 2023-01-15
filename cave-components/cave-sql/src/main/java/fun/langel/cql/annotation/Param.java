package fun.langel.cql.annotation;

import java.lang.annotation.*;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:35 下午
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Param {
    String name();
}
