package fun.langel.cql.calcite;

import org.apache.calcite.sql.parser.SqlAbstractParserImpl;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.SqlParserImplFactory;

import java.io.Reader;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/29 10:28 下午
 **/
public class CqlParserImpl implements SqlParserImplFactory {
    @Override
    public SqlAbstractParserImpl getParser(Reader reader) {
        return null;
    }
}
