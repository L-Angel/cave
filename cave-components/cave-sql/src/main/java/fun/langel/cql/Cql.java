package fun.langel.cql;

import fun.langel.cql.calcite.Calcite;
import fun.langel.cql.calcite.config.Lex;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.Mysql;
import fun.langel.cql.node.Node;
import fun.langel.cql.node.Select;
import fun.langel.cql.resolve.SqlResolver;
import fun.langel.cql.resolve.dialect.ElasticSearchQDLDialectResolver;
import fun.langel.cql.resolve.sql.DefaultSqlResolver;
import fun.langel.cql.resolve.sql.OrderBySqlResolver;
import fun.langel.cql.resolve.sql.SelectSqlResolver;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.elasticsearch.action.search.SearchRequest;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 11:31
 **/
public class Cql {

    private static final FrameworkConfig CALCITE_CONFIG = Frameworks.newConfigBuilder()
            .parserConfig(Calcite.configBuilder()
                    .setLex(Lex.CAVE_SQL)
                    .setParserFactory(SqlParserImpl.FACTORY)
                    .setConformance(SqlConformanceEnum.MYSQL_5)
                    .build())
            .build();

    /**
     * @param sql    Standard Mysql 5.7 grammar.
     * @param target The target language type.
     * @return System Di·alect
     * @throws SqlParseException
     */
    public static Dialect parse(String sql, Language target) throws SqlParseException {
        if (target == Language.MYSQL) {
            return new Mysql(sql);
        }

        SqlNode sqlNode = SqlParser.create(sql, CALCITE_CONFIG.getParserConfig()).parseStmt();
        SqlKind kind = sqlNode.getKind();
        SqlResolver resolver;
        if (kind == SqlKind.SELECT) {
            resolver = new SelectSqlResolver();
        } else if (kind == SqlKind.ORDER_BY) {
            resolver = new OrderBySqlResolver();
        } else {
            resolver = new DefaultSqlResolver();
        }
        Node node = resolver.resolve(sqlNode);

        final ElasticSearchQDLDialectResolver dialectResolver = new ElasticSearchQDLDialectResolver();
        SearchRequest sr = dialectResolver.resolve((Select) node);
        System.out.println(sr);
        return null;
    }

}
