package org.siu.model;

import org.siu.annotation.TableField;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Siu
 * @Date 2020/2/18 15:27
 * @Version 0.0.1
 */
public class BaseEntity {

    public String baseColumnList() {
        List<String> columns = new ArrayList<>();
        Field[] fs = this.getClass().getDeclaredFields();
        //设置私有属性的访问权限
        // Field.setAccessible(true);
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

        return camel2Underline(StringUtils.collectionToDelimitedString(columns, ", "));

    }


    /**
     * 驼峰转下划线
     *
     * @param camelStr
     * @return
     */
    private String camel2Underline(String camelStr) {

        if (StringUtils.isEmpty(camelStr)) {
            return "";
        }

        int len = camelStr.length();
        StringBuilder strb = new StringBuilder(len + len >> 1);
        for (int i = 0; i < len; i++) {

            char c = camelStr.charAt(i);
            if (Character.isUpperCase(c)) {

                strb.append("_");
                strb.append(Character.toLowerCase(c));
            } else {

                strb.append(c);
            }
        }
        return strb.toString();
    }
}
