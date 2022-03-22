package fun.langel.cql.invoke;

import fun.langel.cql.bind.CaveMethod;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:03 下午
 **/
public class Invocation {

    private CaveMethod.MethodSignature signature;

    public Invocation(CaveMethod.MethodSignature signature) {
        this.signature = signature;
    }

    public CaveMethod.MethodSignature getSignature() {
        return signature;
    }

}
