Cave
----
统一的SQL数据访问层

# 特性介绍

- 多异构数据源支持
- 统一化sql查询支持
- sql扩展支持

# 版本需求

| 组件                                    | 版本  ||
|---------------------------------------|-----|---------|
| antlr                                 | 4.10.1 ||
| antlr-runtime                         | 4.10.1 ||
| elasticsearch                         | 7.17.0+ ||
| elasticsearch-rest-client             | 7.17.0 ||
| elasticsearch-rest-high-level-client  | 7.17.0 ||
| springframework                       |     ||
| springboot                            |     |optional|

# 设计架构

# 使用

1. Pom引入

```xml

<dependency>
    <groupId>fun.l-angel</groupId>
    <artifactId>cave-sql</actifactId>
</dependency>
```
2. 配置扫描路径、配置数据源
```java
@SpringBootApplication
@CaveScan(packages = {"fun.langel.cql.cave"},
        datasource = {@DataSource(name = "defaultEs", klass = ElasticSearchDataSource.class, priority = 1)})
public class App {
    public static void main(String... args){
        SpringApplication.run(App.class, args);
    }
}

```
3. 编写签名类
```java
@Cave
public interface CaveDemo {

    @Select(sql = "select field1,field2 from index1 where f1 = #{param1}", direct = "defaultEs")
    String query(@Param(name = "param1") String param1);

    @Select(sql = "select field1,field2 from demo_index where field1 = #{param1}",
            direct = "defaultEs"
            , returnType = Model.class)
    List<Model> queryList(@Param(name = "param1") String param1);
}

```

```java
public class CaveDemo{

    static {
        System.setProperty("cave.elasticsearch.hosts", "127.0.0.1:9200");
    }

    @Autowired
    private CaveDemo caveDemo;

    @Test
    public void test_queryEs() {
        caveDemo.query("param_abc");
    }

    @Test
    public void test_queryEs_list() {
        List<Model> r = caveDemo.queryList("value1");

        System.out.println(r);
    }
}
```

# CQL支持的语法
```
like
not like
= (equal)
!= (not equal)
> (greater)
< (less)
>= (greater or equals)
<= (less or equals)
and 
or
```

## 函数
- **c_exists('colName')** ES
- **c_script('script'')** ES
