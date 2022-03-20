package fun.langel.cql;

import fun.langel.cql.calcite.Calcite;
import fun.langel.cql.calcite.config.Lex;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.Mysql;
import fun.langel.cql.node.NodeKind;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;

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
     * @return System Dialect
     * @throws SqlParseException
     */
    public static Dialect parse(String sql, Language target) throws SqlParseException {
        if (target == Language.MYSQL) {
            return new Mysql(sql);
        }
        SqlNode sqlNode = SqlParser.create(sql, CALCITE_CONFIG.getParserConfig()).parseStmt();
        NodeKind kind = NodeKind.of(sqlNode);
        if (NodeKind.SELECT.equals(kind)) {
            SqlSelect select = null;
            if (kind.ordered()) {
                select = (SqlSelect) ((SqlOrderBy) sqlNode).getOperandList().get(0);
            } else {
                select = (SqlSelect) sqlNode;
            }
            System.out.println(select);
        }
        return null;
    }


}
