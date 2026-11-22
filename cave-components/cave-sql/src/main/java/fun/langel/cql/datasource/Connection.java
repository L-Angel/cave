package fun.langel.cql.datasource;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/7 06:00
 **/
public interface Connection {

    Session getSession();

    /**
     * Releases the underlying physical connection when this connection owns one.
     * Shared/long-lived connections (ES, Mongo) can keep the no-op default.
     */
    default void close() {
    }
}
