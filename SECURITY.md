---
layout: default
title: 安全策略
---

# Security Policy

## Supported Versions

| Version | Supported |
|---------|-----------|
| 0.1.x (latest) | :white_check_mark: |
| older releases | :x: |

## Reporting a Vulnerability

Please **do not** open a public GitHub issue for security-sensitive reports.

Report privately to **lonelyangel.jcw@gmail.com** with:

- affected version / commit
- a minimal reproduction (CQL statement, datasource, expected vs actual behavior)
- impact assessment if known

You can expect an acknowledgement within 72 hours. We will coordinate a fix and
a disclosure timeline with you.

## Security Notes

- `c_script(...)` passes a Painless script to Elasticsearch. It is a trusted
  escape hatch: never build it from untrusted user input.
- Credentials are read from JVM system properties or environment variables.
  Prefer environment variables or a secret manager; do not commit them.
- Non-JDBC parameter values are escaped before being interpolated into CQL.
  JDBC datasources bind values through `PreparedStatement`.
- Do not enable debug logging of raw requests in production environments that
  contain sensitive data.
