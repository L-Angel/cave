package fun.langel.cql.spring;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Set;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/8/20 12:34 上午
 **/
public class CaveClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends Annotation> annotationKlass;

    private Configuration configuration;

    public CaveClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry,
                                              boolean useDefaultFilters,
                                              Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public CaveClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry,
                                              boolean useDefaultFilters,
                                              Environment environment,
                                              ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }

    public CaveClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public CaveClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public void setAnnotationKlass(Class<? extends Annotation> annoKlass) {
        this.annotationKlass = annoKlass;
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {

        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder bfh : beanDefinitions) {
            GenericBeanDefinition gbd = (GenericBeanDefinition) bfh.getBeanDefinition();
            ConstructorArgumentValues args = new ConstructorArgumentValues();
            args.addIndexedArgumentValue(0, gbd.getBeanClassName());
            args.addIndexedArgumentValue(1, this.configuration);
            gbd.setBeanClass(CaveFactoryBean.class);
            gbd.setConstructorArgumentValues(args);
        }
        return Collections.emptySet();
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return (metadata.isIndependent() && (metadata.isConcrete() ||
                (metadata.isAbstract() && metadata.hasAnnotatedMethods(Lookup.class.getName())) ||
                (metadata.isAbstract() && metadata.hasAnnotation(this.annotationKlass.getName()))));
    }

    public void setConfiguration(final Configuration configuration) {
        this.configuration = configuration;
    }

    public void registerIncludeFilter() {
        this.addIncludeFilter(new AnnotationTypeFilter(this.annotationKlass));
    }


}
