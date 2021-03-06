package org.siu.myboot.datasource.core.strategy;

import javax.sql.DataSource;
import java.util.List;

/**
 * 数据源命中策略
 *
 * @Author Siu
 * @Date 2020/3/19 14:07
 * @Version 0.0.1
 */
public interface DataSourceSelectionStrategy {

    /**
     * 从候选数据源中挑选数据源
     *
     * @param candidate
     * @return
     */
    DataSource lookup(List<DataSource> candidate);
}
