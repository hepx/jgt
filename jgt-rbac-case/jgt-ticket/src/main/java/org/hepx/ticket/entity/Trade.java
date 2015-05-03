package org.hepx.ticket.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易
 * User: hepanxi
 * Date: 15-4-18
 * Time: 上午10:11
 */
@Alias("t_trade")
public class Trade extends IdEntity implements Serializable {

    private String tradeNo;//交易号

    private Date createTime;//交易日期

    private double inTicketMoney;    //进票总额

    private double outTicketMoney;   //出票总额

    private double tradeTotal;//合计

    private String name;

    private String telphone;

    private String idCard;

    private TradeStatus tradeStatus=TradeStatus.UNCHECKED;//交易状态

    public static enum TradeStatus{

        CHECKED("已核对"),UNCHECKED("未核对");

        private String value;

        private TradeStatus(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public double getInTicketMoney() {
        return inTicketMoney;
    }

    public void setInTicketMoney(double inTicketMoney) {
        this.inTicketMoney = inTicketMoney;
    }

    public double getOutTicketMoney() {
        return outTicketMoney;
    }

    public void setOutTicketMoney(double outTicketMoney) {
        this.outTicketMoney = outTicketMoney;
    }

    public double getTradeTotal() {
        return tradeTotal;
    }

    public void setTradeTotal(double tradeTotal) {
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
