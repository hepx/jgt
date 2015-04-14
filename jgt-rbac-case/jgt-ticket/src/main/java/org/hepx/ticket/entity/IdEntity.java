package org.hepx.ticket.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * Oracle需要每个Entity独立定义id的SEQUCENCE时，不继承于本类而改为实现一个Idable的接口。
 * 
 * @author hepx
 */
public abstract class IdEntity implements Serializable {

	protected Long id;  //编号

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @JsonIgnore
    public boolean isNew(){
        return this.id ==null;
    }

}
