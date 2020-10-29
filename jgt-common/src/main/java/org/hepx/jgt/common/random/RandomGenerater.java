package org.hepx.jgt.common.random;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * 随机数生成器
 *
 * @author: Koala
 * @Date: 14-8-21 下午5:26
 * @Version: 1.0
 */
public class RandomGenerater {


    /**
     * 生成纯数字的随机数
     *
     * @param count
     * @return
     */
    public static String generateNumeric(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 生成纯字母随机数
     *
     * @param count
     * @return
     */
    public static String generateAlphabetic(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    /**
     * 生成混合型随机数
     *
     * @param count
     * @return
     */
    public static String generateMix(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    /**
     * 生成给定字符数组随机数
     * @param count
     * @param c
     * @return
     */
    public static String generate(int count, char[] c) {
        return RandomStringUtils.random(count, c);
    }


    /**
     * 获取去掉"-" UUID
     * @return
     */
    public static String generate32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取不去掉"-" UUID
     * @return
     */
    public static String generate32_UUID(){
        return UUID.randomUUID().toString();
    }


}
