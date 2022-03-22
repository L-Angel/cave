package fun.langel.cql.calcite;

import fun.langel.cql.Cql;
import fun.langel.cql.Language;
import fun.langel.cql.calcite.config.Lex;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/29 9:16 下午
 **/
@RunWith(JUnit4.class)
public class CalciteTest {

    @Test
    public void testSqlParse() throws SqlParseException {
        final String sql1 = "select * from table1 where field1=${value1} and field2=${value2} order by ${value1}";
        final String sql2 = "select * from table2 where field1 = 1  order by field2 desc limit 1,100";
        SchemaPlus rootSchema = Frameworks.createRootSchema(true);

        final FrameworkConfig config = Frameworks.newConfigBuilder()
                .parserConfig(Calcite.configBuilder()
                        .setLex(Lex.CAVE_SQL)
                        .setParserFactory(SqlParserImpl.FACTORY)
                        .setConformance(SqlConformanceEnum.MYSQL_5)
                        .build())
                .build();

//        SqlParser parser = SqlParser.create(sql1, config.getParserConfig());
//        SqlNode sqlNode = parser.parseStmt();
//        System.out.println(sqlNode);

        SqlParser parser2 = SqlParser.create(sql2, config.getParserConfig());
        SqlNode sqlNode2 = parser2.parseStmt();
        System.out.println(sqlNode2);
        System.out.println(parser2);
    }

    @Test
    public void cqlParseTest() throws SqlParseException {
        final String sql = "select field1 as f1,field2 f2 from table2 where field1 = 1 and field2 = 'value2' and " +
                "gmt_created between '2021-01-01 00:00:00' and '2022-01-01 00:00:00' and (field3 > 12 or field3=44) and field4 in(1,2,3,4) order by field2 asc limit 1,100";
        Cql.parse(sql, Language.QDL_ELASTIC_SEARCH);


    }
}
