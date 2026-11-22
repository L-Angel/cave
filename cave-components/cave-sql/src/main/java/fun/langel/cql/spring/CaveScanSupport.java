/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.spring;

import fun.langel.cql.annotation.Cave;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * Shared helpers for registering {@code @Cave} interfaces from a
 * {@code @CaveScan} annotation or from Spring Boot configuration properties.
 *
 * @author Rick Jiang
 */
public final class CaveScanSupport {

    /**
     * Bean/singleton name of the shared {@link Configuration} instance.
     */
    public static final String CONFIGURATION_BEAN_NAME = "caveConfiguration";

    private CaveScanSupport() {
    }

    /**
     * Returns the configuration already registered in this context, or creates
     * and registers a new one. Both the annotation registrar and the Spring
     * Boot starter must use the same instance so that datasources registered
     * from configuration properties are visible to proxies created earlier.
     */
    public static Configuration getOrCreateConfiguration(final BeanDefinitionRegistry registry) {
        if (registry instanceof SingletonBeanRegistry) {
            Object existing = ((SingletonBeanRegistry) registry).getSingleton(CONFIGURATION_BEAN_NAME);
            if (existing instanceof Configuration) {
                return (Configuration) existing;
            }
        }
        Configuration configuration = new Configuration();
        if (registry instanceof SingletonBeanRegistry) {
            ((SingletonBeanRegistry) registry).registerSingleton(CONFIGURATION_BEAN_NAME, configuration);
        }
        return configuration;
    }

    /**
     * Scans the given packages for interfaces annotated with {@link Cave} and
     * registers a proxy factory bean for each of them.
     */
    public static void scan(final BeanDefinitionRegistry registry,
                            final Configuration configuration,
                            final String... packages) {
        CaveClassPathBeanDefinitionScanner scanner = new CaveClassPathBeanDefinitionScanner(registry, false);
        scanner.setConfiguration(configuration);
        scanner.setAnnotationKlass(Cave.class);
        scanner.registerIncludeFilter();
        scanner.doScan(packages);
    }
}
