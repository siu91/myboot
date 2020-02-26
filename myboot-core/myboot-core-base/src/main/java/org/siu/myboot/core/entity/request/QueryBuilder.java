package org.siu.myboot.core.entity.request;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import org.siu.myboot.core.dsl.QBuiler;
import org.springframework.data.querydsl.QSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 查询构建工具类
 *
 * @Author Siu
 * @Date 2020/2/26 14:53
 * @Version 0.0.1
 */
public class QueryBuilder {

    /**
     * build sort
     *
     * @param sortList
     * @return
     */
    public static QSort buildSort(List<Sort> sortList, QBuiler qBuiler) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        if (Objects.nonNull(sortList)) {
            for (Sort s : sortList) {
                ComparableExpressionBase ceb = qBuiler.order(s.getOrder());
                if (Objects.nonNull(ceb)) {
                    OrderSpecifier o;
                    if (Order.DESC.toString().equals(s.getDirection().toUpperCase())) {
                        o = new OrderSpecifier(Order.DESC, ceb);
                    } else {
                        o = new OrderSpecifier(Order.ASC, ceb);
                    }
                    orderSpecifiers.add(o);
                }
            }
        }
        QSort sort = new QSort(orderSpecifiers);
        return sort;
    }
}
