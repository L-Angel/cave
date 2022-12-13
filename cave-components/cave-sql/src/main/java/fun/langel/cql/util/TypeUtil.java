package fun.langel.cql.util;

import java.util.List;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/13 12:10
 **/
public class TypeUtil {

    private TypeUtil() {
    }

    public static boolean isList(Class<?> type) {
        return List.class.isAssignableFrom(type);
    }

    public static boolean isMap(Class<?> type) {
        return Map.class.isAssignableFrom(type);
    }


}
