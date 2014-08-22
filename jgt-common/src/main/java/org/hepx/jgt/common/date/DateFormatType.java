package org.hepx.jgt.common.date;

/**
 * 日期format类型
 * @author: Koala
 * @Date: 14-8-22 下午5:03
 * @Version: 1.0
 */
public enum DateFormatType {
    DATE("yyyy-MM-dd"),
    DATE_S("yyyyMMdd"),
    DATETIME("yyyy-MM-dd HH:mm:ss"),
    DATETIME_S("yyyyMMddHHmmss");

    private String value;

    private DateFormatType(String value){
        this.value=value;
    }

    public String getValue() {
        return this.value;
    }
}
