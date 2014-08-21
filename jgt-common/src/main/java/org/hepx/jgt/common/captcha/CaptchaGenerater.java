package org.hepx.jgt.common.captcha;

import org.hepx.jgt.common.random.RandomGenerater;
import org.hepx.jgt.common.random.RodomType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 验证码生成器
 *
 * @author: Koala
 * @Date: 14-8-21 下午5:18
 * @Version: 1.0
 */
public class CaptchaGenerater {

    private static int WIDTH = 100;
    private static int HEIGHT = 40;
    private static int NUM = 4;

    private static int RED = 240;
    private static int GREEN = 238;
    private static int BLUE = 229;

    /**
     * 生成默认为4位数字的验证码
     * @param request
     * @return
     */
    public static BufferedImage generateNumeric(HttpServletRequest request) {
        return generateNumeric(NUM,request);
    }

    /**
     * 生成指定长度数字的验证码
     * @param count
     * @param request
     * @return
     */
    public static BufferedImage generateNumeric(int count,HttpServletRequest request) {
        return generate(count,RodomType.NUM,request);
    }

    /**
     * 生成默认长度纯字符验证码
     * @param request
     * @return
     */
    public static BufferedImage generateAlphabetic(HttpServletRequest request) {
        return generateAlphabetic(NUM,request);
    }

    /**
     * 生成指定长度纯字符验证码
     * @param count
     * @param request
     * @return
     */
    public static BufferedImage generateAlphabetic(int count, HttpServletRequest request) {
        return generate(NUM,RodomType.CHR,request);
    }

    /**
     * 生成默认长度混合验证码
     * @param request
     * @return
     */
    public static BufferedImage generateMix(HttpServletRequest request) {
        return generateMix(NUM,request);
    }

    /**
     * 生成指定长度混合验证码
     * @param count
     * @param request
     * @return
     */
    public static BufferedImage generateMix(int count,HttpServletRequest request) {
        return generate(NUM,RodomType.MIX,request);
    }


    /**
     * 生成验证码
     * @param count
     * @param type
     * @param request
     * @return
     */
    private static BufferedImage generate(int count, RodomType type, HttpServletRequest request) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(new Color(RED, GREEN, BLUE));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        String code = null;
        switch (type) {
            case NUM: {
                code = RandomGenerater.generateNumeric(count);
                break;
            }
            case CHR: {
                code = RandomGenerater.generateAlphabetic(count);
                break;
            }
            case MIX: {
                code = RandomGenerater.generateMix(count);
                break;
            }
        }
        for (int i = 0; i < NUM; i++) {
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font(Integer.valueOf(Font.ITALIC).toString(), Font.ITALIC, HEIGHT + 10));
            g.drawString(code.substring(i, i + 1), (((i * WIDTH) / NUM) * 90) / 100, HEIGHT);
        }
        request.getSession().setAttribute("imageCode", code);
        return image;
    }

}
