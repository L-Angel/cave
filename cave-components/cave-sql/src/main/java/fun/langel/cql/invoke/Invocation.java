package fun.langel.cql.invoke;

import fun.langel.cql.bind.CaveMethod;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.util.Pair;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/10/19 10:03 下午
 **/
public class Invocation {

    private final CaveMethod.MethodSignature signature;


    public Invocation(CaveMethod.MethodSignature signature) {
        this.signature = signature;
    }

    public CaveMethod.MethodSignature getSignature() {
        return signature;
    }

    public Class<?> returnType() {
        return this.signature.returnType();
    }

    public Class<?> actualType() {
        return this.signature.actualType();
    }
}
