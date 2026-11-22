Cave
----
统一的 SQL 数据访问层：用一套 SQL/CQL 语法查询 Elasticsearch、MongoDB 和 MySQL。

[![CI](https://github.com/L-Angel/cave/actions/workflows/ci.yml/badge.svg)](https://github.com/L-Angel/cave/actions/workflows/ci.yml)
[![Maven Central](https://img.shields.io/maven-central/v/fun.l-angel/cave-sql.svg)](https://search.maven.org/artifact/fun.l-angel/cave-sql)
[![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://adoptium.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6.3%2B-blue.svg)](https://maven.apache.org/)
[![Docs](https://img.shields.io/badge/docs-GitHub%20Pages-blue.svg)](https://L-Angel.github.io/cave/)

文档站点：<https://L-Angel.github.io/cave/>

> 最新发布版本：**0.1.0**。当前 `main` 分支为 **0.1.1-SNAPSHOT**，包含尚未发布的
> Spring Boot Starter 与参数校验改进。

# 特性介绍

- 多异构数据源支持：Elasticsearch、MongoDB、MySQL
- 统一 SQL 查询：同一套语法在不同存储介质之间迁移
- 显式失败：未实现的语法会抛 `UnsupportedOperationException` / `SqlException`，不会被静默忽略
- 参数绑定：`#{}` / `${}` 参数在进入存储之前完成参数化/转义

# 版本需求

| 组件 | 版本 |
|------|------|
| JDK | 8+ |
| antlr4 / antlr4-runtime | 4.10.1 |
| elasticsearch-rest-high-level-client | 6.8.9（当前 POM） |
| mongo-java-driver | 3.12.14 |
| mysql-connector-java | 8.0.29 |
| spring-context / spring-boot | 5.1.8 / 2.1.6（可选） |

> 注意：Elasticsearch 客户端当前编译版本为 6.8.9；升级到 7.x/8.x 需要同步调整 `SearchType`、high-level client 等 API。README 早期版本中的 7.17 与实际 POM 不一致。

# 设计架构

```
CQL / SQL 子集
      │
      ▼
CqlLexer + CqlParser (ANTLR)
      │
      ▼
Statements / AST (SelectStatement、Expr、Function)
      │
      ├── ElasticSearchQDLDialectResolver ──> SearchRequest ──> ElasticSearchSession ──> ElasticSearchRvResolver
      ├── MongoQDLDialectResolver           ──> BSON filter   ──> MongoSession           ──> MongoRvResolver
      └── JDBC (*)                          ──> PreparedStatement(?) ──> JdbcPreparedSession ──> JdbcRvResolver
```

`*` JDBC 路径不解析 SQL，直接把参数化后的 SQL 交给 MySQL 执行。

# 使用

1. Pom 引入

```xml
<dependency>
    <groupId>fun.l-angel</groupId>
    <artifactId>cave-sql</artifactId>
    <version>0.1.0</version>
</dependency>
```

2. 配置扫描路径与数据源

```java
@SpringBootApplication
@CaveScan(packages = {"fun.langel.cql.cave"},
        datasource = {@DataSource(name = "defaultEs", klass = ElasticSearchDataSource.class, priority = 1)})
public class App {
    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
```

3. 编写签名接口

```java
@Cave
public interface CaveDemo {

    @Select(sql = "select field1, field2 from index1 where f1 = #{param1}", direct = "defaultEs")
    String query(@Param(name = "param1") String param1);

    @Select(sql = "select field1, field2 from demo_index where field1 = #{param1}", direct = "defaultEs")
    List<Model> queryList(@Param(name = "param1") String param1);
}
```

```java
public class CaveDemoTest {

    static {
        System.setProperty("cave.elasticsearch.hosts", "127.0.0.1:9200");
    }

    @Autowired
    private CaveDemo caveDemo;

    @Test
    public void test_queryEs_list() {
        List<Model> r = caveDemo.queryList("value1");
        System.out.println(r);
    }
}
```

返回类型由方法签名决定：

| 方法返回类型 | 映射结果 |
|------|------|
| `List<Model>` | 实体列表 |
| `Model` | 取第一行映射为实体 |
| `String` / `int` / `long` | 取第一行第一列 |
| `List<String>` / `List<Integer>` | 单列标量列表 |
| `List<Map<String, Object>>` | 原始列名/值列表 |

实体映射要求无参构造器，字段名需要与列名或 `SELECT` 别名一致（也可通过 `@CField` 指定别名）。`@Select#returnType` 当前未参与映射。

### 多数据源示例

```java
@Cave
public interface Repositories {

    @Select(sql = "select * from demo_index where id = #{id}", direct = "defaultEs")
    Model findEs(@Param(name = "id") String id);

    @Select(sql = "select * from users where id = #{id}", direct = "mongodb")
    User findMongo(@Param(name = "id") long id);

    @Select(sql = "select * from users where id = #{id}", direct = "mysql")
    User findMysql(@Param(name = "id") long id);

    @Update(sql = "update users set name = #{name} where id = #{id}", direct = "mysql")
    int rename(@Param(name = "name") String name, @Param(name = "id") long id);
}
```

- `direct` 对应数据源名称：Starter 为 `defaultEs`、`mongodb`、`mysql`。
- MySQL 路径不解析 SQL，JOIN、子查询、CTE、窗口函数等由 MySQL 原生执行；
  Mongo 为单集合查询；ES 为索引查询。

## Spring Boot Starter（0.1.1+）

Starter 会把 `application.yml` 中的 `cave.*` 配置自动注册为数据源，并支持通过
`cave.packages` 扫描 `@Cave` 接口，不再需要手写 `@CaveScan`：

```xml
<dependency>
    <groupId>fun.l-angel</groupId>
    <artifactId>cave-spring-boot-starter</artifactId>
    <version>0.1.1</version>
</dependency>
```

```yaml
cave:
  packages: [com.example.repository]   # 可选；也可以继续使用 @CaveScan
  elasticsearch:
    hosts: 127.0.0.1:9200              # 多个节点用逗号分隔
    username: elastic                  # 可选
    password: ${ES_PASSWORD:}          # 可选
    default-size: 10000                # 无 LIMIT 时默认返回条数
    group-size: 10000                  # GROUP BY 最大分组数
    enabled: true
    priority: 0
  mongodb:
    host: mongodb://127.0.0.1:27017
    dbname: demo
    username: demo                     # 可选
    password: ${MONGO_PASSWORD:}       # 可选
    enabled: true
    priority: 1
  mysql:
    host: 127.0.0.1:3306
    db-name: demo
    username: root
    password: secret
    # 可选：完整 JDBC URL，优先于 host/db-name
    # url: jdbc:mysql://127.0.0.1:3306/demo
    # url-params: useSSL=false&serverTimezone=UTC
    # pool:
    #   maximum-pool-size: 10
    #   minimum-idle: 2
    #   connection-timeout: 30000
    enabled: true
    priority: 2
```

- 启用数据源名称固定为 `defaultEs`、`mongodb`、`mysql`，可在
  `@Select(direct = "mongodb")` 中引用；省略 `direct` 时使用 priority 最小的数据源
  （默认 0，按 `defaultEs` → `mongodb` → `mysql` 的顺序）。
- 每个数据源都支持 `enabled: false` 与 `priority`。
- `-D` 系统属性和环境变量的优先级高于 `application.yml`。
- `cave.enabled=false` 可以整体关闭 Starter。
- `@CaveScan(useDefaultElasticSearch = true)` 注册的名字是 `defaultEs`；
  旧版本使用的 `default_es` 仍作为别名兼容。
- 该模块随 0.1.1 发布；在发布前可以从源码执行 `./mvnw install` 后引用
  `0.1.1-SNAPSHOT`。

## 数据源配置

### Elasticsearch

地址键为 `cave.elasticsearch.hosts`，支持单个或多个节点：

```text
127.0.0.1:9200
http://es1:9200,http://es2:9200
https://es.example.com:9200
```

- 未写端口时自动补 `9200`。
- 用户名/密码键：`cave.elasticsearch.username` / `cave.elasticsearch.password`。
- 兼容旧键：`es.host` / `es.username` / `es.password`。
- 配置优先级：JVM `-D` > 环境变量（原始带点键名）> Starter YAML > 旧键。

`-D` 示例：

```bash
java -Dcave.elasticsearch.hosts=http://es1:9200,http://es2:9200 \
     -Dcave.elasticsearch.username=elastic \
     -Dcave.elasticsearch.password=secret \
     -jar app.jar
```

不使用 Starter 时，需要显式声明数据源，并在第一次查询前设置地址：

```java
@SpringBootApplication
@CaveScan(
    packages = {"com.example.repository"},
    datasource = {
        @DataSource(name = "defaultEs", klass = ElasticSearchDataSource.class, priority = 0)
    }
)
public class App {
    static {
        System.setProperty("cave.elasticsearch.hosts", "127.0.0.1:9200");
    }
}
```

### MongoDB

```yaml
cave:
  mongodb:
    host: mongodb://127.0.0.1:27017
    dbname: demo
    username: demo
    password: secret
```

对应的键：`cave.mongodb.host`、`cave.mongodb.dbname`、`cave.mongodb.username`、`cave.mongodb.password`。

### MySQL

```yaml
cave:
  mysql:
    host: 127.0.0.1:3306
    db-name: demo
    username: root
    password: secret
```

也可以直接配置完整 JDBC URL：

```yaml
cave:
  mysql:
    url: jdbc:mysql://127.0.0.1:3306/demo?useSSL=false&serverTimezone=UTC
    username: root
    password: secret
```

连接池相关键：`cave.mysql.pool.maximum-pool-size`、`minimum-idle`、
`connection-timeout`、`idle-timeout`、`max-lifetime`。

# 查询能力支持矩阵

## 1. ES / MongoDB 查询引擎

| 能力 | Elasticsearch | MongoDB | 说明 |
|------|:---:|:---:|------|
| `SELECT` 字段列表 | ✅ | ✅ | |
| `SELECT *` | ✅ | ✅ | |
| SELECT 别名 `f AS g` | ✅ | ✅ | 结果字段名与 `ORDER BY` 都可使用别名 |
| `= != > < >= <=` | ✅ | ✅ | 右侧必须是字面量 |
| `LIKE / NOT LIKE` | ✅ | ✅ | SQL `%` / `_` 通配符 |
| `IN / NOT IN` | ✅ | ✅ | 只接受字面量列表，NULL 会被过滤 |
| `BETWEEN / NOT BETWEEN` | ✅ | ✅ | |
| `IS NULL / IS NOT NULL` | ✅ | ✅ | |
| `AND / OR / NOT` | ✅ | ✅ | |
| 括号分组 | ✅ | ✅ | `a = 1 AND (b = 2 OR c = 3)` |
| `&&` / `\|\|` | ✅ | ✅ | 分别等价于 AND / OR |
| `ORDER BY 字段/别名 [ASC\|DESC]` | ✅ | ✅ | |
| `LIMIT offset, count` | ✅ | ✅ | |
| `LIMIT 0` | ✅ | ✅ | 返回空结果 |
| JOIN / 多表 | ❌ | ❌ | 显式抛异常 |
| 子查询（FROM / IN） | ❌ | ❌ | 显式抛异常 |
| `DISTINCT` | ❌ | ✅ | Mongo 复用 `$group` 实现 |
| `GROUP BY` | ✅ | ✅ | ES 使用 composite aggregation；Mongo 使用 `$group` |
| `HAVING` | ✅ | ✅ | ES 在中间件对 bucket 求值；Mongo 在 `$project` 后使用 `$match` |
| 窗口函数 / `UNION` | ❌ | ❌ | 显式抛异常 |
| SELECT 表达式（`select 1`、`f+1`） | ❌ | ❌ | 显式抛异常 |
| 字段间比较（`a > b`） | ❌ | ✅ | Mongo 使用 `$expr` |
| `LIKE ... ESCAPE` | ✅ | ✅ | 支持显式转义字符 |
| `ORDER BY 1` 位置序号 | ✅ | ✅ | 按 SELECT 列序号解析 |
| `SELECT *` 与显式字段混用 | ❌ | ❌ | 显式抛异常 |

## 2. 函数与聚合

| 函数 | Elasticsearch | MongoDB | 说明 |
|------|:---:|:---:|------|
| `count(*)` | ✅ | ✅ | 可单独使用，也可与其他聚合组合 |
| `count(field)` | ✅ | ✅ | 只统计非 NULL 值 |
| `count(DISTINCT field)` | ✅ | ✅ | ES cardinality；Mongo `$addToSet` + `$size` |
| `avg / sum / min / max` | ✅ | ✅ | |
| 多聚合组合 | ✅ | ✅ | 返回一行结果；不能与普通字段混用（除非 GROUP BY） |
| `c_exists('field')` | ✅ | ✅ | ES exists 查询；Mongo `$exists` |
| `c_script('...')` | ✅ | ❌ | ES script 查询；Mongo 明确不支持，注意脚本注入风险 |
| `c_keyvalue(field, 'key')` | ✅ | ✅ | 解析 `K1:V1;K2:V2` 格式的字段 |
| `date_format / date_sub / curdate` | ❌ | ❌ | 仅在 MySQL/JDBC 原生 SQL 中可用 |

聚合限制：
- ES 支持 `GROUP BY` / `HAVING`；普通字段必须出现在 GROUP BY 中。
- ES 的分组结果通过 composite aggregation 获取，默认最多 10000 个分组
  （`cave.elasticsearch.group-size` 可配置）；HAVING、ORDER BY、LIMIT 在中间件内存中执行，
  WHERE 条件应尽量提前过滤以控制分组数量。
- ES 暂不支持 `DISTINCT`、字段间比较。
- Mongo 支持 `GROUP BY` / `HAVING`；SELECT 中的普通字段必须出现在 GROUP BY 中。
- Mongo 的 `HAVING` / `ORDER BY` 只能引用最终投影出的字段（聚合别名或组键）。
- Mongo 对空集合执行 `count(*)` 返回 0，`avg/sum/min/max` 返回 NULL，与 SQL 一致。
- ES 对空分组结果返回空集合；`count(*)` 使用每个 bucket 的 doc_count。

> 能力定义集中在 `fun.langel.cql.capability.Capabilities`，两个方言共享同一套校验和 conformance 测试，未实现的能力会显式失败，不会静默忽略。

## 3. MySQL / JDBC

| 能力 | 状态 | 说明 |
|------|:---:|------|
| SELECT、JOIN、子查询、CTE、窗口函数 | ✅ | MySQL 原生语法直通 |
| GROUP BY / HAVING / DISTINCT / UNION | ✅ | MySQL 原生语法直通 |
| INSERT / UPDATE / DELETE | ✅ | 参数绑定，返回影响行数 |
| `#{name}` 标量参数 | ✅ | `PreparedStatement` 绑定 |
| `${list}` 集合参数 | ✅ | 展开为 `(?, ?, ...)`，支持 `IN (${ids})` |
| JDBC URL / SSL / 时区参数 | ✅ | `cave.mysql.url` / `cave.mysql.url-params` |
| HikariCP 连接池 | ✅ | `cave.mysql.pool.*` |
| 类型映射 | ✅ | BigDecimal、Boolean、日期时间、byte[] 等 |
| DDL / 存储过程 | ❌ | 不属于当前 API |
| 显式事务 | ❌ | 暂未提供 |
| Batch / 自增主键回填 | ❌ | 暂未提供 |
| 动态表名/列名 `${table}` | ❌ | JDBC 下会成为绑定参数 |
| SQL AST 校验/改写 | ❌ | JDBC 路径直接交给 MySQL |

## 4. 各数据源语义差异

### Elasticsearch
- `= null`、`!= null` 按标准 SQL 返回空结果；判空请使用 `IS NULL` / `IS NOT NULL`。
- `!=`、`NOT IN`、`NOT LIKE` 会额外要求字段存在，避免把缺失/NULL 字段算作“不相等”。
- 字符串等值、`IN`、`LIKE` 默认查询 `field.keyword`，要求 mapping 中存在 keyword 子字段。
- `LIKE` 使用 wildcard 查询，区分大小写。
- 未指定 `LIMIT` 时默认取 10000 条（`cave.elasticsearch.default-size` 可配置）。
- 深分页受 `index.max_result_window` 限制，暂不支持 scroll / search_after。
- `GROUP BY` 使用 composite aggregation，默认最多返回 10000 个分组
  （`cave.elasticsearch.group-size` 可配置）；分组后的 `HAVING` / `ORDER BY` / `LIMIT`
  由中间件在内存中完成。
- 非分组聚合查询的 `ORDER BY` 使用原始字段名；聚合别名排序仅在 `GROUP BY` 查询中支持。

### MongoDB
- 只支持单集合查询。
- 聚合管道顺序为 `$match -> $group -> $project -> $match(HAVING) -> $sort -> $skip -> $limit`；非聚合查询为 `$match -> $sort -> $skip -> $limit -> $project`。
- SELECT 别名通过 `$project` 生效；显式投影时会排除 `_id`。
- `IS NULL` 使用 `{field: null}`，会同时匹配 null 和缺失字段；`IS NOT NULL` 使用 `{field: {$ne: null}}`。
- `= null`、`!= null` 按标准 SQL 返回空结果。
- `!=`、`NOT IN`、`NOT LIKE` 会额外要求字段存在。
- `LIKE` 转换为锚定的正则表达式，大小写不敏感（`i`）。
- 支持 `count/avg/sum/min/max`、`count(distinct)`、`GROUP BY`、`HAVING`、`DISTINCT`、
  字段间比较、`c_exists`、`c_keyvalue`。
- 不支持 JOIN、子查询、`c_script`、日期函数，使用时会显式抛异常。
- `GROUP BY` / `DISTINCT` 的组键在管道内部使用 `k0/k1/...` 合成键，最终投影回原字段名或别名。
- 字段间比较使用 `$expr`，例如 `a > b` 生成 `{"$expr": {"$gt": ["$a", "$b"]}}`。

### MySQL / JDBC
- 支持 `SELECT`、`INSERT`、`UPDATE`、`DELETE`，参数通过 `?` 绑定到 `PreparedStatement`。
- JDBC 路径不解析 SQL，`JOIN`、`GROUP BY`、子查询、CTE、窗口函数、`UNION` 等语法由 MySQL 原生支持。
- `${list}` 在 JDBC 下会展开为多个 `?`，支持 `IN (${ids})`；`#{name}` 始终是单个绑定值。
- 可通过 `cave.mysql.url` 直接配置完整 JDBC URL，或用 `cave.mysql.url-params` 追加
  `useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true` 等参数。
- 参数名优先使用 `@Param(name = "...")`；未使用时依赖编译器 `-parameters`（本项目的 POM 已开启）。
- 类型映射支持 `String`、整数/浮点、`BigDecimal`/`BigInteger`、`Boolean`、
  `LocalDateTime`/`LocalDate`/`LocalTime`、`java.util.Date`、`java.sql.Date/Time/Timestamp`、`byte[]`。
- 使用 HikariCP 连接池，默认最大 10 个连接、最小空闲 2 个；可通过
  `cave.mysql.pool.*` 配置。
- 暂不支持显式事务、DDL、存储过程、Batch、自增主键回填。
- 未实现（或未配置）的数据源会在执行时抛出带属性名的 `DataSourceException`。

# 性能说明

- **MySQL/JDBC**：使用 HikariCP 连接池，默认最大 10 个连接、最小空闲 2 个；
  每次查询从池中获取连接，使用后归还，避免反复创建物理连接。
- **Elasticsearch 分组**：使用 composite aggregation；默认最多 10000 个分组
  （`cave.elasticsearch.group-size`）。HAVING、ORDER BY、LIMIT 在中间件内存中执行，
  WHERE 条件应尽量提前缩小结果集；无 HAVING/ORDER BY 且带 LIMIT 时，分组大小会按
  `offset + limit` 下推，避免拉取过多 bucket。
- **Elasticsearch 普通查询**：无 LIMIT 时默认 `size=10000`；深分页受
  `index.max_result_window` 限制。`COUNT(*)` 使用 `totalHits`，分组 `count(*)` 使用
  bucket 的 `doc_count`，不额外发起聚合。
- **MongoDB**：分组、聚合、排序、分页都下推到聚合管道执行，不在中间件缓存全量结果。
- **通用**：参数正则、`@Cave` 方法元数据、排序字段解析均做了复用/预计算；
  避免在每次查询时重复编译正则或排序字段。
- **大列表参数**：JDBC 的 `${list}` 会按元素展开为占位符，列表过大会增加 SQL 长度和
  绑定参数数量，建议分批查询（例如每批 500～1000 个）。

# 参数与转义

```sql
select * from t where f1 = #{p1}                  -- 标量参数
select * from t where f1 in (${p1})               -- 集合参数，展开为 ('a','b')
select * from t where f1 = ${p1}                  -- 列表值展开为 ('a','b')
```

- `#{name}` 与 `${name}` 都按 `@Param(name = "...")` 或 Java 参数名匹配。
- 占位符必须能匹配到方法参数；匹配不到会抛出 `SqlException`（错误信息会列出可用参数），
  不会静默绑定为 `NULL`。
- 若依赖 Java 参数名而不是 `@Param`，应用需要使用 `-parameters` 编译
  （`spring-boot-starter-parent` 默认开启）。推荐始终显式使用 `@Param`。
- 非 JDBC 数据源会把值转义后重新解析 CQL；值中的单引号、反斜杠、控制字符会被转义。
- JDBC 数据源使用 `PreparedStatement` 绑定，不拼接字符串：
  - `#{name}` 始终绑定为单个值；
  - `${list}` 会展开为 `(?, ?, ...)`，支持 `IN (${ids})`；空集合绑定为 `NULL`；
  - `${table}` 这类动态标识符在 JDBC 下不可用（会成为绑定参数），请勿用于表名/列名。

# 显式不支持的语法

以下语法会在解析或方言转换阶段抛出异常，而不是静默返回错误结果：

- `JOIN`、多表查询、FROM 子查询、`IN (subquery)`、`EXISTS (subquery)`
- ES 的 `DISTINCT`、字段间比较
- 窗口函数、`UNION`
- SELECT 表达式
- 未实现的标量函数/聚合函数
- 没有 GROUP BY 时混用聚合与普通字段

# 配置项

| 配置 | 说明 |
|------|------|
| `cave.elasticsearch.hosts` | ES 地址，逗号分隔，例如 `127.0.0.1:9200` |
| `cave.elasticsearch.username` / `password` | ES Basic Auth（可选） |
| `cave.elasticsearch.default-size` | 无 LIMIT 时默认返回条数，默认 10000 |
| `cave.elasticsearch.group-size` | GROUP BY 最大分组数，默认 10000 |
| `cave.mongodb.host` | MongoDB 连接串 |
| `cave.mongodb.dbname` | 数据库名 |
| `cave.mongodb.username` / `password` | 认证信息（可选） |
| `cave.mysql.host` / `cave.mysql.dbName` | MySQL 地址与库名 |
| `cave.mysql.url` | 完整 JDBC URL，优先于 host/dbName |
| `cave.mysql.url-params` | 追加到 JDBC URL 的连接参数，例如 `useSSL=false&serverTimezone=UTC` |
| `cave.mysql.username` / `password` | 认证信息 |
| `cave.mysql.pool.maximum-pool-size` | HikariCP 最大连接数，默认 10 |
| `cave.mysql.pool.minimum-idle` | HikariCP 最小空闲连接数，默认 2 |
| `cave.mysql.pool.connection-timeout` | 获取连接超时，默认 30000ms |
| `cave.mysql.pool.idle-timeout` | 空闲连接回收时间，默认 600000ms |
| `cave.mysql.pool.max-lifetime` | 连接最大生命周期，默认 1800000ms |
| `cave.jdbc.driver` | 自定义 JDBC 驱动（可选） |

配置通过 JVM `-D` 参数或环境变量读取，例如：

```bash
-Dcave.elasticsearch.hosts=127.0.0.1:9200
```

使用 `cave-spring-boot-starter` 时也可以写在 `application.yml` 的 `cave.*` 下，
但 `-D` 参数和环境变量仍然优先。

# 文档站点

本项目的使用文档和支持矩阵同时发布在 GitHub Pages：

<https://L-Angel.github.io/cave/>

Pages 由 `.github/workflows/pages.yml` 在 `main` 分支更新时自动构建部署。
首次启用时，需要在仓库 **Settings → Pages → Build and deployment → Source**
中选择 **GitHub Actions**。

# 构建与测试

```bash
./mvnw -B -DskipTests package    # 编译打包
./mvnw -B test                   # 离线单元测试（默认排除集成测试）
./mvnw -B -Pintegration-tests test  # 集成测试，需要本机启动 ES / MongoDB / Docker(MySQL)
```

# 贡献与社区

- [贡献指南](CONTRIBUTING.md)
- [行为准则](CODE_OF_CONDUCT.md)
- [安全策略](SECURITY.md)
- [变更日志](CHANGELOG.md)
- [许可证](LICENSE)（Apache License 2.0）；第三方声明见 [NOTICE](NOTICE)
- [发布说明](RELEASE.md)
