# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- Elasticsearch `INSERT` / `UPDATE` / `DELETE`: index/bulk writes,
  `_update_by_query` with a painless `SET` script and `_delete_by_query`,
  reusing the CQL WHERE translation.
- MongoDB `INSERT` / `UPDATE` / `DELETE`: `insertOne` / `insertMany`,
  `updateMany` with `$set` and `deleteMany`, reusing the CQL WHERE translation.
- INSERT / UPDATE / DELETE parameter routing
  (`executeInsert` / `executeUpdate` / `executeDelete`); non-JDBC DML
  interpolates `#{name}` values like SELECT.
- CQL parser builds INSERT / UPDATE / DELETE ASTs with table, column values,
  SET assignments and WHERE clauses.
- Explicit CQL support matrix in `README.md` for Elasticsearch, MongoDB and JDBC.
- Parentheses, `NOT`, `NOT BETWEEN`, `&&`/`||` and standard AND/OR precedence.
- `SUM`, `MIN`, `MAX`, `COUNT(DISTINCT ...)` parsing and Elasticsearch aggregations.
- Elasticsearch `c_keyvalue`, SELECT aliases, multi-aggregate result rows.
- `IS NULL` / `IS NOT NULL` handling for Elasticsearch and MongoDB.
- SQL wildcard translation for Elasticsearch `LIKE` / `NOT LIKE`.
- Configurable Elasticsearch default page size (`cave.elasticsearch.default-size`).
- MongoDB SELECT aliases, `_id` exclusion, `LIMIT 0` handling.
- Scalar and `Map` result mapping (`List<String>`, `List<Map<String, Object>>`).
- Boolean literal and negative number support in CQL.
- Offline unit tests for parsing, dialect translation, result mapping and the proxy.
- MongoDB `GROUP BY`, `HAVING`, `count(*)`, `count(field)`, `count(distinct)`,
  `avg/sum/min/max`, `c_exists` and `c_keyvalue`.
- Capability contract (`DialectCapabilities` / `Capabilities`) shared by all
  dialects, plus cross-dialect conformance tests.
- Elasticsearch `count(*)` can now be combined with other aggregate functions.
- Shared `RowProjector` for aliases and `c_keyvalue` across Elasticsearch and MongoDB.
- Open-source project materials: `LICENSE`, `NOTICE`, `CONTRIBUTING.md`,
  `CODE_OF_CONDUCT.md`, `SECURITY.md`, `CHANGELOG.md`, `.editorconfig`,
  GitHub issue/PR templates, CODEOWNERS, Dependabot, CI workflow and Maven Wrapper.
- Apache-2.0 SPDX headers replacing the previous placeholder license headers.
- `cave-spring-boot-starter`: Spring Boot auto-configuration with `cave.*`
  configuration properties, `cave.packages` scanning and a shared
  `Configuration` instance between the starter and `@CaveScan`.
- `Props` supports embedded configuration overrides; JVM `-D` properties and
  environment variables still take precedence.
- JDBC `${list}` expansion: `IN (${ids})` now generates `(?, ?, ...)` and binds
  each element; empty collections bind `NULL`.
- MySQL JDBC URL configuration through `cave.mysql.url` and
  `cave.mysql.url-params` (SSL, timezone, public-key retrieval, ...).
- `-parameters` compiler flag so method parameters can be matched without
  `@Param`.
- JDBC type mapping for `BigDecimal`/`BigInteger`, `Boolean`, date/time types,
  `LocalDate`/`LocalTime` and `byte[]` (`rv.Boolean`, `rv.Bytes`).
- MySQL Testcontainers integration test covering JOIN, CTE, window functions,
  `IN (${list})`, DML binding and type mapping.
- Documentation site refreshed at <https://l-angel.github.io/cave/>.
- Starter support for `cave.elasticsearch.group-size`.
- MongoDB `DISTINCT` (lowered to `$group`), field-to-field comparisons
  (`$expr`), `LIKE ... ESCAPE` and `ORDER BY` positional ordinals.
