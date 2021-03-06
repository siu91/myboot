package org.siu.myboot.datasource.core.provider;

import org.siu.myboot.datasource.core.model.DataSourceDefinition;

import java.util.List;

/**
 * @Author Siu
 * @Date 2020/3/18 22:49
 * @Version 0.0.1
 */
public interface DataSourceProvider {

    /**
     * 加载所有数据源
     *
     * @return
     */
    List<DataSourceDefinition> buildDataSources();

}
