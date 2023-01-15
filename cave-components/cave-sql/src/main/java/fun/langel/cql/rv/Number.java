package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;
import fun.langel.cql.util.TimeUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/7 06:10
 **/
public class Number implements ReturnValue<Number> {

    private final BigDecimal value;

    private Number(final BigDecimal value) {
        this.value = value;
    }

    public static Number of(final java.lang.String value) {
        return new Number(new BigDecimal(value));
    }

    public static Number of(final int value) {
        return new Number(BigDecimal.valueOf(value));
    }

    public static Number of(final long value) {
        return new Number(BigDecimal.valueOf(value));
    }

    public static Number of(final double value) {
        return new Number(BigDecimal.valueOf(value));
    }

    public static Number of(final float value) {
        return new Number(BigDecimal.valueOf(value));
    }

    @Override
    public Number getValue() {
        return this;
    }

    public int intValue() {
        if (this.value == null) {
            return 0;
        }
        return this.value.intValue();
    }

    public long longValue() {
        if (this.value == null) {
            return 0;
        }
        return this.value.longValue();
    }

    public double doubleValue() {
        if (this.value == null) {
            return 0;
        }
        return this.value.doubleValue();
    }

    @Override
    public Object mapTo(Class<?> klass) throws MappingException {
        if (String.class.isAssignableFrom(klass)) {
            return this.value.toString();
        } else if (int.class.isAssignableFrom(klass) || Integer.class.isAssignableFrom(klass)) {
            return this.value.intValue();
        } else if (long.class.isAssignableFrom(klass) || Long.class.isAssignableFrom(klass)) {
            return this.value.longValue();
        } else if (short.class.isAssignableFrom(klass) || Short.class.isAssignableFrom(klass)) {
            return this.value.shortValue();
        } else if (float.class.isAssignableFrom(klass) || Float.class.isAssignableFrom(klass)) {
            return this.value.floatValue();
        } else if (double.class.isAssignableFrom(klass) || Double.class.isAssignableFrom(klass)) {
            return this.value.doubleValue();
        } else if (LocalDateTime.class.isAssignableFrom(klass)) {
            return TimeUtil.toLocalDateTime(this.value.longValue());
        } else if (Date.class.isAssignableFrom(klass)) {
            return TimeUtil.toDate(this.value.longValue());
        }
        return this.value.toString();
    }
}
