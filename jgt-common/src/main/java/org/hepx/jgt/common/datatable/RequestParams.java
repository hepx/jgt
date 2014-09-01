package org.hepx.jgt.common.datatable;

import org.hepx.jgt.common.datatable.type.JpaPageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对JQUERY DATATABLE控件封装的JAVABEAN
 *
 * @author: Koala
 * @Date: 14-8-28 下午1:58
 * @Version: 1.0
 */
public class RequestParams implements JpaPageable {

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_START = 0;
    private static final int DEFAULT_PAGE = 0;

    /**
     * 请求次数
     */
    private Integer draw;
    /**
     * 分页起点
     */
    private Integer start;
    /**
     * 每页显示数量
     */
    private Integer length;
    /**
     * 页码，第几页
     */
    private Integer page;
    /**
     * 查询过滤内容
     */
    private Search search;
    /**
     * 列字段
     */
    private List<Column> columns;
    /**
     * 排序字段
     */
    private List<Order> orders;

    @Override
    public Pageable buildPageable() {
        Pageable pageable=new PageRequest(getPage(),getLength(),buildSort());
        return pageable;
    }

    @Override
    public Sort buildSort() {
        if (orders != null && orders.size() > 0) {
            List<Sort.Order>s_orders=new ArrayList<Sort.Order>();
            for(Order o:orders){
                Sort.Order s_o=new Sort.Order(Sort.Direction.fromString(o.getDir()),this.columns.get(o.getColumn()).getData());
                s_orders.add(s_o);
            }
            return new Sort(s_orders);
        } else {
            return null;
        }
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        if (start == null) {
            return DEFAULT_START;
        } else {
            return start;
        }
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        if (length == null) {
            return DEFAULT_PAGE_SIZE;
        } else {
            return length;
        }
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getPage() {
        if (this.start == 0) {
            return DEFAULT_PAGE;
        } else {
            return this.length / this.start;
        }
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
