package org.siu.myboot.core.entity.qo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * 通用分页/排序查询参数
 *
 * @Author Siu
 * @Date 2020/2/25 22:49
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class PageParams<T> extends Pagination {
    /**
     * 查询实体参数
     */
    private T terms;
}
