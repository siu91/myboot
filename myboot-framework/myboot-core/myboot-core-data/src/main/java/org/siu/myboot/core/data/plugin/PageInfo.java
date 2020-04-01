package org.siu.myboot.core.data.plugin;

import lombok.Data;

/**
 * @Author Siu
 * @Date 2020/4/1 21:38
 * @Version 0.0.1
 */
@Data
public class PageInfo {

    private int current;
    private int limit;
    private int total;
}
