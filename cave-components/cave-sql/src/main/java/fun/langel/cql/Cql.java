package fun.langel.cql;

import fun.langel.cql.antlr4.CqlLexer;
import fun.langel.cql.antlr4.CqlParser;
import fun.langel.cql.antlr4.DefaultCqlParserVisitor;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.exception.SqlException;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.parameter.ParameterResolver;
import fun.langel.cql.resolve.dialect.ElasticSearchQDLDialectResolver;
import fun.langel.cql.statement.DeleteStatement;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.statement.Statement;
import fun.langel.cql.statement.Statements;
import fun.langel.cql.util.Pair;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

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

    /**
     * ANTLR's default listener only prints to stderr and then performs error
     * recovery, which silently hands back a partial AST. Fail loudly instead.
     */
    private static final BaseErrorListener ERROR_LISTENER = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                int line, int charPositionInLine, String msg,
                                RecognitionException e) {
            throw new SqlException("CQL syntax error at line " + line + ":" + charPositionInLine + " - " + msg);
        }
    };

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
        if (sql == null) {
            throw new SqlException("CQL statement must not be null.");
        }
        CqlLexer lexer = new CqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ERROR_LISTENER);
        CqlParser parser = new CqlParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(ERROR_LISTENER);
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
        throw new SqlException("No select statement found in CQL: " + sql);
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
