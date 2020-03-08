package org.siu.myboot.core.utils;


import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Strings工具
 *
 * @author gsw
 * @since 2019-08-16
 */
public class Strings {


    /**
     * 驼峰转下划线
     *
     * @param camelStr
     * @return
     */
    public static String camel2Underline(String camelStr) {

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

    /**
     * @param delimiter 分割符
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> String join(CharSequence delimiter, Collection<? super T> elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Object cs : elements) {
            joiner.add(cs.toString());
        }
        return joiner.toString();
    }


    /**
     * @param delimiter 分割符
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> String join(CharSequence delimiter, T... elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Object cs : elements) {
            joiner.add(cs.toString());
        }
        return joiner.toString();
    }


}
