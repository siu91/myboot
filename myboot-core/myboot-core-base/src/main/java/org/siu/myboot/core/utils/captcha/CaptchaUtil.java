package org.siu.myboot.core.utils.captcha;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Properties;

import static com.google.code.kaptcha.Constants.*;

/**
 * 验证码工具
 *
 * @Author Siu
 * @Date 2020/2/29 14:15
 * @Version 0.0.1
 */
public class CaptchaUtil {


    private static CaptchaUtil captchaUtil = null;

    private static Producer producer;


    private CaptchaUtil(int characterNum) {
        Properties properties = new Properties();
        properties.put(KAPTCHA_BORDER, "no");
        properties.put(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        properties.put(KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "" + characterNum);
        properties.put(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
        properties.put(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "25");
        properties.put(KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        properties.put(KAPTCHA_BACKGROUND_CLR_FROM, "white");
        properties.put(KAPTCHA_BACKGROUND_CLR_TO, "white");
        properties.put(KAPTCHA_IMAGE_WIDTH, "150");
        properties.put(KAPTCHA_IMAGE_HEIGHT, "35");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        producer = defaultKaptcha;
    }


    /**
     * 获取实例
     *
     * @param characterNum 要生成的验证码的字符个数
     * @return
     */
    public static CaptchaUtil getInstance(Integer... characterNum) {
        if (Objects.isNull(captchaUtil)) {
            captchaUtil = new CaptchaUtil(characterNum == null ? 5 : characterNum[0]);
        }
        return captchaUtil;
    }


    /**
     * 文本
     *
     * @return
     */
    public String textCaptcha() {
        return producer.createText();
    }


    /**
     * 图片
     *
     * @param text
     * @return
     */
    public BufferedImage captcha(String text) {
        return producer.createImage(text);
    }


}
