package fun.langel.cql.spring;


import fun.langel.cql.Cave;
import fun.langel.cql.annotation.CaveScan;
import fun.langel.cql.annotation.DataSource;
import fun.langel.cql.datasource.DataSourceHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.LinkedList;
import java.util.List;

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
        AnnotationAttributes[] datasourceAttr = caveScanAttrs.getAnnotationArray("datasource");

        CaveClassPathBeanDefinitionScanner scanner = new CaveClassPathBeanDefinitionScanner(registry, false);
        // recognize datasource
        this.configuration.setDataSource(resolveDataSources(datasourceAttr));
        scanner.setConfiguration(this.configuration);
        scanner.setAnnotationKlass(fun.langel.cql.annotation.Cave.class);
        scanner.registerIncludeFilter();
        scanner.doScan(packages);

    }

    private List<DataSourceHolder> resolveDataSources(AnnotationAttributes[] attributes) {
        final List<DataSourceHolder> holders = new LinkedList<>();
        for (int idx = 0, len = attributes.length; idx < len; idx++) {
            AnnotationAttributes attr = attributes[idx];
            final String name = attr.getString("name");
            final Class<?> klass = attr.getClass("klass");
            final int priority = attr.getNumber("priority");
            holders.add(new DataSourceHolder(klass, name, priority));
        }
        return holders;
    }


}
