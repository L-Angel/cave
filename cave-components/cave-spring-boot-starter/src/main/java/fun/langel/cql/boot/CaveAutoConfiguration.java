/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.boot;

import fun.langel.cql.spring.Configuration;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Spring Boot auto-configuration for Cave.
 *
 * <p>Datasources are registered from {@code cave.*} configuration properties.
 * Interfaces can be discovered either through {@code cave.packages} or through
 * the existing {@code @CaveScan(packages = ...)} annotation.</p>
 *
 * @author Rick Jiang
 */
@org.springframework.context.annotation.Configuration
@ConditionalOnProperty(prefix = "cave", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(CaveProperties.class)
@Import(CavePackageRegistrar.class)
public class CaveAutoConfiguration {

    @Bean
    public CaveDataSourceInitializer caveDataSourceInitializer(
            ObjectProvider<Configuration> configurationProvider,
            CaveProperties properties) {
        return new CaveDataSourceInitializer(configurationProvider.getIfAvailable(), properties);
    }
}
