package fun.langel.cql.node.func;

import fun.langel.cql.node.Function;
import fun.langel.cql.node.Node;
import fun.langel.cql.node.obj.Date;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/11/12 20:44
 **/
public class DateSub implements Function {

    private static final String DAY = "day";

    private static final String MONTH = "month";

    private static final String YEAR = "year";

    private static final String HOUR = "hour";

    private static final String MINUTE = "minute";

    private static final String SECOND = "second";

    private Date date;

    private int interval;

    public String unit;

    private Function function;

    private DateSub(Date date, int interval, String unit) {
        this.date = date;
        this.interval = interval;
        this.unit = unit;
    }

    private DateSub(Function function, int interval, String unit) {
        this.function = function;
        this.interval = interval;
        this.unit = unit;
    }

    public static DateSub of(Date date, int interval, String unit) {
        return new DateSub(date, interval, unit);
    }

    public static DateSub of(Function function, int interval, String unit) {
        return new DateSub(function, interval, unit);
    }

    @Override
    public Node executable() {
        Date d = function != null ? ((Date) function.executable()) : this.date;
        switch (unit) {
            case DAY:
                return new Date(d.getLocalDate().plusDays(-interval));

            case MONTH:
                return new Date(d.getLocalDate().plusMonths(-interval));

            case YEAR:
                return new Date(d.getLocalDate().plusYears(-interval));

            case HOUR:
                return new Date(d.getLocalDate().plusHours(-interval));

            case MINUTE:
                return new Date(d.getLocalDate().plusMinutes(-interval));

            case SECOND:
                return new Date(d.getLocalDate().plusSeconds(-interval));

        }
        return null;
    }
}
