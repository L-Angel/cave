package fun.langel.cql.node;

/**
 * A single column assignment in an UPDATE ... SET clause.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class Assignment implements Node {

    private final Column column;

    private final Value value;

    public Assignment(final Column column, final Value value) {
        this.column = column;
        this.value = value;
    }

    public Column column() {
        return this.column;
    }

    public Value value() {
        return this.value;
    }
}
