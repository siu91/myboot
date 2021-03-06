package org.siu.myboot.datasource.core.provider;

import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.datasource.autoconfigure.properties.DataSourceProperty;
import org.siu.myboot.datasource.core.model.DataSourceDefinition;
import org.siu.myboot.datasource.core.provider.builder.DataSourceBuilder;
import org.siu.myboot.datasource.core.provider.warp.DataSourceWarp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.*;

/**
 * @Author Siu
 * @Date 2020/3/18 23:12
 * @Version 0.0.1
 */
@Slf4j
public abstract class AbstractDataSourceProvider implements DataSourceProvider {


    @Autowired
    private DataSourceBuilder dataSourceBuilder;

    @Autowired
    private DataSourceWarp dataSourceWarp;


    /**
     * 从配置中构建所有数据源
     *
     * @param dataSourcePropertiesMap
     * @return
     */
    protected List<DataSourceDefinition> buildDataSources(Map<String, DataSourceProperty> dataSourcePropertiesMap) {
        List<DataSourceDefinition> dataSourceDefinitionList = new LinkedList<>();

        for (Map.Entry<String, DataSourceProperty> item : dataSourcePropertiesMap.entrySet()) {
            DataSourceProperty dataSourceProperty = item.getValue();
            DataSource dataSource = dataSourceBuilder.builderDataSource(dataSourceProperty);
            dataSource = dataSourceWarp.warp(dataSource);
            DataSourceDefinition dataSourceDefinition = new DataSourceDefinition(item.getKey(), dataSource);
            dataSourceDefinitionList.add(dataSourceDefinition);
        }

        return dataSourceDefinitionList;
    }


}
