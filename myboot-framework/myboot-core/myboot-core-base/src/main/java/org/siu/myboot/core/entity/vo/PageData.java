package org.siu.myboot.core.entity.vo;


import lombok.Data;
import lombok.experimental.Accessors;
import org.siu.myboot.core.entity.BaseEntity;
import org.siu.myboot.core.entity.qo.Pagination;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

/**
 * 分页查询的分页对象
 *
 * @Author Siu
 * @Date 2020/2/25 16:02
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class PageData {

    /**
     * 分页列表数据
     */
    private List<BaseEntity> items;

    /**
     * 总数据量
     */
    private Long total;

    /**
     * 总页数
     */
    private Integer totalPage;

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

    public PageData(Page page, Pagination pagination) {
        if (Objects.nonNull(page) && Objects.nonNull(pagination)) {
            this.items = page.getContent();
            this.total = page.getTotalElements();
            this.totalPage = page.getTotalPages();
            this.current = pagination.getViewPage();
            this.limit = pagination.getLimit();
            this.hasNext = page.hasNext();
            this.hasPrevious = page.hasPrevious();
        }


    }


}
