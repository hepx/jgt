package org.hepx.ticket.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
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

    private BigDecimal ticketMoney;//票面金额

    private Date expireDate;   //到期日期

    private BigDecimal certifyFee;//证明费

    private BigDecimal ticketOdd;//票面零头

    private BigDecimal otherFee;//其它费用

    private BigDecimal inPoint;//进票点数

    private BigDecimal outPoint;//出票点数

    private BigDecimal inTicketSurplus;//进票票面实际金额

    private BigDecimal outTicketSurplus;//出票票面实际金额

    private Date outDate;//出票日期

    private Long tradeId;//票据所在交易

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

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public BigDecimal getTicketMoney() {
        return ticketMoney;
    }

    public void setTicketMoney(BigDecimal ticketMoney) {
        this.ticketMoney = ticketMoney;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public BigDecimal getCertifyFee() {
        return certifyFee;
    }

    public void setCertifyFee(BigDecimal certifyFee) {
        this.certifyFee = certifyFee;
    }

    public BigDecimal getTicketOdd() {
        return ticketOdd;
    }

    public void setTicketOdd(BigDecimal ticketOdd) {
        this.ticketOdd = ticketOdd;
    }

    public BigDecimal getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public BigDecimal getInPoint() {
        return inPoint;
    }

    public void setInPoint(BigDecimal inPoint) {
        this.inPoint = inPoint;
    }

    public BigDecimal getOutPoint() {
        return outPoint;
    }

    public void setOutPoint(BigDecimal outPoint) {
        this.outPoint = outPoint;
    }

    public BigDecimal getInTicketSurplus() {
        return inTicketSurplus;
    }

    public void setInTicketSurplus(BigDecimal inTicketSurplus) {
        this.inTicketSurplus = inTicketSurplus;
    }

    public BigDecimal getOutTicketSurplus() {
        return outTicketSurplus;
    }

    public void setOutTicketSurplus(BigDecimal outTicketSurplus) {
        this.outTicketSurplus = outTicketSurplus;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNo='" + ticketNo + '\'' +
                ", ticketMoney=" + ticketMoney +
                ", expireDate=" + expireDate +
                ", certifyFee=" + certifyFee +
                ", ticketOdd=" + ticketOdd +
                ", otherFee=" + otherFee +
                ", inPoint=" + inPoint +
                ", outPoint=" + outPoint +
                ", inTicketSurplus=" + inTicketSurplus +
                ", outTicketSurplus=" + outTicketSurplus +
                ", outDate=" + outDate +
                '}';
    }
}
