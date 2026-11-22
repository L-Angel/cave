package fun.langel.cql.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
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

    public static boolean isSimple(Class<?> type) {
        return type.isPrimitive()
                || java.lang.String.class.equals(type)
                || java.lang.Number.class.isAssignableFrom(type)
                || BigDecimal.class.equals(type)
                || java.lang.Boolean.class.equals(type)
                || java.lang.Character.class.equals(type)
                || Date.class.isAssignableFrom(type)
                || LocalDateTime.class.equals(type)
                || LocalDate.class.equals(type)
                || LocalTime.class.equals(type)
                || byte[].class.equals(type);
    }


}
