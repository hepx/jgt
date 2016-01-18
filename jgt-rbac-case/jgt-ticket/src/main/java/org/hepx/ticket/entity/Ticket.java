package org.hepx.ticket.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * 票据
 * User: hepanxi
 * Date: 15-4-15
 * Time: 上午10:14
 */
@Alias("t_ticket")
public class Ticket extends IdEntity implements Serializable {

    private String ticketNo;//票号8位

    private double ticketMoney;//票面金额

    private Date expireDate;   //到期日期

    private Date inDate;    //进票日期

    private double certifyFee;//证明费

    private double ticketOdd;//票面零头

    private double otherFee;//其它费用

    private double inPoint;//进票点数

    private double outPoint;//出票点数

    private double inTicketSurplus;//进票票面实际金额

    private double outTicketSurplus;//出票票面实际金额

    private double discountAmount; //出票贴现金额

    private Date outDate;//出票日期

    private Long inTradeId;//进票票据所在交易

    private Long outTradeId;//出票票据所在交易

    private TicketStatus ticketStatus; //票据状态

    public static enum TicketStatus{

        EXISTED("在库"),SALED("已售出");

        private String value;

        private TicketStatus(String value){
            this.value= value;
        }

        public String getValue() {
            return value;
        }
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public double getTicketMoney() {
        return ticketMoney;
    }

    public void setTicketMoney(double ticketMoney) {
        this.ticketMoney = ticketMoney;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public double getCertifyFee() {
        return certifyFee;
    }

    public void setCertifyFee(double certifyFee) {
        this.certifyFee = certifyFee;
    }

    public double getTicketOdd() {
        return ticketOdd;
    }

    public void setTicketOdd(double ticketOdd) {
        this.ticketOdd = ticketOdd;
    }

    public double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(double otherFee) {
        this.otherFee = otherFee;
    }

    public double getInPoint() {
        return inPoint;
    }

    public void setInPoint(double inPoint) {
        this.inPoint = inPoint;
    }

    public double getOutPoint() {
        return outPoint;
    }

    public void setOutPoint(double outPoint) {
        this.outPoint = outPoint;
    }

    public double getInTicketSurplus() {
        return inTicketSurplus;
    }

    public void setInTicketSurplus(double inTicketSurplus) {
        this.inTicketSurplus = inTicketSurplus;
    }

    public double getOutTicketSurplus() {
        return outTicketSurplus;
    }

    public void setOutTicketSurplus(double outTicketSurplus) {
        this.outTicketSurplus = outTicketSurplus;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Long getInTradeId() {
        return inTradeId;
    }

    public void setInTradeId(Long inTradeId) {
        this.inTradeId = inTradeId;
    }

    public Long getOutTradeId() {
        return outTradeId;
    }

    public void setOutTradeId(Long outTradeId) {
        this.outTradeId = outTradeId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }


}
