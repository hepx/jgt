package org.hepx.ticket.web;

import org.hepx.ticket.entity.Payment;
import org.hepx.ticket.entity.Ticket;
import org.hepx.ticket.entity.Trade;

import java.util.List;

/**
 * User: hepx
 * Date: 15-4-19
 * Time: 下午4:31
 */
public class TicketVo {

    private Trade trade;  //交易信息

    private List<Ticket> inTickets;   //进票

    private List<Ticket> outTickets;  //出票

    private List<Payment> payments;   //支付

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public List<Ticket> getInTickets() {
        return inTickets;
    }

    public void setInTickets(List<Ticket> inTickets) {
        this.inTickets = inTickets;
    }

    public List<Ticket> getOutTickets() {
        return outTickets;
    }

    public void setOutTickets(List<Ticket> outTickets) {
        this.outTickets = outTickets;
    }
}
