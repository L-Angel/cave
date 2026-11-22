## Summary

<!-- What does this PR change and why? -->

## Type of change

- [ ] Bug fix
- [ ] New feature / datasource capability
- [ ] Parser / CQL syntax change
- [ ] Documentation
- [ ] Build / CI
- [ ] Refactor / cleanup
- [ ] Breaking change

## Datasources affected

- [ ] Elasticsearch
- [ ] MongoDB
- [ ] MySQL / JDBC
- [ ] Parser only

## How was it verified?

<!-- Commands, tests, and manual verification. -->

```bash
./mvnw -B test
```

## Checklist

- [ ] I have added or updated tests.
- [ ] `./mvnw -B test` passes locally.
- [ ] Unsupported syntax fails explicitly (no silent fallback).
- [ ] README / support matrix is updated if behavior changed.
- [ ] No credentials or secrets are included.
- [ ] Changes are compatible with Java 8 unless stated otherwise.
