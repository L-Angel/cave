package fun.langel.cql.dialect;

import fun.langel.cql.Language;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 16:45
 **/
public class Mysql implements Dialect {

    private final String origin;

    public Mysql(final String origin) {
        this.origin = origin;
    }

    @Override
    public Language type() {
        return Language.MYSQL;
    }

    @Override
    public String asString() {
        return this.origin;
    }
}
