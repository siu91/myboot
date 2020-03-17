package org.siu.myboot.core.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;


/**
 * uuid 工具
 *
 * @Author Siu
 * @Date 2020/3/17 11:06
 * @Version 0.0.1
 */
@UtilityClass
public class UUID {


    private static final Random RANDOM = new Random();

    /**
     * 获取UUID
     *
     * @return
     */
    public static String randomUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 基于nanoTime+ 随机数，转成36进制
     *
     * @return
     */
    public static String nanoTimeUUID() {
        return Long.toString(System.nanoTime() + RANDOM.nextInt(100), Character.MAX_RADIX);
    }

}
