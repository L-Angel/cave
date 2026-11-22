/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for the Cave Spring Boot starter.
 *
 * <pre>
 * cave:
 *   packages: [com.example.repository]
 *   elasticsearch:
 *     hosts: 127.0.0.1:9200
 *   mongodb:
 *     host: mongodb://127.0.0.1:27017
 *     dbname: demo
 *   mysql:
 *     host: 127.0.0.1:3306
 *     db-name: demo
 *     username: root
 *     password: secret
 *     # Optional: full JDBC URL, takes precedence over host/db-name.
 *     url: jdbc:mysql://127.0.0.1:3306/demo
 *     url-params: useSSL=false&amp;serverTimezone=UTC
 * </pre>
 *
 * JVM {@code -D} properties and environment variables still take precedence
 * over values configured here.
 *
 * @author Rick Jiang
 */
@ConfigurationProperties(prefix = "cave")
public class CaveProperties {

    /**
     * Disables the whole starter when set to {@code false}.
     */
    private boolean enabled = true;

    /**
     * Packages scanned for {@code @Cave} interfaces. Alternative to
     * {@code @CaveScan(packages = ...)}.
     */
    private List<String> packages = new ArrayList<>();

    private Elasticsearch elasticsearch = new Elasticsearch();

    private Mongodb mongodb = new Mongodb();

    private Mysql mysql = new Mysql();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    public Elasticsearch getElasticsearch() {
        return elasticsearch;
    }

    public void setElasticsearch(Elasticsearch elasticsearch) {
        this.elasticsearch = elasticsearch;
    }

    public Mongodb getMongodb() {
        return mongodb;
    }

    public void setMongodb(Mongodb mongodb) {
        this.mongodb = mongodb;
    }

    public Mysql getMysql() {
        return mysql;
    }

    public void setMysql(Mysql mysql) {
        this.mysql = mysql;
    }

    public static class Elasticsearch {

        private boolean enabled = true;

        /** Comma-separated host list, for example {@code 127.0.0.1:9200}. */
        private String hosts;

        private String username;

        private String password;

        /** Default page size when a CQL statement has no LIMIT. */
        private int defaultSize = 10000;

        /** Maximum number of buckets returned by a GROUP BY query. */
        private int groupSize = 10000;

        /** Datasource priority; the lowest value is used when {@code direct} is absent. */
        private int priority;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getHosts() {
            return hosts;
        }

        public void setHosts(String hosts) {
            this.hosts = hosts;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getDefaultSize() {
            return defaultSize;
        }

        public void setDefaultSize(int defaultSize) {
            this.defaultSize = defaultSize;
        }

        public int getGroupSize() {
            return groupSize;
        }

        public void setGroupSize(int groupSize) {
            this.groupSize = groupSize;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }

    public static class Mongodb {

        private boolean enabled = true;

        /** MongoDB connection string, for example {@code mongodb://127.0.0.1:27017}. */
        private String host;

        private String dbname;

        private String username;

        private String password;

        private int priority;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getDbname() {
            return dbname;
        }

        public void setDbname(String dbname) {
            this.dbname = dbname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }

    public static class Mysql {

        private boolean enabled = true;

        private String host;

        private String dbName;

        /** Optional full JDBC URL; takes precedence over host/dbName. */
        private String url;

        /** Optional connection properties appended to the JDBC URL. */
        private String urlParams;

        private Pool pool = new Pool();

        private String username;

        private String password;

        /** Optional JDBC driver class name. */
        private String driver;

        private int priority;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        /**
         * Alias so that {@code cave.mysql.dbname} binds as well as
         * {@code cave.mysql.db-name}.
         */
        public void setDbname(String dbName) {
            this.dbName = dbName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlParams() {
            return urlParams;
        }

        public void setUrlParams(String urlParams) {
            this.urlParams = urlParams;
        }

        public Pool getPool() {
            return pool;
        }

        public void setPool(Pool pool) {
            this.pool = pool;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }

    public static class Pool {

        private Integer maximumPoolSize;

        private Integer minimumIdle;

        private Long connectionTimeout;

        private Long idleTimeout;

        private Long maxLifetime;

        public Integer getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public void setMaximumPoolSize(Integer maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
        }

        public Integer getMinimumIdle() {
            return minimumIdle;
        }

        public void setMinimumIdle(Integer minimumIdle) {
            this.minimumIdle = minimumIdle;
        }

        public Long getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(Long connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        public Long getIdleTimeout() {
            return idleTimeout;
        }

        public void setIdleTimeout(Long idleTimeout) {
            this.idleTimeout = idleTimeout;
        }

        public Long getMaxLifetime() {
            return maxLifetime;
        }

        public void setMaxLifetime(Long maxLifetime) {
            this.maxLifetime = maxLifetime;
        }
    }
}
