package fun.langel.cql.calcite;

import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.*;
import org.apache.calcite.sql.validate.SqlConformance;

import java.io.Reader;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/29 10:28 下午
 **/
public class CqlParserImpl implements SqlParserImplFactory {
    @Override
    public SqlAbstractParserImpl getParser(Reader reader) {
        return new SqlAbstractParserImpl() {
            @Override
            public Metadata getMetadata() {
                return null;
            }

            @Override
            public SqlParseException normalizeException(Throwable throwable) {
                return null;
            }

            @Override
            protected SqlParserPos getPos() throws Exception {
                return null;
            }

            @Override
            public void ReInit(Reader reader) {

            }

            @Override
            public SqlNode parseSqlExpressionEof() throws Exception {
                return null;
            }

            @Override
            public SqlNode parseSqlStmtEof() throws Exception {
                return null;
            }

            @Override
            public void setTabSize(int i) {

            }

            @Override
            public void setQuotedCasing(Casing casing) {

            }

            @Override
            public void setUnquotedCasing(Casing casing) {

            }

            @Override
            public void setIdentifierMaxLength(int i) {

            }

            @Override
            public void setConformance(SqlConformance sqlConformance) {

            }

            @Override
            public void switchTo(String s) {

            }
        };
    }
}

