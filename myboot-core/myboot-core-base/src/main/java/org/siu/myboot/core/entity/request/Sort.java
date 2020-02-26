package org.siu.myboot.core.entity.request;

import com.querydsl.core.types.Order;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * 排序参数
 *
 * @Author Siu
 * @Date 2020/2/26 10:45
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class Sort {

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序类型： asc/desc
     * <p>
     * 默认 asc
     */
    private String order;

    public void setOrder(String order) {
        if (Objects.nonNull(order)) {
            if (Order.ASC.toString().equals(order.toUpperCase()) || Order.DESC.toString().equals(order.toUpperCase())) {
                this.order = order;
            } else {
                this.order = Order.ASC.toString();
            }
        } else {
            this.order = Order.ASC.toString();
        }
    }
}
