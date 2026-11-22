---
layout: default
title: 发布说明
---

# Release notes

The full and up-to-date history is maintained in [CHANGELOG.md](CHANGELOG.md).

## Unreleased (0.1.1)

- New `cave-spring-boot-starter`: datasource configuration through
  `application.yml` (`cave.*`) and interface scanning through `cave.packages`.
- Unmatched CQL placeholders now fail with `SqlException` instead of binding
  `NULL` silently.
- `@CaveScan` warns when no `@Cave` interfaces are found.
- The implicit Elasticsearch datasource is now named `defaultEs`; `default_es`
  is kept as an alias.
- MySQL `${list}` expansion for `IN (...)`, JDBC URL parameters,
  `-parameters` compilation, and richer type mapping.
- MongoDB `DISTINCT`, field-to-field comparisons, `LIKE ... ESCAPE` and
  `ORDER BY` positional ordinals.
- Elasticsearch `GROUP BY` / `HAVING` using composite aggregations.
- MySQL HikariCP connection pool.
- MySQL Testcontainers integration coverage.
- GitHub Pages documentation site.

## 0.1.0

- MongoDB datasource query support.

## 0.0.4

- Subquery syntax parsing (execution is not supported; subqueries are now
  rejected explicitly).
- `date_format`, `date_sub`, `curdate` helper evaluation.

## 0.0.3

- Fixed parsing when the WHERE clause is empty.
