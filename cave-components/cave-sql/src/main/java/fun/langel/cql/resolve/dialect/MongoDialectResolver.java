package fun.langel.cql.resolve.dialect;

import fun.langel.cql.resolve.DialectResolver;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.elasticsearch.action.ActionRequest;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/9/11 14:43
 **/
public interface MongoDialectResolver<IN, OUT extends Bson> extends DialectResolver<IN, OUT> {
}
