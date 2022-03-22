package fun.langel.cql.util;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/3/21 15:49
 **/
public class LongUtil {

    private LongUtil() {
    }

    public static long tryParse(final String v) {
        try {
            return Long.parseLong(v);
        } catch (Exception ex) {
            return 0L;
        }
    }
}
