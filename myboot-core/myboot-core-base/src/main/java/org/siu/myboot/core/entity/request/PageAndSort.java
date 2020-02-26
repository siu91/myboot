package org.siu.myboot.core.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页&排序参数
 *
 * @Author Siu
 * @Date 2020/2/25 20:29
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class PageAndSort {


    /**
     * 前端传的页码，规定前端页码从1开始，set的时候转成后端的值
     * <p>
     * 后端默认从0开始
     */
    private int page = 0;

    /**
     * 每页记录数
     */
    private int limit = 10;

    /**
     * 排序参数
     */
    private List<Sort> sort;

    /**
     * set 时转成后端的页码
     *
     * @param page
     */
    public void setPage(int page) {
        if (page > 0) {
            this.page = page - 1;
        }
    }

    /**
     * 获取前端页码
     *
     * @return
     */
    public int getViewPage() {
        return page + 1;
    }
}
