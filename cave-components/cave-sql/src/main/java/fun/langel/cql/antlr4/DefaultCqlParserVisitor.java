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
import fun.langel.cql.util.StringUtil;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collections;
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
        if (ctx.sqlStatements() == null) {
            return new Statements(Collections.emptyList());
        }
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
            throw new UnsupportedOperationException("UNION / parenthesized SELECT is not supported yet.");
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
        for (CqlParser.SelectSpecContext spec : ctx.selectSpec()) {
            String text = spec.getText();
            if ("distinct".equalsIgnoreCase(text) || "distinctrow".equalsIgnoreCase(text)) {
                statement.setDistinct(true);
            }
        }
        if (ctx.windowClause() != null) {
            throw new UnsupportedOperationException("Window expressions are not supported yet.");
        }

        for (int idx = 0, len = ctx.getChildCount(); idx < len; idx++) {
            ParseTree pt = ctx.getChild(idx);
            if (pt instanceof TerminalNode) {
                // SELECT keyword and similar tokens.
            } else if (pt instanceof CqlParser.SelectElementsContext) {
                final List<Column> columns = new LinkedList<>();
                boolean star = false;
                for (int i2 = 0, len2 = pt.getChildCount(); i2 < len2; i2++) {
                    ParseTree p2 = pt.getChild(i2);
                    if (p2 instanceof TerminalNode) {
                        if ("*".equals(p2.getText())) {
                            star = true;
                        }
                        continue;
                    }
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
                    } else if (p2 instanceof CqlParser.SelectStarElementContext) {
                        star = true;
                    } else if (p2 instanceof CqlParser.SelectExpressionElementContext) {
                        throw new UnsupportedOperationException("SELECT expressions are not supported yet: " + p2.getText());
                    } else {
                        throw new UnsupportedOperationException("Unsupported SELECT element: " + p2.getText());
                    }
                }
                if (star && !columns.isEmpty()) {
                    throw new UnsupportedOperationException("Mixing '*' with explicit columns is not supported yet.");
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
            } else if (pt instanceof CqlParser.HavingClauseContext) {
                statement.setHaving(normalizeLogical(visitExpr(((CqlParser.HavingClauseContext) pt).havingExpr)));
            } else if (pt instanceof CqlParser.WindowClauseContext
                    || pt instanceof CqlParser.SelectSpecContext) {
                // Window clauses are rejected above; SELECT modifiers are handled before the loop.
            } else {
                throw new UnsupportedOperationException("Unsupported SELECT clause: " + pt.getText());
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
        } else if (ctx.getChild(0) instanceof CqlParser.ScalarFunctionCallContext) {
            return Column.of(visitScalarFunctionCall((CqlParser.ScalarFunctionCallContext) ctx.getChild(0)), alias);
        }
        throw new UnsupportedOperationException("Unsupported function in SELECT list: " + ctx.getChild(0).getText());
    }

    @Override
    public Function visitScalarFunctionCall(CqlParser.ScalarFunctionCallContext ctx) {
        String functionName = ctx.getChild(0).getText();
        if ("date_format".equalsIgnoreCase(functionName)) {
            if (ctx.getChildCount() == 4) {
                CqlParser.FunctionArgsContext argsContext = (CqlParser.FunctionArgsContext) ctx.getChild(2);
                if (argsContext.getChildCount() == 3) {
                    if (argsContext.getChild(0) instanceof CqlParser.ScalarFunctionCallContext) {
                        return DateFormat.of(visitScalarFunctionCall((CqlParser.ScalarFunctionCallContext) argsContext.getChild(0)),
                                StringUtil.stripQuote(argsContext.getChild(2).getText()));
                    }
                }
            }
        } else if ("date_sub".equalsIgnoreCase(functionName)) {
            if (ctx.getChildCount() == 4) {
                CqlParser.FunctionArgsContext argsContext = (CqlParser.FunctionArgsContext) ctx.getChild(2);
                if (argsContext.getChildCount() == 3) {
                    if (argsContext.getChild(0) instanceof CqlParser.ScalarFunctionCallContext) {
                        CqlParser.PredicateExpressionContext expressionCtx = (CqlParser.PredicateExpressionContext) argsContext.getChild(2);
                        CqlParser.IntervalExpressionAtomContext intervalExpressionCtx = (CqlParser.IntervalExpressionAtomContext) expressionCtx.getChild(0).getChild(0);
                        return DateSub.of(visitScalarFunctionCall((CqlParser.ScalarFunctionCallContext) argsContext.getChild(0)),
                                IntegerUtil.tryParse(intervalExpressionCtx.getChild(1).getText()), intervalExpressionCtx.getChild(2).getText());
                    }
                }

            }
        } else if ("curdate".equalsIgnoreCase(functionName)) {
            return new CurDate();
        }
        throw new UnsupportedOperationException("Unsupported scalar function: " + functionName);
    }



    @Override
    public Function visitSpecificFunctionCall(CqlParser.SpecificFunctionCallContext ctx) {
        if (ctx.getChild(0) instanceof CqlParser.C_keyvalueFunctionCallContext) {
            return visitC_keyvalueFunctionCall((CqlParser.C_keyvalueFunctionCallContext) ctx.getChild(0));
        }
        throw new UnsupportedOperationException("Unsupported function: " + ctx.getText());
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
        throw new UnsupportedOperationException("Unsupported function: " + ctx.getText());
    }

    @Override
    public Function visitAggregateWindowedFunction(CqlParser.AggregateWindowedFunctionContext ctx) {
        String functionName = ctx.getChild(0).getText();
        if ("count".equalsIgnoreCase(functionName)) {
            ParseTree arg = ctx.getChild(2);
            boolean distinct = false;
            if (arg instanceof TerminalNode && "distinct".equalsIgnoreCase(arg.getText())) {
                distinct = true;
                arg = ctx.getChild(3);
            } else if (arg instanceof TerminalNode && "all".equalsIgnoreCase(arg.getText())) {
                arg = ctx.getChild(3);
            }
            if (arg == null || arg.getText().contains(",")) {
                throw new UnsupportedOperationException("COUNT with multiple arguments is not supported yet.");
            }
            return Count.of(Column.of(arg.getText()), distinct);
        }
        for (int idx = 1, len = ctx.getChildCount(); idx < len; idx++) {
            ParseTree pt = ctx.getChild(idx);
            if (!(pt instanceof CqlParser.FunctionArgContext)) {
                continue;
            }
            Column column = Column.of(pt.getText());
            if ("avg".equalsIgnoreCase(functionName)) {
                return Avg.of(column);
            }
            if ("sum".equalsIgnoreCase(functionName)) {
                return Sum.of(column);
            }
            if ("min".equalsIgnoreCase(functionName)) {
                return Min.of(column);
            }
            if ("max".equalsIgnoreCase(functionName)) {
                return Max.of(column);
            }
        }
        throw new UnsupportedOperationException("Unsupported aggregate function: " + functionName);
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
        String name = ctx.getText();
        if (!isSimpleColumnReference(name)) {
            throw new UnsupportedOperationException("GROUP BY expressions are not supported yet: " + name);
        }
        return Column.of(name);
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
            String name = exp.getChild(0).getText();
            if (!name.matches("[0-9]+") && !isSimpleColumnReference(name)) {
                throw new UnsupportedOperationException("ORDER BY expressions are not supported yet: " + name);
            }
            columns.add(Column.of(name, null, Order.of(exp.order == null ? null : exp.order.getText())));
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
        where = normalizeLogical(this.cqlParseExtension.afterWhere(where));
        List<Table> tables = new LinkedList<>();
        if (sources != null) {
            for (int idx = 0, len = sources.getChildCount(); idx < len; idx++) {
                ParseTree pt = sources.getChild(idx);
                if (!(pt instanceof CqlParser.TableSourceBaseContext)) {
                    throw new UnsupportedOperationException("Multiple tables / JOIN are not supported yet: " + pt.getText());
                }
                Table t = visitTableSourceBase((CqlParser.TableSourceBaseContext) pt);
                if (t != null) {
                    tables.add(t);
                }
            }
        }
        if (tables.size() > 1) {
            throw new UnsupportedOperationException("Multiple tables / JOIN are not supported yet.");
        }
        return new From(tables, where);
    }

    @Override
    public Table visitTableSourceBase(CqlParser.TableSourceBaseContext ctx) {
        if (ctx.joinPart() != null && !ctx.joinPart().isEmpty()) {
            throw new UnsupportedOperationException("JOIN is not supported yet: " + ctx.getText());
        }
        ParseTree p = ctx.getChild(0);
        if (p instanceof CqlParser.AtomTableItemContext) {
            CqlParser.AtomTableItemContext t = (CqlParser.AtomTableItemContext) p;
            return Table.of(t.getChild(0).getText(), t.alias == null ? null : t.alias.getText());
        } else if (p instanceof CqlParser.SubqueryTableItemContext) {
            return visitSubqueryTableItem((CqlParser.SubqueryTableItemContext) p);
        }
        throw new UnsupportedOperationException("Unsupported table source: " + ctx.getText());
    }

    @Override
    public TemporaryTable visitSubqueryTableItem(CqlParser.SubqueryTableItemContext ctx) {
        throw new UnsupportedOperationException("Subquery in FROM is not supported yet: " + ctx.getText());
    }

    @Override
    public Node visitQueryExpression(CqlParser.QueryExpressionContext ctx) {
        return super.visitQueryExpression(ctx);
    }

    private Expr visitExpr(ParseTree tree) {
        if (tree == null) {
            return null;
        }
        if (tree instanceof CqlParser.PredicateExpressionContext) {
            return visitExpr(tree.getChild(0));
        }
        if (tree instanceof CqlParser.NotExpressionContext) {
            CqlParser.NotExpressionContext not = (CqlParser.NotExpressionContext) tree;
            return new NotExpr(requireExpr(visitExpr(not.expression())));
        }
        if (tree instanceof CqlParser.NestedExpressionAtomContext) {
            CqlParser.NestedExpressionAtomContext nested = (CqlParser.NestedExpressionAtomContext) tree;
            if (nested.expression() == null || nested.expression().size() != 1) {
                throw new UnsupportedOperationException("Row / tuple expressions are not supported yet: " + tree.getText());
            }
            return new GroupedExpr(requireExpr(visitExpr(nested.expression(0))));
        }
        if (tree instanceof CqlParser.ExpressionAtomPredicateContext) {
            return visitExpr(tree.getChild(0));
        }
        if (tree instanceof CqlParser.CqlWhereFunctionAtomContext) {
            return visitCqlWhereFunctionAtom((CqlParser.CqlWhereFunctionAtomContext) tree);
        }
        if (tree instanceof CqlParser.LikePredicateContext) {
            return visitLikePredicate((CqlParser.LikePredicateContext) tree);
        }
        if (tree instanceof CqlParser.InPredicateContext) {
            return visitInPredicate((CqlParser.InPredicateContext) tree);
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
        if (tree instanceof CqlParser.LogicalExpressionContext) {
            CqlParser.LogicalExpressionContext logical = (CqlParser.LogicalExpressionContext) tree;
            String opText = logical.logicalOperator().getText();
            Operator operator = LogicalOperator.of(opText);
            if (operator == LogicalOperator.NONE) {
                throw new UnsupportedOperationException("Unsupported logical operator: " + opText);
            }
            Expr left = requireExpr(visitExpr(logical.expression(0)));
            Expr right = requireExpr(visitExpr(logical.expression(1)));
            return new ExprImpl(left, operator, right);
        }
        if (tree instanceof CqlParser.IsExpressionContext) {
            throw new UnsupportedOperationException("IS TRUE / FALSE / UNKNOWN is not supported yet.");
        }
        if (tree instanceof CqlParser.RegexpPredicateContext
                || tree instanceof CqlParser.SoundsLikePredicateContext
                || tree instanceof CqlParser.JsonMemberOfPredicateContext
                || tree instanceof CqlParser.SubqueryComparisonPredicateContext) {
            throw new UnsupportedOperationException("Unsupported predicate: " + tree.getText());
        }
        throw new UnsupportedOperationException("Unsupported WHERE expression: " + tree.getText());
    }

    /**
     * Standard SQL gives AND a higher precedence than OR. The ANTLR expression
     * rule is left-associative with a single precedence, so the parse tree is
     * re-associated here: flatten the AND/OR chain and rebuild it with AND
     * applied first. Parenthesized groups are kept as opaque {@link GroupedExpr}
     * operands so explicit grouping is preserved.
     */
    private Expr normalizeLogical(Expr expr) {
        if (expr == null) {
            return null;
        }
        if (expr instanceof GroupedExpr) {
            return new GroupedExpr(normalizeLogical(((GroupedExpr) expr).expression()));
        }
        if (expr instanceof NotExpr) {
            return new NotExpr(normalizeLogical(((NotExpr) expr).expression()));
        }
        if (!(expr.operator() instanceof LogicalOperator)) {
            return expr;
        }
        LogicalOperator operator = (LogicalOperator) expr.operator();
        if (operator != LogicalOperator.AND && operator != LogicalOperator.OR) {
            return expr;
        }
        List<Object> parts = new ArrayList<>();
        flattenLogical(expr, parts);
        return reduceLogical(parts);
    }

    private void flattenLogical(Expr expr, List<Object> parts) {
        if (expr instanceof GroupedExpr || expr instanceof NotExpr) {
            parts.add(expr);
            return;
        }
        if (!(expr.operator() instanceof LogicalOperator)) {
            parts.add(expr);
            return;
        }
        LogicalOperator operator = (LogicalOperator) expr.operator();
        if (operator != LogicalOperator.AND && operator != LogicalOperator.OR) {
            parts.add(expr);
            return;
        }
        flattenLogical(requireExpr(expr.left()), parts);
        parts.add(operator);
        flattenLogical(requireExpr(expr.right()), parts);
    }

    private Expr reduceLogical(List<Object> parts) {
        List<Expr> orGroups = new ArrayList<>();
        List<Expr> andOperands = new ArrayList<>();
        for (Object part : parts) {
            if (part == LogicalOperator.OR) {
                orGroups.add(combine(andOperands, LogicalOperator.AND));
                andOperands = new ArrayList<>();
            } else if (!(part instanceof LogicalOperator)) {
                andOperands.add((Expr) part);
            }
        }
        orGroups.add(combine(andOperands, LogicalOperator.AND));
        Expr result = null;
        for (Expr group : orGroups) {
            if (group == null) {
                continue;
            }
            result = result == null ? group : new ExprImpl(result, LogicalOperator.OR, group);
        }
        if (result == null) {
            throw new SqlException("Empty logical expression.");
        }
        return result;
    }

    private Expr combine(List<Expr> operands, LogicalOperator operator) {
        Expr result = null;
        for (Expr operand : operands) {
            result = result == null ? operand : new ExprImpl(result, operator, operand);
        }
        return result;
    }

    private Expr requireExpr(Node node) {
        if (!(node instanceof Expr)) {
            throw new SqlException("Expected a boolean expression but got: " + node);
        }
        return (Expr) node;
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
        if (ctx.selectStatement() != null || ctx.expressions() == null) {
            throw new UnsupportedOperationException("IN (subquery) is not supported yet: " + ctx.getText());
        }
        String columnName = ctx.predicate().getText();
        if (!isSimpleColumnReference(columnName)) {
            throw new UnsupportedOperationException("IN requires a plain column on the left side: " + columnName);
        }
        Column col = Column.of(columnName);
        List<CqlParser.ExpressionContext> exps = ctx.expressions().expression();

        final List<Value> values = new LinkedList<>();
        for (CqlParser.ExpressionContext exp : exps) {
            if (!isLiteralValue(exp.getText())) {
                throw new UnsupportedOperationException("IN accepts only literals: " + exp.getText());
            }
            values.add(Value.of(exp.getText()));
        }

        return new ExprImpl(col, ctx.NOT() == null ? RelOperator.IN : RelOperator.NOT_IN, Range.of(values));
    }

    @Override
    public Expr visitLikePredicate(CqlParser.LikePredicateContext ctx) {
        String columnName = ctx.predicate(0).getText();
        if (!isSimpleColumnReference(columnName)) {
            throw new UnsupportedOperationException("LIKE requires a plain column on the left side: " + columnName);
        }
        String pattern = ctx.predicate(1).getText();
        if (!isQuotedString(pattern)) {
            throw new UnsupportedOperationException("LIKE requires a string literal pattern: " + pattern);
        }
        Character escape = null;
        if (ctx.ESCAPE() != null) {
            String escapeText = ctx.STRING_LITERAL() == null
                    ? null : StringUtil.stripQuote(ctx.STRING_LITERAL().getText());
            if (escapeText == null || escapeText.length() != 1) {
                throw new UnsupportedOperationException("LIKE ESCAPE requires a single-character literal.");
            }
            escape = escapeText.charAt(0);
        }
        return new ExprImpl(Column.of(columnName), ctx.NOT() == null ? RelOperator.LIKE : RelOperator.NOT_LIKE,
                Value.of(pattern), escape);
    }

    @Override
    public Expr visitBinaryComparisonPredicate(CqlParser.BinaryComparisonPredicateContext node) {
        String columnName = node.left.getText();
        if (!isSimpleColumnReference(columnName)) {
            throw new UnsupportedOperationException("Expressions on the left side of a comparison are not supported yet: " + columnName);
        }
        String operatorText = node.comparisonOperator().getText();
        RelOperator operator = RelOperator.of(operatorText);
        if (operator == RelOperator.NONE) {
            throw new UnsupportedOperationException("Unsupported comparison operator: " + operatorText);
        }
        String right = node.right.getText();
        Node rightNode;
        if (isLiteralValue(right)) {
            rightNode = Value.of(right);
        } else if (isSimpleColumnReference(right)) {
            rightNode = Column.of(right);
        } else {
            throw new UnsupportedOperationException("Unsupported right-hand side of comparison: " + right);
        }
        return new ExprImpl(Column.of(columnName), operator, rightNode);
    }

    @Override
    public Expr visitIsNullPredicate(CqlParser.IsNullPredicateContext ctx) {
        String columnName = ctx.predicate().getText();
        if (!isSimpleColumnReference(columnName)) {
            throw new UnsupportedOperationException("IS NULL requires a plain column: " + columnName);
        }
        String value = ctx.nullNotnull().getText();
        return new ExprImpl(Column.of(columnName), RelOperator.IS,
                new Value(null, "null".equalsIgnoreCase(value), "notnull".equalsIgnoreCase(value)));
    }

    @Override
    public Expr visitBetweenPredicate(CqlParser.BetweenPredicateContext ctx) {
        String columnName = ctx.predicate(0).getText();
        if (!isSimpleColumnReference(columnName)) {
            throw new UnsupportedOperationException("BETWEEN requires a plain column: " + columnName);
        }
        String begin = ctx.predicate(1).getText();
        String end = ctx.predicate(2).getText();
        if (!isLiteralValue(begin) || !isLiteralValue(end)) {
            throw new UnsupportedOperationException("BETWEEN accepts only literal bounds: " + begin + " AND " + end);
        }
        Expr between = new ExprImpl(Column.of(columnName), Value.of(begin), Value.of(end));
        return ctx.NOT() == null ? between : new NotExpr(between);
    }

    private static boolean isSimpleColumnReference(String text) {
        String t = text == null ? "" : text.trim();
        if (t.isEmpty()) {
            return false;
        }
        if (t.length() > 1 && t.startsWith("`") && t.endsWith("`")) {
            return true;
        }
        return t.matches("[A-Za-z0-9_$@]+(\\.[A-Za-z0-9_$@]+)*");
    }

    private static boolean isQuotedString(String text) {
        String t = text == null ? "" : text.trim();
        return t.length() >= 2
                && ((t.startsWith("'") && t.endsWith("'"))
                || (t.startsWith("\"") && t.endsWith("\"")));
    }

    private static boolean isLiteralValue(String text) {
        String t = text == null ? "" : text.trim();
        if (isQuotedString(t)) {
            return true;
        }
        if ("null".equalsIgnoreCase(t) || "true".equalsIgnoreCase(t) || "false".equalsIgnoreCase(t)) {
            return true;
        }
        return t.matches("[-+]?[0-9]+(\\.[0-9]+)?([eE][-+]?[0-9]+)?[dD]?");
    }


}
