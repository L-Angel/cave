package fun.langel.cql.node.obj;

import fun.langel.cql.node.Node;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/11/13 11:21
 **/
public class Date implements Node {
    private final LocalDateTime localDate;

    public Date(LocalDateTime localDate) {
        this.localDate = localDate;
    }
    public LocalDateTime getLocalDate() {
        return this.localDate;
    }
}
