package org.hepx.ticket.service;

import org.hepx.ticket.entity.Ticket;
import org.hepx.ticket.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Ticket> findAll(Ticket ticket) {
        return ticketMapper.findAllByCriteria(ticket);
    }

    @Override
    public List<Ticket> findByCondition(Map<String, Object> paramMap) {
        return ticketMapper.findByCriteria(paramMap);
    }

    @Override
    public List<Ticket> findByStatus(Ticket.TicketStatus status) {
        return ticketMapper.findByStatus(status.toString());
    }

    @Override
    public List<Ticket> findByStock() {
        return ticketMapper.findByStock();
    }

    @Override
    public List<Map> findJsonByStock(String ticketNo) {
        return ticketMapper.findJsonByStock(ticketNo);
    }

    @Override
    public List<Ticket> findInTicketByTradeId(Long tradeId) {
        return ticketMapper.findInTicketByTradeId(tradeId);
    }

    @Override
    public List<Ticket> findOutTicketByTradeId(Long tradeId) {
        return ticketMapper.findOutTicketByTradeId(tradeId);
    }
}
