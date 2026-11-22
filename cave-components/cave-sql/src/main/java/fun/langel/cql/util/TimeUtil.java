/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.util;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/13 01:10
 **/
public class TimeUtil {

    private static final DateTimeFormatter DATETIME =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DATETIME_FRACTION = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .optionalStart()
            .appendFraction(ChronoField.NANO_OF_SECOND, 1, 9, true)
            .optionalEnd()
            .toFormatter();

    private TimeUtil() {
    }

    /**
     * Accepts the common JDBC / ISO-8601 representations:
     * {@code yyyy-MM-dd}, {@code yyyy-MM-dd HH:mm:ss},
     * {@code yyyy-MM-dd HH:mm:ss.SSS}, {@code yyyy-MM-ddTHH:mm:ss[.SSS]},
     * and offset date-times.
     */
    public static LocalDateTime toLocalDateTime(final String t) {
        if (t == null || t.trim().isEmpty()) {
            throw new IllegalArgumentException("Date/time value must not be empty.");
        }
        final String value = t.trim();
        try {
            return LocalDateTime.parse(value);
        } catch (DateTimeParseException ignored) {
            // try the next representation
        }
        try {
            return LocalDateTime.parse(value, DATETIME_FRACTION);
        } catch (DateTimeParseException ignored) {
            // try the next representation
        }
        try {
            return OffsetDateTime.parse(value.replace(' ', 'T'))
                    .atZoneSameInstant(ZoneId.systemDefault())
                    .toLocalDateTime();
        } catch (DateTimeParseException ignored) {
            // try the next representation
        }
        try {
            return LocalDate.parse(value).atStartOfDay();
        } catch (DateTimeParseException ignored) {
            throw new IllegalArgumentException("Unsupported date/time value: '" + t + "'");
        }
    }

    public static LocalDateTime toLocalDateTime(final long t) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(t), ZoneId.systemDefault());
    }

    public static Date toDate(final String t) throws ParseException {
        try {
            return Date.from(toLocalDateTime(t).atZone(ZoneId.systemDefault()).toInstant());
        } catch (RuntimeException ex) {
            ParseException parseException = new ParseException("Unsupported date/time value: '" + t + "'", 0);
            parseException.initCause(ex);
            throw parseException;
        }
    }

    public static Date toDate(final long t) {
        return new Date(t);
    }
}
