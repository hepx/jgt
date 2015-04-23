package org.hepx.ticket.service;

import org.hepx.ticket.entity.Ticket;

import java.util.List;

public interface TicketService {

    public Ticket createTicket(Ticket ticket);

    public int updateTicket(Ticket ticket);

    public int deleteTicket(Long ticketId);

    public Ticket findOne(Long ticketId);

    public List<Ticket> findAll();

    public List<Ticket> findByStatus(Ticket.TicketStatus status);

    public List<Ticket> findByStock();

}
