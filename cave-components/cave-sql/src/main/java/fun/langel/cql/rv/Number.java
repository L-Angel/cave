package fun.langel.cql.rv;

import java.math.BigDecimal;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/7 06:10
 **/
public class Number implements ReturnValue<Number> {

    private final BigDecimal value;

    private Number(final BigDecimal value) {
        this.value = value;
    }

    public static Number of(final String value) {
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
}
