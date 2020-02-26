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
    private String order;

    /**
     * 排序类型： asc/desc
     * <p>
     * 默认 asc
     */
    private String direction;

    public void setDirection(String direction) {
        if (Objects.nonNull(direction)) {
            if (Order.ASC.toString().equals(direction.toUpperCase()) || Order.DESC.toString().equals(direction.toUpperCase())) {
                this.direction = direction;
            } else {
                this.direction = Order.ASC.toString();
            }
        } else {
            this.direction = Order.ASC.toString();
        }
    }
}
