package fun.langel.cql.node;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/12 22:46
 **/
public class C_Script implements Function {

    private final Script executable;

    private C_Script(Script script) {
        this.executable = script;
    }

    @Override
    public Script executable() {
        return this.executable;
    }

    public static C_Script of(final String script) {
        return new C_Script(Script.of(script));
    }
}
