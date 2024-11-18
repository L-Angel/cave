package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;
import fun.langel.cql.node.Node;
import fun.langel.cql.util.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        } else if (Date.class.isAssignableFrom(klass)) {
            try {
                return TimeUtil.toDate(this.value);
            } catch (ParseException e) {
                throw new MappingException(e);
            }
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
}
