package org.siu.myboot.core.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.querydsl.QSort;

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
public class Page {


    /**
     * 前端传的页码，规定前端页码从1开始
     */
    private int pageNum = 1;

    /**
     * 每页记录数
     */
    private int limit = 10;

    /**
     * 排序参数
     */
    private List<String> sort;

    /**
     * 排序方式 asc/desc
     */
    private List<String> direction;

    /**
     * set 时转成后端的页码
     *
     * @param pageNum
     */
    public void setPageNum(int pageNum) {
        if (pageNum > 0) {
            this.pageNum = pageNum - 1;
        }
    }


}
