/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.boot;

import fun.langel.cql.boot.scan.BootDemo;
import fun.langel.cql.constant.Const;
import fun.langel.cql.spring.Configuration;
import fun.langel.cql.spring.CaveScanSupport;
import fun.langel.cql.util.Props;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rick Jiang
 */
public class CaveDataSourceInitializerTest {

    @After
    public void clearOverrides() {
        Props.clearOverrides();
    }

    @Test
    public void registersEnabledDatasourcesAndProperties() {
        CaveProperties properties = new CaveProperties();
        properties.getElasticsearch().setHosts("127.0.0.1:9200");
        properties.getMongodb().setHost("mongodb://127.0.0.1:27017");
        properties.getMongodb().setDbname("demo");
        properties.getMysql().setHost("127.0.0.1:3306");
        properties.getMysql().setDbName("demo");

        Configuration configuration = new Configuration();
        new CaveDataSourceInitializer(configuration, properties);

        Assert.assertNotNull(configuration.getDataSource(Configuration.DEFAULT_ELASTICSEARCH_DATASOURCE));
        Assert.assertNotNull(configuration.getDataSource(CaveDataSourceInitializer.DEFAULT_MONGODB_DATASOURCE));
        Assert.assertNotNull(configuration.getDataSource(CaveDataSourceInitializer.DEFAULT_MYSQL_DATASOURCE));
        Assert.assertEquals("127.0.0.1:9200", Props.getProperty(Const.CAVE_ELASTICSEARCH_HOST));
        Assert.assertEquals("demo", Props.getProperty(Const.CAVE_MONGODB_DBNAME));
        Assert.assertEquals("demo", Props.getProperty(Const.CAVE_MYSQL_DBNAME));
    }

    @Test
    public void legacyDefaultElasticsearchNameStillResolves() {
        CaveProperties properties = new CaveProperties();
        properties.getElasticsearch().setHosts("127.0.0.1:9200");

        Configuration configuration = new Configuration();
        new CaveDataSourceInitializer(configuration, properties);

        Assert.assertNotNull(configuration.getDataSource("default_es"));
    }

    @Test
    public void disabledDatasourceIsNotRegistered() {
        CaveProperties properties = new CaveProperties();
        properties.getElasticsearch().setEnabled(false);
        properties.getElasticsearch().setHosts("127.0.0.1:9200");

        Configuration configuration = new Configuration();
        new CaveDataSourceInitializer(configuration, properties);

        Assert.assertNull(configuration.getDataSource(Configuration.DEFAULT_ELASTICSEARCH_DATASOURCE));
    }

    @Test
    public void missingConfigurationIsTolerated() {
        new CaveDataSourceInitializer(null, new CaveProperties());
    }

    @Test
    public void initializerSeesConfigurationSharedWithCaveScan() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        Configuration configuration = CaveScanSupport.getOrCreateConfiguration(beanFactory);

        CaveProperties properties = new CaveProperties();
        properties.getElasticsearch().setHosts("127.0.0.1:9200");

        ObjectProvider<Configuration> provider = beanFactory.getBeanProvider(Configuration.class);
        new CaveAutoConfiguration().caveDataSourceInitializer(provider, properties);

        Assert.assertNotNull(configuration.getDataSource(Configuration.DEFAULT_ELASTICSEARCH_DATASOURCE));
    }

    @Test
    public void autoConfigurationRegistersDatasourcesAndScannedInterfaces() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("cave.packages", "fun.langel.cql.boot.scan");
        properties.put("cave.elasticsearch.hosts", "127.0.0.1:9200");
        properties.put("cave.elasticsearch.default-size", "500");

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.getEnvironment().getPropertySources()
                    .addFirst(new MapPropertySource("test", properties));
            context.register(CaveAutoConfiguration.class);
            context.refresh();

            Assert.assertNotNull(context.getBean(BootDemo.class));
            Assert.assertNotNull(context.getBean(CaveProperties.class));

            Configuration configuration = (Configuration) context.getBean(
                    CaveScanSupport.CONFIGURATION_BEAN_NAME);
            Assert.assertNotNull(configuration.getDataSource(Configuration.DEFAULT_ELASTICSEARCH_DATASOURCE));
            Assert.assertEquals("500", Props.getProperty(Const.CAVE_ELASTICSEARCH_DEFAULT_SIZE));
        }
    }
}
