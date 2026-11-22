/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.boot;

import fun.langel.cql.constant.Const;
import fun.langel.cql.datasource.DataSourceHolder;
import fun.langel.cql.datasource.support.ElasticSearchDataSource;
import fun.langel.cql.datasource.support.JdbcDataSource;
import fun.langel.cql.datasource.support.MongoDataSource;
import fun.langel.cql.spring.Configuration;
import fun.langel.cql.util.Props;
import fun.langel.cql.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Registers datasources described by {@link CaveProperties} and copies their
 * settings into the global Cave property store. Values are stored as
 * low-precedence overrides, so {@code -D} and environment variables still win.
 *
 * @author Rick Jiang
 */
public class CaveDataSourceInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(CaveDataSourceInitializer.class);

    public static final String DEFAULT_MONGODB_DATASOURCE = "mongodb";

    public static final String DEFAULT_MYSQL_DATASOURCE = "mysql";

    public CaveDataSourceInitializer(final Configuration configuration,
                                     final CaveProperties properties) {
        if (properties == null) {
            return;
        }
        if (configuration == null) {
            if (hasConfiguredDatasource(properties)) {
                LOG.warn("Cave datasources are configured, but no Cave Configuration was registered. "
                        + "Add @CaveScan(packages = \"...\") or set cave.packages in your configuration.");
            }
            return;
        }
        registerElasticsearch(configuration, properties.getElasticsearch());
        registerMongodb(configuration, properties.getMongodb());
        registerMysql(configuration, properties.getMysql());
    }

    private void registerElasticsearch(final Configuration configuration,
                                       final CaveProperties.Elasticsearch es) {
        if (es == null || !es.isEnabled() || StringUtil.isEmpty(es.getHosts())) {
            return;
        }
        Props.setOverride(Const.CAVE_ELASTICSEARCH_HOST, es.getHosts());
        if (!StringUtil.isEmpty(es.getUsername())) {
            Props.setOverride(Const.CAVE_ELASTICSEARCH_USERNAME, es.getUsername());
        }
        if (!StringUtil.isEmpty(es.getPassword())) {
            Props.setOverride(Const.CAVE_ELASTICSEARCH_PASSWORD, es.getPassword());
        }
        if (es.getDefaultSize() > 0) {
            Props.setOverride(Const.CAVE_ELASTICSEARCH_DEFAULT_SIZE, String.valueOf(es.getDefaultSize()));
        }
        if (es.getGroupSize() > 0) {
            Props.setOverride(Const.CAVE_ELASTICSEARCH_GROUP_SIZE, String.valueOf(es.getGroupSize()));
        }
        configuration.addDataSourceIfAbsent(DataSourceHolder.builder()
                .setName(Configuration.DEFAULT_ELASTICSEARCH_DATASOURCE)
                .setKlass(ElasticSearchDataSource.class)
                .setPriority(es.getPriority())
                .build());
    }

    private void registerMongodb(final Configuration configuration,
                                 final CaveProperties.Mongodb mongo) {
        if (mongo == null || !mongo.isEnabled()
                || StringUtil.isEmpty(mongo.getHost()) || StringUtil.isEmpty(mongo.getDbname())) {
            return;
        }
        Props.setOverride(Const.CAVE_MONGODB_HOST, mongo.getHost());
        Props.setOverride(Const.CAVE_MONGODB_DBNAME, mongo.getDbname());
        if (!StringUtil.isEmpty(mongo.getUsername())) {
            Props.setOverride(Const.CAVE_MONGODB_USERNAME, mongo.getUsername());
        }
        if (!StringUtil.isEmpty(mongo.getPassword())) {
            Props.setOverride(Const.CAVE_MONGODB_PASSWORD, mongo.getPassword());
        }
        configuration.addDataSourceIfAbsent(DataSourceHolder.builder()
                .setName(DEFAULT_MONGODB_DATASOURCE)
                .setKlass(MongoDataSource.class)
                .setPriority(mongo.getPriority())
                .build());
    }

    private void registerMysql(final Configuration configuration,
                               final CaveProperties.Mysql mysql) {
        boolean hasUrl = mysql != null && !StringUtil.isEmpty(mysql.getUrl());
        if (mysql == null || !mysql.isEnabled()
                || (!hasUrl && (StringUtil.isEmpty(mysql.getHost()) || StringUtil.isEmpty(mysql.getDbName())))) {
            return;
        }
        if (hasUrl) {
            Props.setOverride(Const.CAVE_MYSQL_URL, mysql.getUrl());
        } else {
            Props.setOverride(Const.CAVE_MYSQL_HOST, mysql.getHost());
            Props.setOverride(Const.CAVE_MYSQL_DBNAME, mysql.getDbName());
        }
        if (!StringUtil.isEmpty(mysql.getUrlParams())) {
            Props.setOverride(Const.CAVE_MYSQL_URL_PARAMS, mysql.getUrlParams());
        }
        if (mysql.getPool() != null) {
            CaveProperties.Pool pool = mysql.getPool();
            if (pool.getMaximumPoolSize() != null) {
                Props.setOverride(Const.CAVE_MYSQL_POOL_MAXIMUM_SIZE, String.valueOf(pool.getMaximumPoolSize()));
            }
            if (pool.getMinimumIdle() != null) {
                Props.setOverride(Const.CAVE_MYSQL_POOL_MINIMUM_IDLE, String.valueOf(pool.getMinimumIdle()));
            }
            if (pool.getConnectionTimeout() != null) {
                Props.setOverride(Const.CAVE_MYSQL_POOL_CONNECTION_TIMEOUT, String.valueOf(pool.getConnectionTimeout()));
            }
            if (pool.getIdleTimeout() != null) {
                Props.setOverride(Const.CAVE_MYSQL_POOL_IDLE_TIMEOUT, String.valueOf(pool.getIdleTimeout()));
            }
            if (pool.getMaxLifetime() != null) {
                Props.setOverride(Const.CAVE_MYSQL_POOL_MAX_LIFETIME, String.valueOf(pool.getMaxLifetime()));
            }
        }
        if (!StringUtil.isEmpty(mysql.getUsername())) {
            Props.setOverride(Const.CAVE_MYSQL_USERNAME, mysql.getUsername());
        }
        if (!StringUtil.isEmpty(mysql.getPassword())) {
            Props.setOverride(Const.CAVE_MYSQL_PASSWORD, mysql.getPassword());
        }
        if (!StringUtil.isEmpty(mysql.getDriver())) {
            Props.setOverride(Const.CAVE_JDBC_DRIVER, mysql.getDriver());
        }
        configuration.addDataSourceIfAbsent(DataSourceHolder.builder()
                .setName(DEFAULT_MYSQL_DATASOURCE)
                .setKlass(JdbcDataSource.class)
                .setPriority(mysql.getPriority())
                .build());
    }

    private boolean hasConfiguredDatasource(final CaveProperties properties) {
        CaveProperties.Elasticsearch es = properties.getElasticsearch();
        CaveProperties.Mongodb mongo = properties.getMongodb();
        CaveProperties.Mysql mysql = properties.getMysql();
        return (es != null && es.isEnabled() && !StringUtil.isEmpty(es.getHosts()))
                || (mongo != null && mongo.isEnabled() && !StringUtil.isEmpty(mongo.getHost()))
                || (mysql != null && mysql.isEnabled()
                && (!StringUtil.isEmpty(mysql.getHost()) || !StringUtil.isEmpty(mysql.getUrl())));
    }
}
