package fun.langel.cql.calcite;

import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/29 9:15 下午
 **/
public class Calcite {

    private FrameworkConfig config;

    public Calcite() {

    }

    public Calcite create() {
        final FrameworkConfig config = Frameworks.newConfigBuilder()
                .parserConfig(SqlParser.configBuilder()
                        .setLex(Lex.MYSQL)
                        .setParserFactory(SqlParserImpl.FACTORY)
                        .setCaseSensitive(false)
                        .setQuoting(Quoting.BACK_TICK)
                        .setQuotedCasing(Casing.TO_UPPER)
                        .setUnquotedCasing(Casing.TO_UPPER)
                        .setConformance(SqlConformanceEnum.MYSQL_5)
                        .build())
                .build();    }
}
