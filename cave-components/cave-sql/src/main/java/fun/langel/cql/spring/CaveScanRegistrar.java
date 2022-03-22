package fun.langel.cql.spring;


import fun.langel.cql.Cave;
import fun.langel.cql.annotation.CaveScan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/8/19 9:21 下午
 **/
public class CaveScanRegistrar implements ImportBeanDefinitionRegistrar {

    private final Configuration configuration;

    public CaveScanRegistrar() {
        this.configuration = new Configuration();
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
                                        BeanDefinitionRegistry registry) {
        AnnotationAttributes caveScanAttrs = AnnotationAttributes
                .fromMap(annotationMetadata.getAnnotationAttributes(CaveScan.class.getName()));
        String[] packages = caveScanAttrs.getStringArray("packages");
        CaveClassPathBeanDefinitionScanner scanner = new CaveClassPathBeanDefinitionScanner(registry, false);
        // scan include interface
        scanner.setConfiguration(this.configuration);
        scanner.setAnnotationKlass(fun.langel.cql.annotation.Cave.class);
        scanner.registerIncludeFilter();
        scanner.doScan(packages);

    }
}
