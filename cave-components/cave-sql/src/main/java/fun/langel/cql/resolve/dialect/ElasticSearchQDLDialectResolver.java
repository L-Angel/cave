package fun.langel.cql.resolve.dialect;

import fun.langel.cql.dialect.Dialect;
import fun.langel.cql.dialect.ElasticSearchQDL;
import fun.langel.cql.node.*;
import fun.langel.cql.node.operator.BetweenOperator;
import fun.langel.cql.node.operator.LogicalOperator;
import fun.langel.cql.node.operator.Operator;
import fun.langel.cql.node.operator.RelOperator;
import fun.langel.cql.resolve.DialectResolver;
import fun.langel.cql.statement.SelectStatement;
import fun.langel.cql.util.ListUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 20:59
 **/
public class ElasticSearchQDLDialectResolver implements ElasticSearchDialectResolver<SelectStatement, SearchRequest> {

    @Override
    public Dialect<SearchRequest> resolve(SelectStatement statement) {
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        if (statement.limit() != null) {
            ssb.from(statement.limit().offset());
            ssb.size(statement.limit().fetch());
        }
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must().addAll(resolveQueryCondition(statement.where()));
        ssb.query(qb);

        List<String> tables = ListUtil.isNullOrEmpty(statement.tables()) ? null : statement.tables().stream().map(Table::getName).collect(Collectors.toList());
        SearchRequest sr = new SearchRequest(ListUtil.toStringArray(tables));
        sr.searchType(SearchType.DEFAULT);
        sr.source(ssb);
        return new ElasticSearchQDL(sr);
    }

    private List<QueryBuilder> resolveQueryCondition(Expr expr) {
        Operator operator = expr.operator();
        if (operator == LogicalOperator.OR) {
            final List<QueryBuilder> builders = new LinkedList<>();
            builders.addAll(resolveQueryCondition((Expr) expr.left()));
            builders.addAll(resolveQueryCondition((Expr) expr.right()));
            BoolQueryBuilder qb = QueryBuilders.boolQuery();
            qb.should().addAll(builders);
            return Collections.singletonList(qb);
        } else if (operator == LogicalOperator.AND) {
            final List<QueryBuilder> builders = new LinkedList<>();
            builders.addAll(resolveQueryCondition((Expr) expr.left()));
            builders.addAll(resolveQueryCondition((Expr) expr.right()));
            return builders;
        } else if (operator instanceof RelOperator) {
            String name = ((Column) expr.left()).name();

            if (operator == RelOperator.IN || operator == RelOperator.NOT_IN) {
                Range range = (Range) expr.right();
                if (operator == RelOperator.IN) {
                    return Collections.singletonList(QueryBuilders.termsQuery(name, range.values().stream().map(Value::value).collect(Collectors.toList())));
                } else if (operator == RelOperator.NOT_IN) {
                    BoolQueryBuilder bqb = QueryBuilders.boolQuery();
                    bqb.mustNot(QueryBuilders.termsQuery(name, range.values().stream().map(Value::value).collect(Collectors.toList())));
                    return Collections.singletonList(bqb);
                }
            } else {
                Object value = ((Value) (expr).right()).value();

                final RangeQueryBuilder qb = QueryBuilders.rangeQuery(name);

                if (operator == RelOperator.LESS) {
                    qb.lt(value);
                    return Collections.singletonList(qb);
                } else if (operator == RelOperator.LESS_OR_EQUALS) {
                    qb.lte(value);
                    return Collections.singletonList(qb);
                } else if (operator == RelOperator.GREATER) {
                    qb.gt(value);
                    return Collections.singletonList(qb);
                } else if (operator == RelOperator.GREATER_OR_EQUALS) {
                    qb.gte(value);
                    return Collections.singletonList(qb);
                }
                if (operator == RelOperator.EQUAL) {
                    return Collections.singletonList(QueryBuilders.matchPhraseQuery(name + ".keyword", value));
                } else if (operator == RelOperator.NOT_EQUAL) {
                    BoolQueryBuilder bqb = QueryBuilders.boolQuery();
                    bqb.mustNot(QueryBuilders.matchPhraseQuery(name + ".keyword", value));
                    return Collections.singletonList(bqb);
                }

                if (operator == RelOperator.LIKE) {
                    return Collections.singletonList(QueryBuilders.matchPhraseQuery(name, value));
                } else if (operator == RelOperator.NOT_LIKE) {
                    BoolQueryBuilder bqb = QueryBuilders.boolQuery();
                    bqb.mustNot(QueryBuilders.matchPhraseQuery(name, value));
                    return Collections.singletonList(bqb);
                }
            }
        } else if (operator instanceof BetweenOperator) {
            final RangeQueryBuilder rqb = QueryBuilders.rangeQuery(((Column) expr.left()).name());
            rqb.from(expr.begin().value());
            rqb.to(expr.end().value());
            return Collections.singletonList(rqb);
        }

        return Collections.emptyList();
    }


}
