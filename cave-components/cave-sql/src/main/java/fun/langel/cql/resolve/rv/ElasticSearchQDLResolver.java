package fun.langel.cql.resolve.rv;

import fun.langel.cql.resolve.RvResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Rows;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.search.SearchHit;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/4/26 17:22
 **/
public class ElasticSearchQDLResolver implements RvResolver<SearchResponse, ReturnValue<?>> {

    @Override
    public ReturnValue<?> resolve(SearchResponse from) {
        if (from == null || from.getHits() == null || from.getHits().getHits() == null) {
            return Rows.EMPTY;
        }
        SearchHit[] hits = from.getHits().getHits();
        Rows rows = new Rows();
        for (SearchHit h : hits) {
            Row row = new Row();
            for (DocumentField df : h) {
                if (df.getValues() != null) {
                    row.put(df.getName(), df.getValues());
                } else {
                    row.put(df.getName(), df.getValue());
                }
            }
            rows.add(row);
        }
        rows.setSize(from.getHits().getTotalHits().value);
        return rows;
    }
}
