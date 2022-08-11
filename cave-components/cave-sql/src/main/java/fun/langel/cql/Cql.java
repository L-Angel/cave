package fun.langel.cql;

import fun.langel.cql.antlr4.CqlLexer;
import fun.langel.cql.antlr4.CqlParser;
import fun.langel.cql.antlr4.DefaultCqlParserVisitor;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.parameter.ParameterResolver;
import fun.langel.cql.resolve.dialect.ElasticSearchQDLDialectResolver;
import fun.langel.cql.statement.DeleteStatement;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.statement.Statement;
import fun.langel.cql.statement.Statements;
import fun.langel.cql.util.Pair;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/18 11:31
 **/
public class Cql {

    private final static ParameterResolver parameterResolver = new ParameterResolver();

    public static <D> Dialect<D> parse(final String sql, Language target) {
        Statements statements = parse(sql);
        for (Statement stat : statements) {
            if (stat instanceof SelectStatement) {
                if (target == Language.ELASTIC_SEARCH) {
                    return (Dialect<D>) new ElasticSearchQDLDialectResolver().resolve((SelectStatement) stat);
                }
            }
        }
        return null;
    }

    public static Statements parse(final String sql) {
        CqlLexer lexer = new CqlLexer(CharStreams.fromString(sql));
        CqlParser parser = new CqlParser(new CommonTokenStream(lexer));
        DefaultCqlParserVisitor visitor = new DefaultCqlParserVisitor();
        return visitor.visit(parser.root());
    }

    public static SelectStatement parseSelectStatement(final String sql) {
        Statements statements = parse(sql);
        for (Statement statement : statements) {
            if (statement instanceof SelectStatement) {
                return (SelectStatement) statement;
            }
        }
        return null;
    }

    public static DeleteStatement parseDeleteStatement(final String sql) {
        Statements statements = parse(sql);
        for (Statement statement : statements) {
            if (statement instanceof DeleteStatement) {
                return (DeleteStatement) statement;
            }
        }
        return null;
    }

}
