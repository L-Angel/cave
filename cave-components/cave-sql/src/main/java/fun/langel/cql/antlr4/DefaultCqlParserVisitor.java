package fun.langel.cql.antlr4;

import fun.langel.cql.antlr4.CqlParser.SqlStatementContext;
import fun.langel.cql.exception.SqlException;
import fun.langel.cql.node.*;
import fun.langel.cql.node.operator.LogicalOperator;
import fun.langel.cql.node.operator.Operator;
import fun.langel.cql.node.operator.RelOperator;
import fun.langel.cql.statement.*;
import fun.langel.cql.statement.impl.DeleteStatementImpl;
import fun.langel.cql.statement.impl.SelectStatementImpl;
import fun.langel.cql.util.StringUtil;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
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
    public Node visit(ParseTree tree) {
        return visitRoot((CqlParser.RootContext) tree);
    }

    @Override
    public Node visitRoot(CqlParser.RootContext ctx) {
        return visitSqlStatements(ctx.sqlStatements());
    }

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
                    return visitSimpleSelect((CqlParser.SimpleSelectContext) pt);
                }
            }
        } else if (Objects.nonNull(ctx.deleteStatement())) {
            return new DeleteStatementImpl();
        }
        return new EmptyStatement();
    }

    @Override
    public SelectStatement visitSimpleSelect(CqlParser.SimpleSelectContext ctx) {

        for (int idx = 0, len = ctx.getChildCount(); idx < len; idx++) {
            ParseTree pt = ctx.getChild(idx);
            if (pt instanceof CqlParser.QuerySpecificationContext) {
                return visitQuerySpecification((CqlParser.QuerySpecificationContext) pt);
            }
        }
        return new EmptyStatement();
    }

    @Override
    public SelectStatement visitQuerySpecification(CqlParser.QuerySpecificationContext ctx) {

        SelectStatementImpl statement = new SelectStatementImpl();
        for (int idx = 0, len = ctx.getChildCount(); idx < len; idx++) {
            ParseTree pt = ctx.getChild(idx);
            if (pt instanceof TerminalNode) {
                System.out.println(pt.getText());
            } else if (pt instanceof CqlParser.SelectElementsContext) {
                System.out.println(pt);
            } else if (pt instanceof CqlParser.FromClauseContext) {
                statement.setFrom(visitFromClause((CqlParser.FromClauseContext) pt));
            }
        }
        return statement;
    }

    @Override
    public From visitFromClause(CqlParser.FromClauseContext ctx) {
        CqlParser.ExpressionContext wExpr = ctx.whereExpr;
        CqlParser.TableSourcesContext source = ctx.tableSources();
        Expr where = null;
        Table table = null;
        if (wExpr instanceof CqlParser.LogicalExpressionContext) {
            CqlParser.LogicalExpressionContext logicWExpr = (CqlParser.LogicalExpressionContext) wExpr;
            where = visitExpr(logicWExpr);
        }
        if (source != null) {
            table = new Table(source.getText(), null);
        }
        return new From(table, where);
    }

    private Expr visitExpr(ParseTree tree) {
        if (tree instanceof CqlParser.PredicateExpressionContext) {
            return visitExpr(tree.getChild(0));
        }
        if (tree instanceof CqlParser.BinaryComparisonPredicateContext) {
            return visitBinaryComparisonPredicate((CqlParser.BinaryComparisonPredicateContext) tree);
        }
        if (tree instanceof CqlParser.IsNullPredicateContext) {
            return visitIsNullPredicate((CqlParser.IsNullPredicateContext) tree);
        }
        if (tree instanceof CqlParser.BetweenPredicateContext) {
            return visitBetweenPredicate((CqlParser.BetweenPredicateContext) tree);
        }
        if (tree.getChildCount() == 3) {
            ParseTree left = tree.getChild(0);
            ParseTree logical = tree.getChild(1);
            ParseTree right = tree.getChild(2);
            Node l = visitExpr(left);
            Operator operator = LogicalOperator.of(logical.getText());
            Node r = visitExpr(right);
            return new ExprImpl(l, operator, r);
        } else {
            throw new SqlException("Illegal sql expression grammar!");
        }
    }

    @Override
    public Expr visitBinaryComparisonPredicate(CqlParser.BinaryComparisonPredicateContext node) {
        return new ExprImpl(new Column(node.left.getText(), null), RelOperator.of(node.comparisonOperator().getText()), new Value(node.right.getText(), false, false));
    }

    @Override
    public Expr visitIsNullPredicate(CqlParser.IsNullPredicateContext ctx) {
        String value = ctx.getChild(2).getText();
        return new ExprImpl(new Column(ctx.getChild(0).getText(), null), RelOperator.IS, new Value(null, "null".equalsIgnoreCase(value), "notnull".equalsIgnoreCase(value)));
    }

    @Override
    public Expr visitBetweenPredicate(CqlParser.BetweenPredicateContext ctx) {
        return new ExprImpl(new Column(ctx.getChild(0).getText(), null), new Value(ctx.getChild(2).getText(), false, false), new Value(ctx.getChild(4).getText(), false, false));
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
