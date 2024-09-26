package fun.langel.cql.resolve.dialect;

import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.MongoQDL;
import fun.langel.cql.node.*;
import fun.langel.cql.node.operator.LogicalOperator;
import fun.langel.cql.node.operator.RelOperator;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.ListUtil;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/11 14:44
 **/
public class MongoQDLDialectResolver implements MongoDialectResolver<SelectStatement, Bson> {

    @Override
    public Dialect<Bson> resolve(SelectStatement statement) {
        List<Table> tables = statement.tables();
        List<Column> columns = statement.columns();

        return new MongoQDL(ListUtil.isNullOrEmpty(tables) ? ""
                : tables.get(0).getName(), resolveQueryCondition(statement.where()), columns, statement.limit(), statement.orderBy(), statement.groupBy());
    }


    private Bson resolveQueryCondition(Expr expr) {

        if (expr.operator() == LogicalOperator.AND) {
            return and(resolveQueryCondition((Expr) expr.left()), resolveQueryCondition((Expr) expr.right()));
        } else if (expr.operator() == LogicalOperator.OR) {
            return or(resolveQueryCondition((Expr) expr.left()), resolveQueryCondition((Expr) expr.right()));
        } else if (expr.operator() instanceof RelOperator) {
            return resolveRelOperator((RelOperator) expr.operator(), expr);
        }
        throw new UnsupportedOperationException("Unsupported operator: " + expr.operator() + ", left : " + expr.left() + ", right : " + expr.right());
    }

    private Bson resolveRelOperator(RelOperator operator, Expr expr) {
        String name = ((Column) expr.left()).name();
        if (operator == RelOperator.IN || operator == RelOperator.NOT_IN) {
            Range range = (Range) expr.right();

            if (operator == RelOperator.IN) {
                return in(name, range.values().stream().map(Value::value).collect(Collectors.toList()));

            } else if (operator == RelOperator.NOT_IN) {
                return nin(name, range.values().stream().map(Value::value).collect(Collectors.toList()));
            }
        } else {
            Value cValue = (Value) expr.right();

            switch (operator) {
                case LESS:
                    return lt(name, cValue.value());
                case LESS_OR_EQUALS:
                    return lte(name, cValue.value());
                case GREATER:
                    return gt(name, cValue.value());
                case GREATER_OR_EQUALS:
                    return gte(name, cValue.value());
                case EQUAL:
                    return eq(name, cValue.value());

                case NOT_EQUAL:
                    return ne(name, cValue.value());
                case LIKE:
                    String v = String.valueOf(cValue.value());
                    String regV = v;
                    if (v.endsWith("%")) {
                        regV = "^" + v;
                    } else if (v.startsWith("%")) {
                        regV = v + "$";
                    } else if (v.startsWith("%") && v.endsWith("%")) {
                        regV = v;
                    } else {
                        regV = "^" + v + "$";
                    }
                    return regex(name, regV, "i");
                case NOT_LIKE:
                    //'^((?!string).)*$'
                    return regex(name, "^((?!" + cValue.value() + ").)*$", "i");
            }
        }
        throw new UnsupportedOperationException("Unsupported operator: " + operator + ", left : " + expr.left() + ", right : " + expr.right());
    }
}
