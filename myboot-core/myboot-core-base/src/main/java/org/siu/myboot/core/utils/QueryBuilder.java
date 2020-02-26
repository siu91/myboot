package org.siu.myboot.core.utils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.dsl.QBuiler;
import org.siu.myboot.core.entity.BaseEntity;
import org.siu.myboot.core.entity.qo.Sort;
import org.springframework.data.querydsl.QSort;

import java.lang.reflect.Field;
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
                ComparableExpressionBase ceb = qBuiler.order(s.getField());
                if (Objects.nonNull(ceb)) {
                    OrderSpecifier o;
                    if (Order.DESC.toString().equals(s.getOrder().toUpperCase())) {
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


    /**
     * @param query
     * @param qBuiler
     * @param entity
     * @return
     */
    public static void buildCondition(JPAQuery query, QBuiler qBuiler, BaseEntity entity) {
        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);
                // 获取非空字段属性名
                if (field.get(entity) != null && !"".equals(field.get(entity))) {
                    String conditionName = field.getName();
                    query.where(qBuiler.condition(conditionName).eq(value));
                }
            }
        } catch (Exception e) {
            // do nothing
        }
    }


}
