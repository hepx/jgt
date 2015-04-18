package org.hepx.ticket.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 交易
 * User: hepanxi
 * Date: 15-4-18
 * Time: 上午10:11
 */
@Alias("t_trade")
public class Trade extends IdEntity implements Serializable {

    private String tradeNo;//交易号

    private BigDecimal inTicketMoney;    //进票总额

    private BigDecimal outTicketMoney;   //出票总额

    private BigDecimal tradeTotal;//合计

    private String name;

    private String telphone;

    private String idCard;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public BigDecimal getInTicketMoney() {
        return inTicketMoney;
    }

    public void setInTicketMoney(BigDecimal inTicketMoney) {
        this.inTicketMoney = inTicketMoney;
    }

    public BigDecimal getOutTicketMoney() {
        return outTicketMoney;
    }

    public void setOutTicketMoney(BigDecimal outTicketMoney) {
        this.outTicketMoney = outTicketMoney;
    }

    public BigDecimal getTradeTotal() {
        return tradeTotal;
    }

    public void setTradeTotal(BigDecimal tradeTotal) {
        this.tradeTotal = tradeTotal;
    }

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

    @Override
    public String toString() {
        return "Trade{" +
                "tradeNo='" + tradeNo + '\'' +
                ", inTicketMoney=" + inTicketMoney +
                ", outTicketMoney=" + outTicketMoney +
                ", tradeTotal=" + tradeTotal +
                ", name='" + name + '\'' +
                ", telphone='" + telphone + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
