package org.hepx.jgt.common.datatable;

/**
 * @author: Koala
 * @Date: 14-8-28 下午4:27
 * @Version: 1.0
 */
public class Search {

    //是否支持正则
    private boolean regex;
    //输入的查询内容
    private String value;

    public boolean isRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
