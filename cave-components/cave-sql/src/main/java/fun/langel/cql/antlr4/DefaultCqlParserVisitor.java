package fun.langel.cql.antlr4;

import fun.langel.cql.antlr4.CqlParser.SqlStatementContext;
import fun.langel.cql.node.Node;
import fun.langel.cql.statement.*;
import fun.langel.cql.util.StringUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/18 17:20
 **/
public class DefaultCqlParserVisitor extends CqlParserBaseVisitor<Node> implements CqlParserVisitor<Node> {

    @Override
    public Statements visitSqlStatements(CqlParser.SqlStatementsContext ctx) {
        List<SqlStatementContext> ctxs = ctx.sqlStatement();
        final List<Statement> statements = new LinkedList<>();
        for (SqlStatementContext c : ctxs) {
            if (c.dmlStatement() != null) {
                Statement dml = visitDmlStatement(c.dmlStatement());
                if (!(dml instanceof EmptyStatement)) {
                    statements.add(dml);
                }
            }
        }
        return new Statements(statements);
    }

    @Override
    public Statement visitDmlStatement(CqlParser.DmlStatementContext ctx) {
        if (Objects.nonNull(ctx.selectStatement())) {
            return new SelectStatement();
        } else if (Objects.nonNull(ctx.deleteStatement())) {
            return new DeleteStatement();
        }
        return new EmptyStatement();
    }

    @Override
    public Node visitSqlStatement(SqlStatementContext ctx) {
        return super.visitSqlStatement(ctx);
    }

}
