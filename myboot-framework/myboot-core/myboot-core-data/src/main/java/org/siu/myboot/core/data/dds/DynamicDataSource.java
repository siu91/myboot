package org.siu.myboot.core.data.dds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;


/**
 * 动态设置数据源
 * <p>
 * 这里设置读写分离，主库写，从库读（其它业务需求也可以在此实现，如多个不同数据源操作）
 *
 * @Author Siu
 * @Date 2020/3/15 14:16
 * @Version 0.0.1
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    private final Object primaryDataSource;
    private final Object secondaryDataSource;

    @Autowired
    public DynamicDataSource(Object primaryDataSource, Object secondaryDataSource) {
        this.primaryDataSource = primaryDataSource;
        this.secondaryDataSource = secondaryDataSource;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceId dsId = DynamicDataSourceHolder.get();
        if (DataSourceId.PRIMARY.equals(dsId)) {
            return DataSourceId.PRIMARY.name();
        } else {
            return DataSourceId.SECONDARY.name();
        }

    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(primaryDataSource, "主库不能为null");
        // 覆盖，设置默认的数据源
        setDefaultTargetDataSource(primaryDataSource);
        // 设置所有目标数据源<K,V>
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceId.PRIMARY.name(), primaryDataSource);
        if (secondaryDataSource != null) {
            targetDataSources.put(DataSourceId.SECONDARY.name(), secondaryDataSource);
        }
        setTargetDataSources(targetDataSources);

        super.afterPropertiesSet();
    }
}
