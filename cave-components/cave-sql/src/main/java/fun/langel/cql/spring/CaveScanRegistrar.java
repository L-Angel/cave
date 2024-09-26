package fun.langel.cql.spring;


import fun.langel.cql.annotation.CaveScan;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.DataSourceHolder;
import fun.langel.cql.datasource.support.ElasticSearchDataSource;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
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
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes caveScanAttrs = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(CaveScan.class.getName()));
        String[] packages = caveScanAttrs.getStringArray("packages");
        boolean useDefaultElasticSearch = caveScanAttrs.getBoolean("useDefaultElasticSearch");
        AnnotationAttributes[] datasourceAttr = caveScanAttrs.getAnnotationArray("datasource");

        CaveClassPathBeanDefinitionScanner scanner = new CaveClassPathBeanDefinitionScanner(registry, false);
        // recognize datasource
        this.configuration.setDataSource(resolveDataSources(datasourceAttr));
        if (useDefaultElasticSearch) {
            this.configuration.addDataSource(resolveDatasource("default_es", ElasticSearchDataSource.class));
        }
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
            final Class<? extends DataSource> klass = attr.getClass("klass");
            final int priority = attr.getNumber("priority");
            DataSourceHolder holder = DataSourceHolder.builder().setKlass(klass).setName(name).setPriority(priority).build();
            holders.add(holder);
        }
        return holders;
    }

    private DataSourceHolder resolveDatasource(String name, Class<? extends DataSource> klass) {
        return DataSourceHolder.builder().setKlass(klass).setName(name).setPriority(0).build();
    }


}
