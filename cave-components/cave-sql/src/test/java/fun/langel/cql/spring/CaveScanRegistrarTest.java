/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.spring;

import fun.langel.cql.annotation.CaveScan;
import fun.langel.cql.annotation.DataSource;
import fun.langel.cql.cave.CaveDemo;
import fun.langel.cql.datasource.support.EmptyDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Offline checks that {@code @CaveScan} still registers proxies and that the
 * shared {@link Configuration} instance survives the refactor.
 *
 * @author Rick Jiang
 */
public class CaveScanRegistrarTest {

    @org.springframework.context.annotation.Configuration
    @CaveScan(packages = "fun.langel.cql.cave",
            datasource = @DataSource(name = "test", klass = EmptyDataSource.class))
    static class ScanConfig {
    }

    @Test
    public void registersCaveInterfaceAndSharesConfiguration() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(ScanConfig.class)) {
            Assert.assertNotNull(context.getBean(CaveDemo.class));

            Object configuration = context.getBean(CaveScanSupport.CONFIGURATION_BEAN_NAME);
            Assert.assertTrue(configuration instanceof Configuration);
            Assert.assertNotNull(((Configuration) configuration).getDataSource("test"));
        }
    }
}
