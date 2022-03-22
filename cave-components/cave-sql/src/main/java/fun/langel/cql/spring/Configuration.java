package fun.langel.cql.spring;

import fun.langel.cql.bind.CaveRegister;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/8/20 5:51 下午
 **/
public class Configuration {

    private CaveRegister caveRegister = new CaveRegister();


    public void addCave(final Class<?> klass) {
        this.caveRegister.addCave(klass);
    }

    public <T> T getCave(final Class<?> klass) {
        return this.caveRegister.getCave(klass);
    }

    public boolean hasCave(final Class<?> klass) {
        return caveRegister.hasCave(klass);
    }

}
