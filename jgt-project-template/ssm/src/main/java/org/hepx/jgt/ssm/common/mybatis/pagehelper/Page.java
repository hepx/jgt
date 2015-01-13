package org.hepx.jgt.ssm.common.mybatis.pagehelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页基本数据
 * User: hepanxi
 * Date: 15-1-5
 * Time: 下午5:30
 */
public class Page<T> {

    private int pageNo = 1;//页码，默认是第一页

    private int pageSize = 20;//每页显示的记录数，默认是15

    private int totalRecord;//总记录数

    private int totalPage;//总页数

    private List<T> results;//对应的当前页记录

    private Map<String, Object> params = new HashMap<String, Object>();//其他的参数我们把它分装成一个Map对象

    public Page(){}

    public Page(int pageNo, int pageSize, Map<String, Object> params) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.params = params;
    }

    /**
     * 添加单个查询参数
     * @param key
     * @param value
     */
    public void addParameter(String key,Object value){
        this.params.put(key,value);
    }

    //是否有上一页
    public boolean hasPreviousPage(){
       if(this.pageNo<=1){
           return false;
       }else{
           return true;
       }
    }

    //是否有下一页
    public boolean hasNextPage(){
        if(this.pageNo>=this.totalPage){
            return false;
        }else{
            return true;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        //设置总页数
        this.totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                ", results=" + results +
                ", params=" + params +
                '}';
    }
}
