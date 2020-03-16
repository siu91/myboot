package org.siu.myboot.core.data.dds;

/**
 * 标识数据库
 * <p>
 * 如：读写类型区分、多数据源区分
 *
 * @Author Siu
 * @Date 2020/3/15 14:14
 * @Version 0.0.1
 */
public enum DataSourceId {
    /**
     * 读写类型区分
     */
    //WRITE, READ,

    /**
     * 多数据源
     */
    PRIMARY, SECONDARY,

    /**
     * 主从标识
     */
    //MASTER, SLAVE0, SLAVE1,
}
