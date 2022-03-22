package fun.langel.cql.resolve.dialect;

import fun.langel.cql.node.*;
import fun.langel.cql.resolve.DialectResolver;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 20:59
 **/
public class ElasticSearchQDLDialectResolver implements DialectResolver<Select, SearchRequest> {

    @Override
    public SearchRequest resolve(Select sqlNode) {
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ssb.from((int) sqlNode.limit().offset());
        ssb.size((int) sqlNode.limit().fetch());

        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must().addAll(resolveQueryCondition(sqlNode.where()));
        ssb.query(qb);

        SearchRequest sr = new SearchRequest(sqlNode.from());
        sr.searchType(SearchType.DEFAULT);
        sr.source(ssb);
        return sr;
    }

    private List<QueryBuilder> resolveQueryCondition(Node node) {
        if (node.kind() == NodeKind.OR) {
            final List<QueryBuilder> builders = new LinkedList<>();
            builders.addAll(resolveQueryCondition(((BinaryOpNode) node).left()));
            builders.addAll(resolveQueryCondition(((BinaryOpNode) node).right()));
            BoolQueryBuilder qb = QueryBuilders.boolQuery();
            qb.should().addAll(builders);
            return Collections.singletonList(qb);
        } else if (node.kind() == NodeKind.AND) {
            final List<QueryBuilder> builders = new LinkedList<>();
            builders.addAll(resolveQueryCondition(((BinaryOpNode) node).left()));
            builders.addAll(resolveQueryCondition(((BinaryOpNode) node).right()));
            return builders;
        } else if (NodeKind.COMPARISON.contains(node.kind())) {
            String name = ((Column) ((BinaryOpNode) node).left()).getName();
            String value = ((Value) ((BinaryOpNode) node).right()).value();
            final RangeQueryBuilder qb = QueryBuilders.rangeQuery(name);
            if (node.kind() == NodeKind.LESS_THAN) {
                qb.lt(value);
                return Collections.singletonList(qb);
            } else if (node.kind() == NodeKind.LESS_THAN_OR_EQUAL) {
                qb.lte(value);
                return Collections.singletonList(qb);
            } else if (node.kind() == NodeKind.GREATER_THAN) {
                qb.gt(value);
                return Collections.singletonList(qb);
            } else if (node.kind() == NodeKind.GREATER_THAN_OR_EQUAL) {
                qb.gte(value);
                return Collections.singletonList(qb);
            }
            if (node.kind() == NodeKind.EQUALS) {
                return Collections.singletonList(QueryBuilders.matchPhraseQuery(name, value));
            } else if (node.kind() == NodeKind.NOT_EQUALS) {
                BoolQueryBuilder bqb = QueryBuilders.boolQuery();
                bqb.mustNot(QueryBuilders.matchPhraseQuery(name, value));
                return Collections.singletonList(bqb);
            }
        } else if (node.kind() == NodeKind.BETWEEN) {
            Between between = (Between) node;
            final RangeQueryBuilder rqb = QueryBuilders.rangeQuery(between.column().getName());
            rqb.from(between.from());
            rqb.to(between.end());
            return Collections.singletonList(rqb);
        } else if (node.kind() == NodeKind.IN) {
            Range range = (Range) node;
            final String name = range.column().getName();
            return Collections.singletonList(QueryBuilders.termsQuery(name, range.values()));
        } else if (node.kind() == NodeKind.NOT_IN) {
            Range range = (Range) node;
            final String name = range.column().getName();
            BoolQueryBuilder bqb = QueryBuilders.boolQuery();
            bqb.mustNot(QueryBuilders.termsQuery(name, range.values()));
            return Collections.singletonList(bqb);
        }
        return Collections.emptyList();
    }


}
