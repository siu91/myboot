package org.siu.myboot.utils.date;


import java.util.HashMap;
import java.util.Map;

/**
 * <code>DateStyle </code>
 * <p>
 * DateStyle 日期工具类：日期风格
 *
 * @author Siu
 * @version v0.1 2018/09/18
 * @see
 * @since JDK1.8
 */
public enum DateStyle {
    /**
     *
     */
    YYYY_MM("yyyy-MM", false),
    YYYYMM("yyyyMM", false),
    YYYY_MM_DD("yyyy-MM-dd", false),
    YYYYMMDD("yyyyMMdd", false),
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm", false),
    YYYYMMDDHHMM("yyyyMMddHHmm", false),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", false),
    YYYY_MM_DD_HH_MM_SS_sss("yyyy-MM-dd HH:mm:ss.SSS", false),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss", false),
    YYYYMMDDHH_MM_SS("yyyyMMddHH:mm:ss", false),
    YYYYMMDDHHMMSSsss("yyyyMMddHHmmssSSS", false),

    YYYY_MM_EN("yyyy/MM", false),
    YYYY_MM_DD_EN("yyyy/MM/dd", false),
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm", false),
    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss", false),
    YYYY_MM_DD_HH_MM_SS_EN_sss("yyyy/MM/dd HH:mm:ss.SSS", false),

    MM_YYYY_EN("MM/yyyy", false),
    DD_MM_YYYY_EN("dd/MM/yyyy", false),
    DD_MM_YYYY_HH_MM_EN("dd/MM/yyyy HH:mm", false),
    DD_MM_YYYY_HH_MM_SS_EN("dd/MM/yyyy HH:mm:ss", false),
    DD_MM_YYYY_HH_MM_SS_EN_sss("dd/MM/yyyy HH:mm:ss.SSS", false),

    YYYY_MM_CN("yyyy年MM月", false),
    YYYY_MM_DD_CN("yyyy年MM月dd日", false),
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm", false),
    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss", false),
    YYYY_MM_DD_HH_MM_SS_CN_sss("yyyy年MM月dd日 HH:mm:ss.SSS", false),

    HH_MM_("HH:mm", false),
    HH_MM_SS_("HH:mm:ss", false),

    HH_MM("HH:mm", true),
    HH_MM_SS("HH:mm:ss", true),

    MM_DD("MM-dd", true),
    MM_DD_HH_MM("MM-dd HH:mm", true),
    MM_DD_HH_MM_SS("MM-dd HH:mm:ss", true),

    MM_DD_EN("MM/dd", true),
    MM_DD_HH_MM_EN("MM/dd HH:mm", true),
    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss", true),

    MM_DD_CN("MM月dd日", true),
    MM_DD_HH_MM_CN("MM月dd日 HH:mm", true),
    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss", true);


    private String value;

    private boolean isShowOnly;

    private static Map<String, DateStyle> styleMap;

    static {
        styleMap = new HashMap<>();
        for (DateStyle style : DateStyle.values()) {
            styleMap.put(style.getValue(), style);
        }
    }

    DateStyle(String value, boolean isShowOnly) {
        this.value = value;
        this.isShowOnly = isShowOnly;
    }

    public static DateStyle getDateStyle(String style) {
        return styleMap.getOrDefault(style, YYYYMMDD);
    }

    public String getValue() {
        return value;
    }

    public boolean isShowOnly() {
        return isShowOnly;
    }

}