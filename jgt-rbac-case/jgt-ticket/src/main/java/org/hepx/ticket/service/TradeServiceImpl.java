package org.hepx.ticket.service;

import org.hepx.jgt.common.random.NumberGenerater;
import org.hepx.ticket.entity.Ticket;
import org.hepx.ticket.entity.Trade;
import org.hepx.ticket.mapper.TicketMapper;
import org.hepx.ticket.mapper.TradeMapper;
import org.hepx.ticket.web.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * User: hepanxi
 * Date: 15-4-18
 * Time: 上午10:29
 */
@Service
@Transactional
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public Trade createTrade(Trade trade) {
        tradeMapper.createTrade(trade);
        return trade;
    }

    @Override
    public int updateTrade(Trade trade) {
        return tradeMapper.updateTrade(trade);
    }

    @Override
    public int deleteTrade(Long tradeId) {
        return tradeMapper.deleteTrade(tradeId);
    }

    @Override
    public Trade findOne(Long tradeId) {
        return tradeMapper.findOne(tradeId);
    }

    @Override
    public List<Trade> findAll() {
        return tradeMapper.findAll();
    }

    @Override
    public String getTradeNo() {
        Trade trade = tradeMapper.findLast();
        if (trade == null) {
            return "00001";//默认开始编号
        } else {
            return NumberGenerater.generate(Long.parseLong(trade.getTradeNo()) + 1);
        }
    }

    @Override
    public void crateTrade(TicketVo vo) {
        Trade trade = vo.getTrade();
        List<Ticket> inTickets = vo.getInTickets();
        verifInTicket(inTickets);
        List<Ticket> outTickets = vo.getOutTickets();
        verifOutTicket(outTickets);
        trade.setInTicketMoney(sumInTicketMoney(inTickets));
        trade.setOutTicketMoney(sumOutTicketMoney(outTickets));
        Trade t = createTrade(trade);
        for (Ticket in_ticket : inTickets) {
            in_ticket.setTradeId(t.getId());
            in_ticket.setTicketStatus(Ticket.TicketStatus.EXISTED);
            ticketMapper.createTicket(in_ticket);
        }
        for (Ticket out_ticket : outTickets) {
            out_ticket.setOutDate(new Date());
            out_ticket.setTicketStatus(Ticket.TicketStatus.SALED);
            ticketMapper.updateTicket(out_ticket);
        }
    }

    /**
     * 验证进票金额计算，前端有计算，确保数据的准确性，这里再验证一下,
     * 最终以这里计算为准
     * 计算公式=（票面金额-票面零头）*（1-点数）-证明费-其他
     * @param inTicets
     * @return
     */
    private void verifInTicket(List<Ticket> inTicets){
        for(Ticket t : inTicets){
            t.setInTicketSurplus((BigDecimal.valueOf(t.getTicketMoney()).subtract(BigDecimal.valueOf(t.getTicketOdd())))
                    .multiply(new BigDecimal(1).subtract(BigDecimal.valueOf(t.getInPoint())))
                    .subtract(BigDecimal.valueOf(t.getCertifyFee())).subtract(BigDecimal.valueOf(t.getOtherFee())).doubleValue());
        }
    }

    /**
     * 验证出票金额计算，前端有计算，确保数据的准确性，这里再验证一下,
     * 最终以这里计算为准
     * 出票公式=票面金额*（1-出票点数）
     * @param outTicets
     */
    private void verifOutTicket(List<Ticket> outTicets){
        for(Ticket t : outTicets){
            t.setOutTicketSurplus(BigDecimal.valueOf(t.getTicketMoney()).multiply(new BigDecimal(1).
                    subtract(BigDecimal.valueOf(t.getOutPoint()))).doubleValue());
        }
    }

    //汇总进票金额
    private double sumInTicketMoney(List<Ticket> inTicets) {
        BigDecimal totalInTicketMoney = new BigDecimal(0);
        for (Ticket t : inTicets) {
            totalInTicketMoney.add(BigDecimal.valueOf(t.getInTicketSurplus()));
        }
        return totalInTicketMoney.doubleValue();
    }

    //汇总出票金额
    public double sumOutTicketMoney(List<Ticket> outTicets) {
        BigDecimal totalOutTicketMoney = new BigDecimal(0);
        for(Ticket t : outTicets){
            totalOutTicketMoney.add(BigDecimal.valueOf(t.getOutTicketSurplus()));
        }
        return totalOutTicketMoney.doubleValue();
    }
}
