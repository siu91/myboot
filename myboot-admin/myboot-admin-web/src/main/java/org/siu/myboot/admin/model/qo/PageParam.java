package org.siu.myboot.admin.model.qo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Siu
 * @Date 2020/2/17 22:11
 * @Version 0.0.1
 */
@Getter
@Setter
public class PageParam {
    /**
     * 起始行
     */
    private int beginLine;
    /**
     * 每页条数
     */
    private Integer pageSize = 10;
    /**
     * 当前页
     */
    private Integer currentPage = 0;


    public int getBeginLine() {
        return pageSize * currentPage;
    }


    @Override
    public String toString() {
        return "PageParam{" +
                "beginLine=" + beginLine +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
