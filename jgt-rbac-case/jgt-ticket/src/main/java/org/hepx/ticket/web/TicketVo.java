package org.hepx.ticket.web;

import org.hepx.ticket.entity.Ticket;
import org.hepx.ticket.entity.Trade;

import java.util.List;

/**
 * User: hepx
 * Date: 15-4-19
 * Time: 下午4:31
 */
public class TicketVo {

    private Trade trade;

    private List<Ticket> inTickets;

    private List<Ticket> outTickets;

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
