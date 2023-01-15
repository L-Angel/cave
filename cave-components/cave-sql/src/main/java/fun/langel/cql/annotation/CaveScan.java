package fun.langel.cql.annotation;

import fun.langel.cql.spring.CaveScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/7/21 12:46 上午
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(CaveScanRegistrar.class)
public @interface CaveScan {

    String[] packages();

    boolean useDefaultElasticSearch()  default false;

    DataSource[] datasource() default {};

}
