package org.hepx.ticket.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * User: hepanxi
 * Date: 15-4-15
 * Time: 上午10:14
 */
@Alias("t_ticket")
public class Ticket extends IdEntity implements Serializable {

    private String ticketNo;//票号8位

    private Double ticketMoney;//票面金额

    private Date expireDate;   //到期日期

    private Double certifyFee;//证明费

    private Double ticketOdd;//票面零头

    private Double inPoint;//进票点数

    private Double otherFee;//其它费用

    private Double ticketSurplus;//票面实际金额/票面结余

    public Double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Double getTicketMoney() {
        return ticketMoney;
    }

    public void setTicketMoney(Double ticketMoney) {
        this.ticketMoney = ticketMoney;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Double getCertifyFee() {
        return certifyFee;
    }

    public void setCertifyFee(Double certifyFee) {
        this.certifyFee = certifyFee;
    }

    public Double getTicketOdd() {
        return ticketOdd;
    }

    public void setTicketOdd(Double ticketOdd) {
        this.ticketOdd = ticketOdd;
    }

    public Double getInPoint() {
        return inPoint;
    }

    public void setInPoint(Double inPoint) {
        this.inPoint = inPoint;
    }

    public Double getTicketSurplus() {
        return ticketSurplus;
    }

    public void setTicketSurplus(Double ticketSurplus) {
        this.ticketSurplus = ticketSurplus;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNo='" + ticketNo + '\'' +
                ", ticketMoney=" + ticketMoney +
                ", expireDate=" + expireDate +
                ", certifyFee=" + certifyFee +
                ", ticketOdd=" + ticketOdd +
                ", inPoint=" + inPoint +
                ", ticketSurplus=" + ticketSurplus +
                '}';
    }
}
