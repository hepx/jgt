package org.hepx.jgt.common.random;

import java.text.NumberFormat;

/**
 * 编号生成器，不适合分布式环镜
 * User: hepanxi
 * Date: 15-4-18
 * Time: 上午10:51
 */
public class NumberGenerater {

    /**
     * 将指定的数据转换成00001这样的编号
     * 默认为5位
     * @param number
     * @return
     */
    public static String  generate(Long number){
        return generate(5,number);
    }

    /**
     * 将指定的数据转换成00001这样的编号
     * 位数由digists指定
     * @param digits
     * @param number
     * @return
     */
    public static String generate(int digits,Long number){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumIntegerDigits(digits);
        nf.setMinimumIntegerDigits(digits);
        return nf.format(number);
    }

}
