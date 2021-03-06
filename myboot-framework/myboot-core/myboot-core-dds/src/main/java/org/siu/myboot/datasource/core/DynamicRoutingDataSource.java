package org.siu.myboot.datasource.core;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.datasource.context.DynamicDataSourceContextHolder;
import org.siu.myboot.datasource.core.exception.NotFoundPrimaryDataSourceError;
import org.siu.myboot.datasource.core.model.DataSourceContainer;
import org.siu.myboot.datasource.core.model.DataSourceDefinition;
import org.siu.myboot.datasource.core.provider.DataSourceProvider;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.util.List;

/**
 * 动态数据源实现类
 *
 * @Author Siu
 * @Date 2020/3/18 22:45
 * @Version 0.0.1
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource implements InitializingBean, DisposableBean {

    @Setter
    private DataSourceProvider provider;

    /**
     * 数据源容器
     */
    private DataSourceContainer dataSourceContainer;

    /**
     * 主数据源标识
     */
    @Setter
    private String primary;

    @Override
    public void afterPropertiesSet() throws NotFoundPrimaryDataSourceError {
        // 创建数据源
        List<DataSourceDefinition> dataSources = this.provider.buildDataSources();
        // 加载数据源到容器中
        this.dataSourceContainer = new DataSourceContainer(this.primary);
        this.dataSourceContainer.load(dataSources);
        // 刷新容器中数据源
        this.dataSourceContainer.flush();

    }


    @Override
    protected DataSource determineDataSource() {
        String key = DynamicDataSourceContextHolder.peek();
        return dataSourceContainer.lookupDataSource(key);
    }

    @Override
    public void destroy() throws Exception {
        this.dataSourceContainer.destroy();
    }

}
