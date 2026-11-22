package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/7 20:39
 **/
public class Time implements ReturnValue<LocalDateTime> {

    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final LocalDateTime value;

    private Time(final LocalDateTime value) {
        this.value = value;
    }

    public static Time of(LocalDateTime value) {
        return new Time(value);
    }

    @Override
    public LocalDateTime getValue() {
        return this.value;
    }

    @Override
    public Object mapTo(Class<?> klass) throws MappingException {
        if (klass.equals(LocalDateTime.class)) {
            return this.value;
        }
        if (klass.equals(LocalDate.class)) {
            return this.value.toLocalDate();
        }
        if (klass.equals(LocalTime.class)) {
            return this.value.toLocalTime();
        }
        if (klass.equals(java.sql.Timestamp.class)) {
            return java.sql.Timestamp.valueOf(this.value);
        }
        if (klass.equals(java.sql.Date.class)) {
            return java.sql.Date.valueOf(this.value.toLocalDate());
        }
        if (klass.equals(java.sql.Time.class)) {
            return java.sql.Time.valueOf(this.value.toLocalTime());
        }
        if (Date.class.isAssignableFrom(klass)) {
            return Date.from(this.value.atZone(ZoneId.systemDefault()).toInstant());
        }
        if (klass.equals(java.lang.String.class)) {
            return this.value.format(OUTPUT);
        }
        if (klass.equals(Long.class) || klass.equals(long.class)) {
            return this.value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
        throw new MappingException("Cannot map datetime to " + klass.getName());
    }
}
