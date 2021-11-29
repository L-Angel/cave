package fun.langel.cql.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/9 3:51 下午
 **/
@RunWith(JUnit4.class)
public class AntlrTest {


    @Test
    public void parseTest() {

        String sql = "select ship.gun_power, ship_info.* FROM " +
                " ( " +
                " select s.name as ship_name, sum(g.power) as gun_power, max(callibr) as max_callibr " +
                " from " +
                " ships s inner join ships_guns sg on s.id = sg.ship_id inner join guns g on g.id = sg.guns_id " +
                " group by s.name " +
                " ) ship_power " +
                " inner join " +
                " ( " +
                " select s.name as ship_name, sc.class_name, sc.tonange, sc.max_length, sc.start_build, sc.max_guns_size " +
                " from " +
                "  ships s inner join ship_class sc on s.class_id = sc.id " +
                " ) ship_info using (ship_name) " +
                " order by ship_power.ship_name;";

        String sql2 = "Select 'abc' as a, `hahah` as c  From a aS table;";
//        CSqlLexer lexer = new CSqlLexer(CharStreams.fromString(sql2));
        CSqlLexer lexer = new CSqlLexer(CharStreams.fromString(sql));
        CSqlParser parser = new CSqlParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.sqlStatements();

//        CSqlParserBaseVisitor visitor = new CSqlParserBaseVisitor();
//        tree.accept(visitor);
        ParseTreeWalker walker = new ParseTreeWalker();
        CSqlWalkListener listener = new CSqlWalkListener();
        walker.walk(listener, tree);
        System.out.println(tree.toStringTree(parser)); //打印规则数

    }
}
