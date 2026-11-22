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

    /**
     * Name of the datasource registered by {@code @CaveScan(useDefaultElasticSearch = true)}
     * and by the Spring Boot starter when {@code cave.elasticsearch.hosts} is configured.
     */
    public static final String DEFAULT_ELASTICSEARCH_DATASOURCE = "defaultEs";

    /**
     * Name used by releases before 0.1.1 for the implicit Elasticsearch datasource.
     * Kept as an alias so existing {@code direct = "default_es"} mappings keep working.
     */
    public static final String LEGACY_DEFAULT_ELASTICSEARCH_DATASOURCE = "default_es";

    private CaveRegister caveRegister = new CaveRegister(this);

    private List<DataSourceHolder> dataSourceHolders = new LinkedList<>();

    public void setDataSource(final List<DataSourceHolder> holders) {
        this.dataSourceHolders.addAll(holders);
    }

    public void addDataSource(final DataSourceHolder holder) {
        this.dataSourceHolders.add(holder);
    }

    /**
     * Adds a datasource unless the same name/class pair is already registered.
     * Registration can happen from {@code @CaveScan} and from the Spring Boot
     * starter, both of which may describe the same datasource.
     */
    public void addDataSourceIfAbsent(final DataSourceHolder holder) {
        if (holder == null) {
            return;
        }
        for (DataSourceHolder existing : this.dataSourceHolders) {
            if (existing.name().equalsIgnoreCase(holder.name())
                    && existing.klass().equals(holder.klass())) {
                return;
            }
        }
        this.dataSourceHolders.add(holder);
    }

    public List<DataSourceHolder> getDataSourceHolders() {
        List<DataSourceHolder> holders = new LinkedList<>(this.dataSourceHolders);
        holders.sort(Comparator.comparingInt(DataSourceHolder::priority));
        return holders;
    }

    public DataSourceHolder getFirstDataSource() {
        DataSourceHolder first = null;
        for (DataSourceHolder holder : this.dataSourceHolders) {
            if (first == null || holder.priority() < first.priority()) {
                first = holder;
            }
        }
        return first;
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
        final String alias = aliasOf(name);
        if (alias != null) {
            for (DataSourceHolder holder : this.dataSourceHolders) {
                if (alias.equalsIgnoreCase(holder.name())) {
                    return holder;
                }
            }
        }
        return null;
    }

    private String aliasOf(final String name) {
        if (DEFAULT_ELASTICSEARCH_DATASOURCE.equalsIgnoreCase(name)) {
            return LEGACY_DEFAULT_ELASTICSEARCH_DATASOURCE;
        }
        if (LEGACY_DEFAULT_ELASTICSEARCH_DATASOURCE.equalsIgnoreCase(name)) {
            return DEFAULT_ELASTICSEARCH_DATASOURCE;
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
