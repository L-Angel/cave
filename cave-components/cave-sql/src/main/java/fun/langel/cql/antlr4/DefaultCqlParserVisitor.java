package fun.langel.cql.antlr4;

import fun.langel.cql.antlr4.CqlParser.SqlStatementContext;
import fun.langel.cql.enums.Order;
import fun.langel.cql.exception.SqlException;
import fun.langel.cql.exception.UnsupportCqlFunctionException;
import fun.langel.cql.extension.CqlParseExtension;
import fun.langel.cql.extension.DefaultCqlParseExtension;
import fun.langel.cql.node.*;
import fun.langel.cql.node.func.*;
import fun.langel.cql.node.operator.LogicalOperator;
import fun.langel.cql.node.operator.Operator;
import fun.langel.cql.node.operator.RelOperator;
import fun.langel.cql.spi.Loader;
import fun.langel.cql.statement.EmptyStatement;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.statement.Statement;
import fun.langel.cql.statement.Statements;
import fun.langel.cql.statement.impl.DeleteStatementImpl;
import fun.langel.cql.statement.impl.SelectStatementImpl;
import fun.langel.cql.util.IntegerUtil;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.security.acl.Group;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/18 17:20
 **/
public class DefaultCqlParserVisitor extends CqlParserBaseVisitor<Node> implements CqlParserVisitor<Node> {

    private final CqlParseExtension cqlParseExtension;

    public DefaultCqlParserVisitor() {
        CqlParseExtension extension = Loader.loadSingle(CqlParseExtension.class);
        this.cqlParseExtension = extension == null ? new DefaultCqlParseExtension() : extension;
    }

    @Override
    public Statements visit(ParseTree tree) {
        return visitRoot((CqlParser.RootContext) tree);
    }

