package org.siu.myboot.core.data.dds;

import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

/**
 * 动态数据源切换
 *
 * @Author Siu
 * @Date 2020/3/15 14:28
 * @Version 0.0.1
 */
@UtilityClass
public class DynamicDataSourceHolder {
    public static final ThreadLocal<DataSourceId> CURRENT_DATA_SOURCE_ID = new ThreadLocal<>();

    /**
     * 选择数据源
     *
     * @param dsId 数据源标识
     */
    public static void select(DataSourceId dsId) {
        Assert.notNull(dsId, "非法数据源标识：" + dsId);
        CURRENT_DATA_SOURCE_ID.set(dsId);
    }


    /**
     * 获取当前数据源
     *
     * @return
     */
    public static DataSourceId get() {
        return CURRENT_DATA_SOURCE_ID.get() == null ? DataSourceId.PRIMARY : CURRENT_DATA_SOURCE_ID.get();
    }

    /**
     * 清除数据源
     */
    public static void clear() {
        CURRENT_DATA_SOURCE_ID.remove();
    }

}
