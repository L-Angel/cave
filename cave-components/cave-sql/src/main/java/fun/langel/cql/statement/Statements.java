package fun.langel.cql.statement;

import fun.langel.cql.node.Node;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/7/20 20:00
 **/
public class Statements implements Node {

    private final List<Statement> statements;

    public Statements(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> statements() {
        return this.statements;
    }
}
