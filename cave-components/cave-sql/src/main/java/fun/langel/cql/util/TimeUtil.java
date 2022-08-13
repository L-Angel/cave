package fun.langel.cql.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/13 01:10
 **/
public class TimeUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> SAFE_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat(DATE_FORMAT));

    private TimeUtil() {
    }

    public static LocalDateTime toLocalDateTime(final String t) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDateTime.parse(t, formatter);
    }

    public static LocalDateTime toLocalDateTime(final long t) {
        return LocalDateTime.ofEpochSecond(t / 1000, 0, ZoneOffset.ofHours(8));

    }

    public static Date toDate(final String t) throws ParseException {
        return SAFE_DATE_FORMAT.get().parse(t);
    }

    public static Date toDate(final long t) {
        return new Date(t);
    }
}
