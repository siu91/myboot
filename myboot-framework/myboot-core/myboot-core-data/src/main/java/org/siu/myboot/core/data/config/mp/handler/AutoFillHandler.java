package org.siu.myboot.core.data.config.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充处理（未填充自动处理）
 *
 * @Author Siu
 * @Date 2020/3/16 10:05
 * @Version 0.0.1
 */
@Component
public class AutoFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 删除标志
        Object deleteStatus = getFieldValByName("deleteStatus", metaObject);
        // 创建时间
        Object createTime = getFieldValByName("createTime", metaObject);
        // 更新时间
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", new Date(), metaObject);
        }

        // 0 未删除，删除设为null
        if (deleteStatus == null) {
            setFieldValByName("deleteStatus", 0, metaObject);
        }

        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时自动更新 updateTime
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}
