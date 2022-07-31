package fun.langel.cql.util;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/27 14:15
 **/
public class IntegerUtil {

    private IntegerUtil() {
    }

    public static int tryParse(final String v) {
        try {
            return Integer.parseInt(v);
        } catch (Exception ex) {
            return 0;
        }
    }
}
