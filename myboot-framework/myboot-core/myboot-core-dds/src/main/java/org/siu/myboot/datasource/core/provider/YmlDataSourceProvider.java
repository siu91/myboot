package org.siu.myboot.datasource.core.provider;

import lombok.AllArgsConstructor;
import org.siu.myboot.datasource.autoconfigure.properties.DataSourceProperty;
import org.siu.myboot.datasource.core.model.DataSourceDefinition;

import java.util.List;
import java.util.Map;

/**
 * DataSource Provider 实现类
 *
 * @Author Siu
 * @Date 2020/3/18 23:15
 * @Version 0.0.1
 */
@AllArgsConstructor
public class YmlDataSourceProvider extends AbstractDataSourceProvider implements DataSourceProvider {

    private Map<String, DataSourceProperty> ymlDataSourcePropertiesMap;


    @Override
    public List<DataSourceDefinition> buildDataSources() {
        return this.buildDataSources(ymlDataSourcePropertiesMap);
    }

}
