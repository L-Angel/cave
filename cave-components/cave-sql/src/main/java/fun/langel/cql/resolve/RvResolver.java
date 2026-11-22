package fun.langel.cql.resolve;

import fun.langel.cql.node.Column;
import fun.langel.cql.rv.Bytes;
import fun.langel.cql.rv.Number;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Row;
import fun.langel.cql.rv.Time;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/4/26 17:16
 **/
public interface RvResolver<F> {
    ReturnValue<?> resolve(F from, List<Column> columns);

    default ReturnValue<?> resolveObj(Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof String) {
            return fun.langel.cql.rv.String.of((String) v);
        }
        if (v instanceof java.lang.Boolean) {
            return fun.langel.cql.rv.Boolean.of((java.lang.Boolean) v);
        }
        if (v instanceof Integer) {
            return Number.of((Integer) v);
        }
        if (v instanceof Long) {
            return Number.of((Long) v);
        }
        if (v instanceof Byte || v instanceof Short) {
            return Number.of(((java.lang.Number) v).longValue());
        }
        if (v instanceof Float) {
            return Number.of((Float) v);
        }
        if (v instanceof Double) {
            return Number.of((Double) v);
        }
        if (v instanceof BigDecimal) {
            return Number.of((BigDecimal) v);
        }
        if (v instanceof BigInteger) {
            return Number.of(new BigDecimal((BigInteger) v));
        }
        if (v instanceof Timestamp) {
            return Time.of(((Timestamp) v).toLocalDateTime());
        }
        if (v instanceof java.sql.Date) {
            return Time.of(((java.sql.Date) v).toLocalDate().atStartOfDay());
        }
        if (v instanceof java.sql.Time) {
            return Time.of(LocalDate.ofEpochDay(0).atTime(((java.sql.Time) v).toLocalTime()));
        }
        if (v instanceof LocalDateTime) {
            return Time.of((LocalDateTime) v);
        }
        if (v instanceof LocalDate) {
            return Time.of(((LocalDate) v).atStartOfDay());
        }
        if (v instanceof LocalTime) {
            return Time.of(LocalDate.ofEpochDay(0).atTime((LocalTime) v));
        }
        if (v instanceof java.util.Date) {
            return Time.of(LocalDateTime.ofInstant(((java.util.Date) v).toInstant(), ZoneId.systemDefault()));
        }
        if (v instanceof byte[]) {
            return Bytes.of((byte[]) v);
        }
        return fun.langel.cql.rv.String.of(String.valueOf(v));
    }

    default Row resolve(Map<String, Object> r) {
        Row row = new Row();
        for (Map.Entry<String, Object> entry : r.entrySet()) {
            final String key = entry.getKey();
            Object value = entry.getValue();
            row.put(key, resolveObj(value));
        }
        return row;
    }
}
