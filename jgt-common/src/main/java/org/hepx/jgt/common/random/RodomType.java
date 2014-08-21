package org.hepx.jgt.common.random;

/**
 * 随机数类型
 * @author: Koala
 * @Date: 14-8-21 下午5:54
 * @Version: 1.0
 */
public enum RodomType {

    NUM("数字"),CHR("字符"),MIX("混合");

    private String value;

    private RodomType(String value){
        this.value=value;
    }

    public String getValue(){
        return this.value;
    }

}

