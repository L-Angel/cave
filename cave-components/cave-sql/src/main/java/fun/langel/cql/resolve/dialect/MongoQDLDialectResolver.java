package fun.langel.cql.resolve.dialect;

import fun.langel.cql.capability.Capabilities;
import fun.langel.cql.capability.CapabilityValidator;
import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.MongoQDL;
import fun.langel.cql.node.*;
import fun.langel.cql.node.func.C_Exists;
import fun.langel.cql.node.func.C_KeyValue;
import fun.langel.cql.node.operator.FunctionOperator;
import fun.langel.cql.node.operator.LogicalOperator;
import fun.langel.cql.node.operator.Operator;
import fun.langel.cql.node.operator.RelOperator;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.AggregationUtil;
import org.bson.*;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/11 14:44
 **/
public class MongoQDLDialectResolver implements MongoDialectResolver<SelectStatement, Bson> {

    @Override
    public Dialect<Bson> resolve(SelectStatement statement) {
        CapabilityValidator.validate(statement, Capabilities.MONGO);
        List<Table> tables = statement.tables();
        if (tables == null || tables.size() != 1 || tables.get(0) instanceof TemporaryTable) {
            throw new UnsupportedOperationException("MongoDB queries require exactly one collection; "
                    + "JOIN and subqueries are not supported yet.");
        }
        List<Column> columns = statement.columns() == null ? Collections.emptyList() : statement.columns();
        for (Column column : columns) {
            if (column.isFunction()) {
                Function function = column.function();
                if (AggregationUtil.isAggregate(function) || function instanceof C_KeyValue) {
                    continue;
                }
                throw new UnsupportedOperationException("Function " + function.getClass().getSimpleName()
                        + " is not supported for mongodb queries yet.");
            }
            if (column.name() == null) {
                throw new UnsupportedOperationException("Unsupported SELECT column: " + column);
            }
        }
        if (statement.groupBy() != null && columns.isEmpty()) {
            throw new UnsupportedOperationException("GROUP BY requires an explicit column list.");
        }

        return new MongoQDL(tables.get(0).getName(),
                statement.where() == null ? null : resolveQueryCondition(statement.where()),
                columns, statement.limit(), statement.orderBy(), statement.groupBy());
    }

    /**
     * Translates an expression into a MongoDB filter. Used for WHERE and HAVING.
     */
    public Bson filter(Expr expr) {
        return resolveQueryCondition(expr);
    }


    private Bson resolveQueryCondition(Expr expr) {
        if (expr == null) {
            return null;
        }
        if (expr instanceof GroupedExpr) {
            return resolveQueryCondition(((GroupedExpr) expr).expression());
        }
        if (expr instanceof NotExpr) {
            Bson inner = resolveQueryCondition(((NotExpr) expr).expression());
            if (inner == null) {
                throw new UnsupportedOperationException("NOT condition could not be translated to mongodb.");
            }
            return nor(inner);
        }
        Operator operator = expr.operator();
        if (operator == LogicalOperator.AND) {
            return and(resolveQueryCondition((Expr) expr.left()), resolveQueryCondition((Expr) expr.right()));
        } else if (operator == LogicalOperator.OR) {
            return or(resolveQueryCondition((Expr) expr.left()), resolveQueryCondition((Expr) expr.right()));
        } else if (operator instanceof RelOperator) {
            return resolveRelOperator((RelOperator) operator, expr);
        } else if (operator instanceof fun.langel.cql.node.operator.BetweenOperator) {
            String name = ((Column) expr.left()).name();
            Value begin = expr.begin();
            Value end = expr.end();
            if (begin == null || end == null || begin.isNull() || end.isNull()) {
                return matchNone();
            }
            return and(gte(name, toBsonValue(begin)), lte(name, toBsonValue(end)));
        } else if (operator instanceof FunctionOperator) {
            if (operator == FunctionOperator.C_EXISTS) {
                C_Exists exists = (C_Exists) expr;
                return exists(exists.executable().toString(), true);
            }
            throw new UnsupportedOperationException("Unsupported function operator: " + operator);
        }
        throw new UnsupportedOperationException("Unsupported operator: " + operator + ", left : " + expr.left()
                + ", right : " + expr.right());
    }

