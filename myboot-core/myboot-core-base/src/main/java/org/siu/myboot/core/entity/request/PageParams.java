package org.siu.myboot.core.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Siu
 * @Date 2020/2/25 22:49
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class PageParams<T> {
    /**
     * 分页与排序参数
     */
    private Page page;

    /**
     * 查询实体参数
     */
    private T item;
}
