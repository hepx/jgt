package org.hepx.ticket.mapper;

import org.hepx.ticket.entity.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User: hepanxi
 * Date: 15-4-15
 * Time: 上午11:05
 */
@Repository
public interface TicketMapper {

    public int createTicket(Ticket ticket);

    public int updateTicket(Ticket ticket);

    public int deleteTicket(Long ticketId);

    public Ticket findOne(Long ticketId);

    public List<Ticket> findAll();

    public List<Ticket> findAllByCriteria(Ticket ticket);

    public List<Ticket> findByStatus(String status);

    public List<Ticket> findByStock();

    public List<Map> findJsonByStock(String ticketNo);

    public List<Ticket> findInTicketByTradeId(Long tradeId);

    public List<Ticket> findOutTicketByTradeId(Long tradeId);

}
