package org.hepx.jgt.common.datatable;

/**
 * @author: Koala
 * @Date: 14-8-28 下午4:28
 * @Version: 1.0
 */
public class Column {

    //数据绑定与后台的POJO字段对应
    private String data;
    //列名
    private String name;
    //字段是否支持排序
    private boolean orderable;
    //字段是否支持查询
    private boolean searchable;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }
}
