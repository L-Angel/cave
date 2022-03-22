package fun.langel.cql.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/8/20 3:59 下午
 **/
public class CaveFactoryBean<T> implements FactoryBean<T>, InitializingBean {

    private Class<?> caveInterfaceKlass;

    private Configuration configuration;


    public CaveFactoryBean(Class<?> caveInterfaceKlass,
                           Configuration configuration) {
        this.caveInterfaceKlass = caveInterfaceKlass;
        this.configuration = configuration;
    }

    @Override
    public T getObject() throws Exception {
        return this.configuration.getCave(this.caveInterfaceKlass);
    }

    @Override
    public Class<?> getObjectType() {
        return this.caveInterfaceKlass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkCave();
    }

    public void checkCave() {
        if (!configuration.hasCave(this.caveInterfaceKlass)) {
            configuration.addCave(this.caveInterfaceKlass);
        }
    }
}

