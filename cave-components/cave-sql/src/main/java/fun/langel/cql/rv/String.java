package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;
import fun.langel.cql.util.TimeUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/7 20:37
 **/
public class String implements ReturnValue<java.lang.String> {

    private java.lang.String value;

    private String(final java.lang.String value) {
        this.value = value;
    }

    public static String of(final java.lang.String value) {
        return new String(value);
    }

    @Override
    public java.lang.String getValue() {
        return this.value;
    }

    @Override
    public Object mapTo(Class<?> klass) throws MappingException {
        if (LocalDateTime.class.isAssignableFrom(klass)) {
            return TimeUtil.toLocalDateTime(this.value);
        } else if (LocalDate.class.isAssignableFrom(klass)) {
            return TimeUtil.toLocalDateTime(this.value).toLocalDate();
        } else if (LocalTime.class.isAssignableFrom(klass)) {
            try {
                return LocalTime.parse(this.value);
            } catch (RuntimeException ignored) {
                return TimeUtil.toLocalDateTime(this.value).toLocalTime();
            }
        } else if (java.sql.Timestamp.class.isAssignableFrom(klass)) {
            return java.sql.Timestamp.valueOf(TimeUtil.toLocalDateTime(this.value));
        } else if (java.sql.Date.class.isAssignableFrom(klass)) {
            return java.sql.Date.valueOf(TimeUtil.toLocalDateTime(this.value).toLocalDate());
        } else if (java.sql.Time.class.isAssignableFrom(klass)) {
            return java.sql.Time.valueOf(LocalTime.parse(this.value));
        } else if (Date.class.isAssignableFrom(klass)) {
            try {
                return TimeUtil.toDate(this.value);
            } catch (Exception e) {
                throw new MappingException(e.getMessage(), e);
            }
        } else if (java.lang.Boolean.class.isAssignableFrom(klass)
                || boolean.class.isAssignableFrom(klass)) {
            return parseBoolean(this.value);
        } else if (BigDecimal.class.isAssignableFrom(klass)) {
            return new BigDecimal(this.value);
        } else if (BigInteger.class.isAssignableFrom(klass)) {
            return new BigInteger(this.value);
        } else if (Long.class.isAssignableFrom(klass)
                || long.class.isAssignableFrom(klass)) {
            return Long.parseLong(this.value);
        } else if (Integer.class.isAssignableFrom(klass)
                || int.class.isAssignableFrom(klass)) {
            return Integer.parseInt(this.value);
        } else if (Short.class.isAssignableFrom(klass)
                || short.class.isAssignableFrom(klass)) {
            return Short.parseShort(this.value);
        } else if (Double.class.isAssignableFrom(klass)
                || double.class.isAssignableFrom(klass)) {
            return Double.parseDouble(this.value);
        } else if (Float.class.isAssignableFrom(klass)
                || float.class.isAssignableFrom(klass)) {
            return Float.parseFloat(this.value);
        }
        return this.value;
    }

    private static boolean parseBoolean(final java.lang.String value) {
        if (value == null) {
            return false;
        }
        java.lang.String normalized = value.trim();
        if ("1".equals(normalized) || "true".equalsIgnoreCase(normalized)) {
            return true;
        }
        if ("0".equals(normalized) || "false".equalsIgnoreCase(normalized)) {
            return false;
        }
        return java.lang.Boolean.parseBoolean(normalized);
    }
}
