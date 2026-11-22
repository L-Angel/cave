---
layout: default
title: 贡献指南
---

# 贡献指南

感谢你考虑为 Cave 做贡献。这是一个使用统一 SQL/CQL 语法访问 Elasticsearch、MongoDB 和 MySQL 的数据访问层。

## 开发环境

- JDK 8+
- Maven 3.6.3+，或使用仓库自带的 Maven Wrapper（`./mvnw`）

## 构建与测试

```bash
# 编译
./mvnw -B -DskipTests package

# 运行默认的离线单元测试（不依赖外部服务）
./mvnw -B test

# 运行集成测试（需要 Elasticsearch / MongoDB；MySQL 使用 Docker）
./mvnw -B -Pintegration-tests test
```

默认的 `test` 会排除标记为 `fun.langel.cql.IntegrationTest` 的用例。集成测试需要：

- Elasticsearch：`cave.elasticsearch.hosts`，默认示例为 `127.0.0.1:9200`
- MongoDB：`cave.mongodb.host` / `cave.mongodb.dbname`

## 提交代码

1. Fork 仓库并从 `main` 创建分支。
2. 保持 Java 8 兼容性。
3. 为行为变更补充测试：
   - 解析/方言转换放在离线单元测试中；
   - 需要真实外部服务的用例加上 `@Category(IntegrationTest.class)`。
4. 运行 `./mvnw -B test`，确保离线测试全部通过。
5. 提交 PR，描述变更内容、动机和验证方式。

## 代码风格

- 4 空格缩进，LF 换行，UTF-8。
- 公共 API 尽量保持兼容；破坏性变更需要在 PR 中说明。
- 未实现的语法应当显式抛出异常，不要静默忽略。
- 不要在日志中输出密码等敏感信息。

## 报告问题

- Bug / 功能建议：使用 GitHub Issue 模板。
- 安全漏洞：请阅读 [SECURITY.md](SECURITY.md)，不要公开提交 Issue。

## 许可证

向本项目提交的代码默认以 [Apache License 2.0](LICENSE) 授权。
