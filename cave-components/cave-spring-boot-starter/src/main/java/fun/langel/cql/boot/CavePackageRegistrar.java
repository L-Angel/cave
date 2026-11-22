/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.boot;

import fun.langel.cql.spring.Configuration;
import fun.langel.cql.spring.CaveScanSupport;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Scans {@code cave.packages} for {@code @Cave} interfaces when the starter is
 * used without {@code @CaveScan}.
 *
 * @author Rick Jiang
 */
public class CavePackageRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        String[] packages = packages(environment);
        if (packages.length == 0) {
            return;
        }
        Configuration configuration = CaveScanSupport.getOrCreateConfiguration(registry);
        CaveScanSupport.scan(registry, configuration, packages);
    }

    private String[] packages(Environment env) {
        if (env == null) {
            return new String[0];
        }
        List<String> configured = Binder.get(env)
                .bind("cave.packages", Bindable.listOf(String.class))
                .orElse(Collections.<String>emptyList());
        List<String> packages = new ArrayList<>(configured.size());
        for (String value : configured) {
            if (value == null) {
                continue;
            }
            String trimmed = value.trim();
            if (!trimmed.isEmpty()) {
                packages.add(trimmed);
            }
        }
        return packages.toArray(new String[0]);
    }
}
