package org.hepx.jgt.common.datatable;

/**
 * @author: Koala
 * @Date: 14-8-28 下午4:26
 * @Version: 1.0
 */
public class Order{
    //列索引，0表示第一列
    private int column;
    //排序类型 asc， desc
    private String dir;

    public Order() {
    }

    public Order(int column, String dir) {
        this.column = column;
        this.dir = dir;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
