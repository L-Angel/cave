package fun.langel.cql;

import fun.langel.cql.antlr4.CqlLexer;
import fun.langel.cql.antlr4.CqlParser;
import fun.langel.cql.antlr4.DefaultCqlParserVisitor;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.ElasticSearchQDL;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 11:31
 **/
public class Cql {

    // private static final FrameworkConfig CALCITE_CONFIG = Frameworks.newConfigBuilder()
    //         .parserConfig(Calcite.configBuilder()
    //                 .setLex(Lex.CAVE_SQL)
    //                 .setParserFactory(SqlParserImpl.FACTORY)
    //                 .setConformance(SqlConformanceEnum.MYSQL_5)
    //                 .build())
    //         .build();
    //
    // /**
    //  * @param sql    Standard Mysql 5.7 grammar.
    //  * @param target The target language type.
    //  * @return System Di·alect
    //  * @throws SqlParseException
    //  */
    // public static Dialect parse(String sql, Language target) throws SqlParseException {
    //     if (target == Language.MYSQL) {
    //         return new Mysql(sql);
    //     }
    //
    //     SqlNode sqlNode = SqlParser.create(sql, CALCITE_CONFIG.getParserConfig()).parseStmt();
    //     SqlKind kind = sqlNode.getKind();
    //     SqlResolver resolver;
    //     if (kind == SqlKind.SELECT) {
    //         resolver = new SelectSqlResolver();
    //     } else if (kind == SqlKind.ORDER_BY) {
    //         resolver = new OrderBySqlResolver();
    //     } else {
    //         resolver = new DefaultSqlResolver();
    //     }
    //     Node node = resolver.resolve(sqlNode);
    //
    //     final ElasticSearchQDLDialectResolver dialectResolver = new ElasticSearchQDLDialectResolver();
    //     SearchRequest sr = dialectResolver.resolve((Select) node);
    //     System.out.println(sr);
    //     return null;
    // }


    public static Dialect parse(final String sql, Language target) throws IOException {
        CqlLexer lexer = new CqlLexer(CharStreams.fromString(sql.toUpperCase()));
        CqlParser parser = new CqlParser(new CommonTokenStream(lexer));
        DefaultCqlParserVisitor visitor = new DefaultCqlParserVisitor();
        visitor.visit(parser.root());
        return new ElasticSearchQDL();
    }
}
