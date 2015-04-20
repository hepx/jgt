package org.hepx.ticket.mapper;

import org.hepx.ticket.entity.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Ticket> findByStatus(String status);
}
