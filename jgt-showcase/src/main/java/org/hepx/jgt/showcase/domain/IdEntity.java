package org.hepx.jgt.showcase.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author: Koala
 * @Date: 14-7-29 上午10:11
 * @Version: 1.0
 */
@MappedSuperclass
public class IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JSONField(serialize = false)
    public boolean isNew() {
        return this.id == null;
    }
}
