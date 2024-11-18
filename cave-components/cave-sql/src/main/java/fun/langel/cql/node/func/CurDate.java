package fun.langel.cql.node.func;

import fun.langel.cql.node.Function;
import fun.langel.cql.node.Node;
import fun.langel.cql.node.obj.Date;
import java.time.LocalDateTime;

/**
 * @author Rick(lonelyangel.jcw @ gmail.com)
 * created 2024/11/12 20:44
 **/
public class CurDate implements Function {
    @Override
    public Node executable() {
        return new Date(LocalDateTime.now());
    }
}
