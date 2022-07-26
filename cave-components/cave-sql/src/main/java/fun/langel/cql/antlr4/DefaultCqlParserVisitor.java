package fun.langel.cql.antlr4;

import com.jayway.jsonpath.internal.filter.LogicalOperator;
import fun.langel.cql.antlr4.CqlParser.SqlStatementContext;
import fun.langel.cql.exception.SqlException;
import fun.langel.cql.node.Expr;
import fun.langel.cql.node.Node;
import fun.langel.cql.node.Terminal;
import fun.langel.cql.statement.*;
import fun.langel.cql.util.StringUtil;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

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
            for (int idx = 0, len = ctx.getChildCount(); idx < len; idx++) {
                ParseTree pt = ctx.getChild(idx);
                if (pt instanceof CqlParser.SimpleSelectContext) {
                    Node node = visitSimpleSelect((CqlParser.SimpleSelectContext) pt);
                    System.out.println(node);
                }
            }
        } else if (Objects.nonNull(ctx.deleteStatement())) {
            return new DeleteStatement();
        }
        return new EmptyStatement();
    }

    @Override
    public Node visitSimpleSelect(CqlParser.SimpleSelectContext ctx) {

        for (int idx = 0, len = ctx.getChildCount(); idx < len; idx++) {
            ParseTree pt = ctx.getChild(idx);
            if (pt instanceof CqlParser.QuerySpecificationContext) {
                Node node = visitQuerySpecification((CqlParser.QuerySpecificationContext) pt);
                System.out.println(node);
            }
        }
        return super.visitSimpleSelect(ctx);
    }

    @Override
    public Node visitQuerySpecification(CqlParser.QuerySpecificationContext ctx) {

        for (int idx = 0, len = ctx.getChildCount(); idx < len; idx++) {
            ParseTree pt = ctx.getChild(idx);
            if (pt instanceof TerminalNode) {
                System.out.println(pt.getText());
            } else if (pt instanceof CqlParser.FromClauseContext) {
                visitFromClause((CqlParser.FromClauseContext) pt);
            }
        }
        return super.visitQuerySpecification(ctx);
    }

    @Override
    public Node visitFromClause(CqlParser.FromClauseContext ctx) {
        CqlParser.ExpressionContext wExpr = ctx.whereExpr;
        if (wExpr instanceof CqlParser.LogicalExpressionContext) {
            CqlParser.LogicalExpressionContext logicWExpr = (CqlParser.LogicalExpressionContext) wExpr;
            CqlParser.LogicalOperatorContext operator = logicWExpr.logicalOperator();

        }
        return super.visitFromClause(ctx);
    }

    private Expr visitExpr(CqlParser.LogicalExpressionContext expression) {
        if (expression.children.size() == 3) {
            ParseTree left = expression.getChild(0);
            ParseTree logical = expression.getChild(1);
            ParseTree right = expression.getChild(2);
            if (left instanceof CqlParser.LogicalExpressionContext) {

            }
            if (right instanceof CqlParser.LogicalExpressionContext) {

            }
        } else {
            throw new SqlException("Illegal sql logical expression grammar!");
        }
        return new Terminal();
    }

    @Override
    public Node visitQuerySpecificationNointo(CqlParser.QuerySpecificationNointoContext ctx) {
        return super.visitQuerySpecificationNointo(ctx);
    }

    @Override
    public Node visitQueryExpression(CqlParser.QueryExpressionContext ctx) {
        return super.visitQueryExpression(ctx);
    }

    @Override
    public Node visitSqlStatement(SqlStatementContext ctx) {
        return super.visitSqlStatement(ctx);
    }

}