- `LIKE ... ESCAPE` and `ORDER BY 1` support for Elasticsearch and MongoDB.
- Elasticsearch `GROUP BY` / `HAVING` via composite aggregation, with middleware
  HAVING evaluation, group ordering and limit; configurable
  `cave.elasticsearch.group-size`.
- MySQL HikariCP connection pool with configurable pool sizes/timeouts.
- MySQL integration tests for pool-backed queries and type mapping.

### Changed

- `Cql.parseDeleteStatement` now throws `SqlException` when no DELETE statement is
  present (previously returned `null`), mirroring `parseSelectStatement`.
- `INSERT` / `UPDATE` / `DELETE` no longer require a JDBC datasource; ES and
  MongoDB execute them through their native write APIs (no WHERE means all
  documents, matching SQL semantics).
- Project version is now `0.1.1-SNAPSHOT`; the latest published release is
  `0.1.0`.
- `@CaveScan(useDefaultElasticSearch = true)` registers the datasource as
  `defaultEs` (matching the documented examples); `default_es` remains
  available as an alias.
- A CQL placeholder that does not match any method argument now throws
  `SqlException` instead of silently binding `NULL`.
- `@CaveScan` logs a warning when no `@Cave` interfaces are found in the
  configured packages.
- Unsupported syntax now fails explicitly instead of being silently ignored:
  JOIN / multiple tables, subqueries, window functions, UNION, SELECT expressions,
  ES DISTINCT and ES field-to-field comparisons.
- `DISTINCT`, field-to-field comparisons, `LIKE ... ESCAPE` and `ORDER BY`
  ordinals are now parsed and routed through the capability contract; Mongo
  supports them while ES rejects the unsupported ones.
- MongoDB aggregation pipeline order corrected to
  `$match -> $sort -> $skip -> $limit -> $project`.
- `!=`, `NOT IN` and `NOT LIKE` now require the field to exist, matching SQL
  NULL semantics more closely.
- `= NULL` / `!= NULL` now return no rows; use `IS NULL` / `IS NOT NULL`.
- Integration tests are excluded from the default Maven test run.

### Fixed

- MySQL connections are now pooled with HikariCP instead of being created and
  closed per query.
- Elasticsearch group ordering resolves output fields once instead of during
  every comparator call; parameter regex and `@Cave` method metadata are reused.
- Elasticsearch group queries without HAVING/ORDER BY push `offset + limit` into
  the composite aggregation size.
- Double connection acquisition per query and JDBC resource leaks.
- JDBC result columns used a 0-based index instead of JDBC's 1-based index.
- JDBC `PreparedStatement` parameter indexes were 0-based.
- MySQL SELECT was never routed; JDBC INSERT/UPDATE/DELETE were stubs.
- `@Update` methods were routed as `@Delete`.
- Parameter interpolation could corrupt values containing `$`/`\` and allowed
  CQL injection; values are now escaped and replaced literally.
- `date_sub` failed for upper-case units; `date_format` kept SQL `%` patterns
  instead of converting them to a Java date pattern.
- MongoDB LIKE ignored `%`/`_` semantics and injected raw regex.
- `CaveProxy` default methods failed on JDK 9+; object methods and the method
  cache were incorrect under concurrency.
- ANTLR syntax errors were silently recovered; they now raise `SqlException`.
- Data-source configuration NPEs replaced with clear `DataSourceException`s.

### Security

- Escaped CQL string values in non-JDBC parameter interpolation.
- MongoDB LIKE patterns are quoted before being compiled into a regex.
- `c_script` remains a trusted-script escape hatch; do not pass user input.

## [0.1.0]

- MongoDB datasource query support.

## [0.0.4]

- Subquery syntax parsing.
- `date_format`, `date_sub`, `curdate` helpers.

## [0.0.3]

- Fixed parsing when the WHERE clause is empty.

## [0.0.2]

- `count`, `avg` aggregates.

## [0.0.1]

- Initial release: CQL parsing and Elasticsearch queries.

[Unreleased]: https://github.com/L-Angel/cave/compare/0.1.0...HEAD
