package fun.langel.cql.node.func;

import fun.langel.cql.node.Function;
import fun.langel.cql.node.Node;
import fun.langel.cql.node.Value;
import fun.langel.cql.node.obj.Date;

import java.time.format.DateTimeFormatter;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/11/12 14:44
 **/
public class DateFormat implements Function {

    private Date date;
    private String pattern;

    private Function function;

    private DateFormat(Date date, String pattern) {
        this.date = date;
        this.pattern = pattern;
    }

    private DateFormat(Function function, String pattern) {
        this.function = function;
        this.pattern = pattern;
    }

    @Override
    public Node executable() {
        if (this.function != null) {
            return Value.of(((Date) this.function.executable()).getLocalDate().format(DateTimeFormatter.ofPattern(pattern)));
        }
        return Value.of(date.getLocalDate().format(DateTimeFormatter.ofPattern(pattern)));
    }

    public static DateFormat of(Date date, String pattern) {
        return new DateFormat(date, pattern);
    }

    public static DateFormat of(Function function, String pattern) {
        return new DateFormat(function, pattern);
    }
}