    @Override
    public Statements visitRoot(CqlParser.RootContext ctx) {
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
                final List<Column> columns = new LinkedList<>();
                for (int i2 = 0, len2 = pt.getChildCount(); i2 < len2; i2++) {
                    ParseTree p2 = pt.getChild(i2);
                    if (p2 instanceof CqlParser.SelectColumnElementContext) {
                        Column col = visitSelectColumnElement((CqlParser.SelectColumnElementContext) p2);
                        if (col != null) {
                            columns.add(col);
                        }
                    } else if (p2 instanceof CqlParser.SelectFunctionElementContext) {
                        Column col = visitSelectFunctionElement((CqlParser.SelectFunctionElementContext) p2);
                        if (col != null) {
                            columns.add(col);
                        }
                    }
                }
                statement.setColumns(this.cqlParseExtension.afterSelectElements(columns));
            } else if (pt instanceof CqlParser.FromClauseContext) {
                statement.setFrom(visitFromClause((CqlParser.FromClauseContext) pt));
            } else if (pt instanceof CqlParser.OrderByClauseContext) {
                statement.setOrderBy(visitOrderByClause((CqlParser.OrderByClauseContext) pt));
            } else if (pt instanceof CqlParser.LimitClauseContext) {
                statement.setLimit(visitLimitClause((CqlParser.LimitClauseContext) pt));
            } else if (pt instanceof CqlParser.GroupByClauseContext) {
                statement.setGroupBy(visitGroupByClause((CqlParser.GroupByClauseContext) pt));
            }
        }
        return statement;
    }

    @Override
    public Column visitSelectFunctionElement(CqlParser.SelectFunctionElementContext ctx) {
        String alias = null;
        if (ctx.getChildCount() == 2 && ctx.getChild(1) instanceof CqlParser.UidContext) {
            alias = ctx.getChild(1).getText();
        }
        if (ctx.getChildCount() == 3 && "as".equalsIgnoreCase(ctx.getChild(1).getText())) {
            alias = ctx.getChild(2).getText();
        }
        if (ctx.getChild(0) instanceof CqlParser.AggregateFunctionCallContext) {
            return Column.of(visitAggregateFunctionCall((CqlParser.AggregateFunctionCallContext) ctx.getChild(0)), alias);
        } else if (ctx.getChild(0) instanceof CqlParser.SpecificFunctionCallContext) {

            return Column.of(visitSpecificFunctionCall((CqlParser.SpecificFunctionCallContext) ctx.getChild(0)), alias);
        }
        return null;
    }

    @Override
    public Function visitSpecificFunctionCall(CqlParser.SpecificFunctionCallContext ctx) {
        if (ctx.getChild(0) instanceof CqlParser.C_keyvalueFunctionCallContext) {
            return visitC_keyvalueFunctionCall((CqlParser.C_keyvalueFunctionCallContext) ctx.getChild(0));
        }
        return null;
    }

    @Override
    public C_KeyValue visitC_keyvalueFunctionCall(CqlParser.C_keyvalueFunctionCallContext ctx) {
        return C_KeyValue.of(Column.of(ctx.getChild(2).getText()), ctx.getChild(4).getText());
    }

    @Override
    public Function visitAggregateFunctionCall(CqlParser.AggregateFunctionCallContext ctx) {
        if (ctx.getChild(0) instanceof CqlParser.AggregateWindowedFunctionContext) {
            return visitAggregateWindowedFunction((CqlParser.AggregateWindowedFunctionContext) ctx.getChild(0));
        }
        return null;
    }

    @Override
    public Function visitAggregateWindowedFunction(CqlParser.AggregateWindowedFunctionContext ctx) {
        String functionName = ctx.getChild(0).getText();

        for (int idx = 1, len = ctx.getChildCount(); idx < len; idx++) {
            ParseTree pt = ctx.getChild(idx);
            if ("avg".equalsIgnoreCase(functionName) && (pt instanceof CqlParser.FunctionArgContext)) {
                return Avg.of(Column.of(pt.getText()));
            }
        }
        if ("count".equalsIgnoreCase(functionName)) {
            return Count.of(Column.of(ctx.getChild(2).getText()));
        }
        return null;
    }


    @Override
    public GroupBy visitGroupByClause(CqlParser.GroupByClauseContext ctx) {
        GroupBy groupBy = GroupBy.ofEmpty();
        for (int idx = 0; idx < ctx.getChildCount(); idx++) {
            ParseTree pt = ctx.getChild(idx);
            if (pt instanceof CqlParser.GroupByItemContext) {
                groupBy.add(visitGroupByItem((CqlParser.GroupByItemContext) pt));
            }

        }
        return groupBy;
    }

    @Override
    public Column visitGroupByItem(CqlParser.GroupByItemContext ctx) {
        return Column.of(ctx.getText());
    }

    @Override
    public Limit visitLimitClause(CqlParser.LimitClauseContext ctx) {
        String limit = ctx.limit == null ? null : ctx.limit.getText();
        String offset = ctx.offset == null ? null : ctx.offset.getText();
        return Limit.of(IntegerUtil.tryParse(offset), IntegerUtil.tryParse(limit));
    }

    @Override
    public OrderBy visitOrderByClause(CqlParser.OrderByClauseContext ctx) {
        List<CqlParser.OrderByExpressionContext> os = ctx.orderByExpression();
        final List<Column> columns = new LinkedList<>();
        for (int idx = 0, len = os.size(); idx < len; idx++) {
            CqlParser.OrderByExpressionContext exp = os.get(idx);
            columns.add(Column.of(exp.getChild(0).getText(), null, Order.of(exp.order == null ? null : exp.order.getText())));
        }
        return OrderBy.of(columns);
    }

    @Override
    public Column visitSelectColumnElement(CqlParser.SelectColumnElementContext ctx) {
        return Column.of(ctx.fullColumnName().getText(), ctx.uid() == null ? null : ctx.uid().getText());
    }

    @Override
    public From visitFromClause(CqlParser.FromClauseContext ctx) {
        CqlParser.ExpressionContext wExpr = ctx.whereExpr;
        CqlParser.TableSourcesContext sources = ctx.tableSources();
        Expr where = visitExpr(wExpr);
        where = this.cqlParseExtension.afterWhere(where);
        List<Table> tables = new LinkedList<>();
        for (int idx = 0, len = sources.getChildCount(); idx < len; idx++) {
            ParseTree pt = sources.getChild(idx);
            if (pt instanceof CqlParser.TableSourceBaseContext) {
                Table t = visitTableSourceBase((CqlParser.TableSourceBaseContext) pt);
                if (t != null) {
                    tables.add(t);
                }
            }
        }
        return new From(tables, where);
    }

    @Override
    public Table visitTableSourceBase(CqlParser.TableSourceBaseContext ctx) {
        ParseTree p = ctx.getChild(0);
        if (p instanceof CqlParser.AtomTableItemContext) {
            CqlParser.AtomTableItemContext t = (CqlParser.AtomTableItemContext) p;
            return new Table(t.getChild(0).getText(), t.alias == null ? null : t.alias.getText());
        }
        return null;
    }

    private Expr visitExpr(ParseTree tree) {
        if (tree == null) {
            return null;
        }
        if (tree instanceof CqlParser.PredicateExpressionContext) {
            return visitExpr(tree.getChild(0));
        }
        if (tree instanceof CqlParser.LikePredicateContext) {
            return visitLikePredicate((CqlParser.LikePredicateContext) tree);
        }
        if (tree instanceof CqlParser.InPredicateContext) {
            return visitInPredicate((CqlParser.InPredicateContext) tree);
        }
        // if (tree instanceof CqlParser.LikePredicateContext) {
        //     visitLikePredicate((CqlParser.LikePredicateContext) tree);
        // }
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
        }
        if (tree.getChildCount() == 1 && tree.getChild(0) instanceof CqlParser.CqlWhereFunctionAtomContext) {
            return visitCqlWhereFunctionAtom((CqlParser.CqlWhereFunctionAtomContext) tree.getChild(0));
        } else {
            throw new SqlException("Illegal cql expression grammar! " + tree.getText());
        }
    }

    @Override
    public Expr visitCqlWhereFunctionAtom(CqlParser.CqlWhereFunctionAtomContext ctx) {
        if (ctx.getChildCount() == 1) {
            if (ctx.getChild(0) instanceof CqlParser.C_existsExpressionAtomContext) {
                CqlParser.FullColumnNameContext fullCol = (CqlParser.FullColumnNameContext) ctx.getChild(0).getChild(2);
                return C_Exists.of(Column.of(fullCol.getText()));
            } else if (ctx.getChild(0) instanceof CqlParser.C_scriptExpressionAtomContext) {
                TerminalNode terminal = (TerminalNode) ctx.getChild(0).getChild(2);
                return C_Script.of(terminal.toString());
            }
            throw new UnsupportCqlFunctionException(ctx.getChild(0).getText());
        }
        throw new IllegalArgumentException("cql where function must have 1 parameter");
    }

    @Override
    public Expr visitInPredicate(CqlParser.InPredicateContext ctx) {
        CqlParser.ExpressionsContext expressionsContext = ctx.expressions();
        Column col = Column.of(ctx.predicate().getText());
        List<CqlParser.ExpressionContext> exps = expressionsContext.expression();

        final List<Value> values = new LinkedList<>();
        for (CqlParser.ExpressionContext exp : exps) {
            if (exp instanceof TerminalNode) {
                continue;
            }
            values.add(Value.of(exp.getText()));
        }

        return new ExprImpl(col, ctx.NOT() == null ? RelOperator.IN : RelOperator.NOT_IN, Range.of(values));
    }

    @Override
    public Expr visitLikePredicate(CqlParser.LikePredicateContext ctx) {
        return new ExprImpl(Column.of(ctx.predicate(0).getText()), ctx.NOT() == null ? RelOperator.LIKE : RelOperator.NOT_LIKE, Value.of(ctx.getChild(ctx.getChildCount() - 1).getText()));
    }

    @Override
    public Expr visitBinaryComparisonPredicate(CqlParser.BinaryComparisonPredicateContext node) {
        return new ExprImpl(Column.of(node.left.getText()), RelOperator.of(node.comparisonOperator().getText()),
                new Value(node.right.getText(), "null".equalsIgnoreCase(node.right.getText()),
                        false));
    }

    @Override
    public Expr visitIsNullPredicate(CqlParser.IsNullPredicateContext ctx) {
        String value = ctx.getChild(2).getText();
        return new ExprImpl(Column.of(ctx.getChild(0).getText()), RelOperator.IS, new Value(null, "null".equalsIgnoreCase(value), "notnull".equalsIgnoreCase(value)));
    }

    @Override
    public Expr visitBetweenPredicate(CqlParser.BetweenPredicateContext ctx) {
        return new ExprImpl(Column.of(ctx.getChild(0).getText()), new Value(ctx.getChild(2).getText(), false, false), new Value(ctx.getChild(4).getText(), false, false));
    }


}
