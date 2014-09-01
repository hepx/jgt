package org.hepx.jgt.showcase.domain;

import java.util.List;
import java.util.Map;

/**
 * datatables接收参数
 * @author: Koala
 * @Date: 14-8-18 下午2:13
 * @Version: 1.0
 */
public class DataTableCriteria {

    private int draw;//请求次数
    private int start;//分页开始
    private int length;//每一页数量

    private Map<SearchCriterias, String> search;

    private List<Map<ColumnCriterias, String>> columns;

    private List<Map<OrderCriterias, String>> order;

    public enum SearchCriterias {
        value,
        regex
    }
    public enum OrderCriterias {
        column,
        dir
    }
    public enum ColumnCriterias {
        data,
        name,
        searchable,
        orderable,
        searchValue,
        searchRegex
    }

    public List<Map<OrderCriterias, String>> getOrder() {
        return order;
    }

    public void setOrder(List<Map<OrderCriterias, String>> order) {
        this.order = order;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Map<SearchCriterias, String> getSearch() {
        return search;
    }

    public void setSearch(Map<SearchCriterias, String> search) {
        this.search = search;
    }

    public List<Map<ColumnCriterias, String>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<ColumnCriterias, String>> columns) {
        this.columns = columns;
    }
}
