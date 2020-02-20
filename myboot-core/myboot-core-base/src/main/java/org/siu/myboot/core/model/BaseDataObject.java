package org.siu.myboot.core.model;

import org.siu.myboot.core.annotation.TableField;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.siu.myboot.core.utils.Strings;


/**
 * 基础数据对象
 *
 * @Author Siu
 * @Date 2020/2/18 15:27
 * @Version 0.0.1
 */
public class BaseDataObject {

    public String baseColumnList() {
        List<String> columns = new ArrayList<>();
        Field[] fs = this.getClass().getDeclaredFields();
        for (Field f : fs) {
            boolean add = true;
            String columnName = null;
            Annotation[] annotations = f.getAnnotations();
            for (Annotation a : annotations) {
                if (a instanceof TableField) {
                    if (!((TableField) a).exist()) {
                        add = false;
                        break;
                    }
                    if (!StringUtils.isEmpty(((TableField) a).value())) {
                        columnName = ((TableField) a).value();
                    }
                }
            }
            if (add) {
                if (columnName == null) {
                    columns.add(f.getName());
                } else {
                    columns.add(columnName);
                }

            }
        }

        return Strings.camel2Underline(StringUtils.collectionToDelimitedString(columns, ", "));

    }


}
