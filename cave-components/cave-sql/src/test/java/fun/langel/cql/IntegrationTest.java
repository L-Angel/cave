package fun.langel.cql;

/**
 * JUnit category marker for tests that require live external services
 * (Elasticsearch, MongoDB, MySQL). They are excluded from the default test run;
 * use {@code ./mvnw -Pintegration-tests test} to run them.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public interface IntegrationTest {
}
