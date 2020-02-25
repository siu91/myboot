package org.siu.myboot.core.entity.vo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页查询的分页对象
 *
 * @Author Siu
 * @Date 2020/2/25 16:02
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class PageData<T> {

    /**
     * 分页列表数据
     */
    private List<T> items;

    /**
     * 总数据量
     */
    private Long total;

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 每页条数
     */
    private Integer limit;

    /**
     * 是否存在下一页
     */
    private boolean hasNext;

    /**
     * 是否存在上一页
     */
    private boolean hasPrevious;

}
