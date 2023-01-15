package fun.langel.cql.spring;

import fun.langel.cql.bind.CaveRegister;
import fun.langel.cql.datasource.DataSource;
import fun.langel.cql.datasource.DataSourceHolder;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/8/20 5:51 下午
 **/
public class Configuration {

    private CaveRegister caveRegister = new CaveRegister(this);

    private List<DataSourceHolder> dataSourceHolders = new LinkedList<>();

    public void setDataSource(final List<DataSourceHolder> holders) {
        this.dataSourceHolders.addAll(holders);
    }

    public void addDataSource(final DataSourceHolder holder) {
        this.dataSourceHolders.add(holder);
    }

    public List<DataSourceHolder> getDataSourceHolders() {
        this.dataSourceHolders.sort(Comparator.comparingInt(DataSourceHolder::priority));
        return this.dataSourceHolders;
    }

    public DataSourceHolder getFirstDataSource() {
        List<DataSourceHolder> holders = getDataSourceHolders();
        if (holders.size() > 0) {
            return holders.get(0);
        }
        return null;
    }

    public DataSourceHolder getDataSource(final String name) {
        if (this.dataSourceHolders == null || name == null) {
            return null;
        }
        for (DataSourceHolder holder : this.dataSourceHolders) {
            if (name.equalsIgnoreCase(holder.name())) {
                return holder;
            }
        }
        return null;
    }

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
