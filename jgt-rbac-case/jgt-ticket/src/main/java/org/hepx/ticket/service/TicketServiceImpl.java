package org.hepx.ticket.service;

import org.hepx.ticket.entity.Ticket;
import org.hepx.ticket.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-4-15
 * Time: 上午11:20
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public Ticket createTicket(Ticket ticket) {
        ticketMapper.createTicket(ticket);
        return ticket;
    }

    @Override
    public int updateTicket(Ticket ticket) {
        return ticketMapper.updateTicket(ticket);
    }

    @Override
    public int deleteTicket(Long ticketId) {
        return ticketMapper.deleteTicket(ticketId);
    }

    @Override
    public Ticket findOne(Long ticketId) {
        return ticketMapper.findOne(ticketId);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketMapper.findAll();
    }
}
