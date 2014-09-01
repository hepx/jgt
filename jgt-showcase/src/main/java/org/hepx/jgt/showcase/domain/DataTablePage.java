package org.hepx.jgt.showcase.domain;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 适配前台JQUERY DATATABLES数据格式
 * @author: Koala
 * @Date: 14-8-15 上午10:06
 * @Version: 1.0
 */
public class DataTablePage<T>{

    public DataTablePage(long recordsTotal, List<T> data, long totalPages) {
        this.recordsFiltered= recordsTotal;
        this.recordsTotal = recordsTotal;
        this.data = data;
        this.totalPages = totalPages;
    }

    public DataTablePage(long totalPages, long recordsTotal, long recordsFiltered, List<T> data) {
        this.totalPages = totalPages;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public DataTablePage(Page<T> page){
        this.recordsFiltered=page.getTotalElements();
        this.totalPages=page.getTotalPages();
        this.recordsTotal=page.getTotalElements();
        this.data=page.getContent();
    }

    private long totalPages;

    private long recordsTotal;

    private long recordsFiltered;

    private List<T> data;

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
