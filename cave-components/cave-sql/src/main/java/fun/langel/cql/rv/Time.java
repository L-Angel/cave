package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/7 20:39
 **/
public class Time implements ReturnValue<LocalDateTime> {

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
        return this.value;
    }
}
