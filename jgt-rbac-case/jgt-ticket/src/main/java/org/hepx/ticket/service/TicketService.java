package org.hepx.ticket.service;

import org.hepx.ticket.entity.Ticket;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface TicketService {

    public Ticket createTicket(Ticket ticket);

    public int updateTicket(Ticket ticket);

    public int deleteTicket(Long ticketId);

    public Ticket findOne(Long ticketId);

    public List<Ticket> findAll();

    public List<Ticket> findAll(Ticket ticket);

    public List<Ticket> findByCondition(Map<String,Object> paramMap);

    public List<Ticket> findByStatus(Ticket.TicketStatus status);

    public List<Ticket> findByStock();

    public List<Map> findJsonByStock(String ticketNo);

    public List<Ticket> findInTicketByTradeId(Long tradeId);

    public List<Ticket> findOutTicketByTradeId(Long tradeId);

}
