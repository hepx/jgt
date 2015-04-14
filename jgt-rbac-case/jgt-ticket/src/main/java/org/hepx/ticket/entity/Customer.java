package org.hepx.ticket.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * User: hepanxi
 * Date: 15-4-14
 * Time: 下午2:25
 */
@Alias("t_customer")
public class Customer extends IdEntity implements Serializable {

    private String name;

    private String telphone;

    private String idCard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