    private Bson resolveRelOperator(RelOperator operator, Expr expr) {
        String name = ((Column) expr.left()).name();
        if (expr.right() instanceof Column) {
            return resolveFieldComparison(operator, expr, name);
        }
        if (operator == RelOperator.IS) {
            Value value = (Value) expr.right();
            if (value.isNull()) {
                return eq(name, new BsonNull());
            }
            if (value.notNull()) {
                return ne(name, new BsonNull());
            }
            return matchNone();
        }
        if (operator == RelOperator.IN || operator == RelOperator.NOT_IN) {
            Range range = (Range) expr.right();
            List<Object> values = range.values().stream()
                    .filter(value -> value != null && !value.isNull())
                    .map(Value::value)
                    .collect(Collectors.toList());
            if (values.isEmpty()) {
                return matchNone();
            }
            if (operator == RelOperator.IN) {
                return in(name, values);
            }
            // Standard SQL: NOT IN excludes documents where the field is NULL/missing.
            return and(exists(name, true), nin(name, values));
        }

        Value cValue = (Value) expr.right();
        if (cValue == null || cValue.isNull()) {
            // Standard SQL: comparisons with NULL are UNKNOWN, so they match no rows.
            return matchNone();
        }
        switch (operator) {
            case LESS:
                return lt(name, toBsonValue(cValue));
            case LESS_OR_EQUALS:
                return lte(name, toBsonValue(cValue));
            case GREATER:
                return gt(name, toBsonValue(cValue));
            case GREATER_OR_EQUALS:
                return gte(name, toBsonValue(cValue));
            case EQUAL:
                return eq(name, toBsonValue(cValue));
            case NOT_EQUAL:
                // Standard SQL: != does not match documents where the field is NULL/missing.
                return and(exists(name, true), ne(name, toBsonValue(cValue)));
            case LIKE:
                return regex(name, likeToRegex(String.valueOf(cValue.value()), expr.escape()), "i");
            case NOT_LIKE:
                return and(exists(name, true),
                        not(regex(name, likeToRegex(String.valueOf(cValue.value()), expr.escape()), "i")));
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + operator + ", left : "
                        + expr.left() + ", right : " + expr.right());
        }
    }

    private Bson resolveFieldComparison(RelOperator operator, Expr expr, String left) {
        String right = ((Column) expr.right()).name();
        String mongoOperator;
        switch (operator) {
            case EQUAL:
                mongoOperator = "$eq";
                break;
            case NOT_EQUAL:
                mongoOperator = "$ne";
                break;
            case GREATER:
                mongoOperator = "$gt";
                break;
            case GREATER_OR_EQUALS:
                mongoOperator = "$gte";
                break;
            case LESS:
                mongoOperator = "$lt";
                break;
            case LESS_OR_EQUALS:
                mongoOperator = "$lte";
                break;
            default:
                throw new UnsupportedOperationException("Field-to-field comparison is not supported for operator: " + operator);
        }
        return expr(new Document(mongoOperator, Arrays.asList("$" + left, "$" + right)));
    }

    /**
     * Translates a SQL LIKE pattern into an anchored MongoDB regex. SQL wildcards
     * ({@code %} and {@code _}) are converted, everything else is quoted so user
     * input cannot inject regex syntax. SQL LIKE is anchored by definition.
     */
    private String likeToRegex(final String value, final Character escape) {
        StringBuilder regex = new StringBuilder();
        StringBuilder literal = new StringBuilder();
        for (int idx = 0; idx < value.length(); idx++) {
            char c = value.charAt(idx);
            if (escape != null && c == escape && idx + 1 < value.length()) {
                literal.append(value.charAt(++idx));
            } else if (c == '%' || c == '_') {
                if (literal.length() > 0) {
                    regex.append(Pattern.quote(literal.toString()));
                    literal.setLength(0);
                }
                regex.append(c == '%' ? ".*" : ".");
            } else {
                literal.append(c);
            }
        }
        if (literal.length() > 0) {
            regex.append(Pattern.quote(literal.toString()));
        }
        return "^(" + regex + ")$";
    }

    private BsonValue toBsonValue(Value value) {
        if (value == null || value.isNull()) {
            return new BsonNull();
        }
        switch (value.type()) {
            case BOOLEAN:
                return new BsonBoolean((Boolean) value.value());
            case NUMBER:
                if (value.value() instanceof Float || value.value() instanceof Double) {
                    return new BsonDouble(((java.lang.Number) value.value()).doubleValue());
                }
                return new BsonInt64(((java.lang.Number) value.value()).longValue());
            default:
                return new BsonString(String.valueOf(value.value()));
        }
    }

    private Bson matchNone() {
        return expr(new Document("$eq", Arrays.asList(1, 0)));
    }
}
