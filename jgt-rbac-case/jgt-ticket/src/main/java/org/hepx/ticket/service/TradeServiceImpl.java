package org.hepx.ticket.service;

import org.hepx.jgt.common.date.DateUtil;
import org.hepx.jgt.common.random.NumberGenerater;
import org.hepx.ticket.entity.*;
import org.hepx.ticket.mapper.*;
import org.hepx.ticket.web.TicketVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private BankAccountMapper bankAccountMapper;

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
    public List<Trade> findByCondition(String ticketNo, String startTime, String endTime) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ticketNo", ticketNo);
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        return tradeMapper.findByCondition(param);
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
    public void createTrade(TicketVo vo) {
        Trade trade = vo.getTrade();
        List<Ticket> inTickets = vo.getInTickets();
        verifInTicket(inTickets);
        List<Ticket> outTickets = vo.getOutTickets();
        verifOutTicket(outTickets);
        List<Payment> payments = vo.getPayments();
        trade.setInTicketMoney(sumInTicketMoney(inTickets));
        trade.setOutTicketMoney(sumOutTicketMoney(outTickets));
        trade.setTradeTotal(BigDecimal.valueOf(trade.getOutTicketMoney())
                .subtract(BigDecimal.valueOf(trade.getInTicketMoney())).doubleValue());
        trade.setCreateTime(new Date());
        Trade t = createTrade(trade);
        for (Ticket in_ticket : inTickets) {
            in_ticket.setInTradeId(t.getId());
            in_ticket.setInDate(new Date());
            in_ticket.setTicketStatus(Ticket.TicketStatus.EXISTED);
            ticketMapper.createTicket(in_ticket);
        }
        for (Ticket out_ticket : outTickets) {
            out_ticket.setOutDate(new Date());
            out_ticket.setTicketStatus(Ticket.TicketStatus.SALED);
            out_ticket.setOutTradeId(t.getId());
            ticketMapper.updateTicket(out_ticket);
        }
        for (Payment payment : payments) {
            payment.setTradeId(t.getId());
            if("0".equals(payment.getPayType())){//0表示现金
                payment.setPayType("现金");
            }else if(!"".equals(payment.getPayType())) {
                BankAccount bankAccount = bankAccountMapper.findOne(Long.parseLong(payment.getPayType()));
                payment.setPayType(bankAccount.getAlias());
                payment.setAccount(bankAccount.getAccount());
                payment.setOwner(bankAccount.getOwner());
                payment.setBankName(bankAccount.getBankName());
            }
            paymentMapper.createPayment(payment);
        }
        //自动创建新客户或更新旧的客户信息
        Customer customer_vo = new Customer(trade);
        Customer customer_ey = customerMapper.criteriaQuery(customer_vo);
        if (customer_ey != null) {
            customer_ey.setIdCard(customer_vo.getIdCard());
            customerMapper.updateCustomer(customer_ey);
        } else {
            customerMapper.createCustomer(customer_vo);
        }
    }


    /**
     * 验证进票金额计算，前端有计算，确保数据的准确性，这里再验证一下,
     * 最终以这里计算为准
     * 计算公式=（票面金额-票面零头）*（1-点数）-证明费-其他
     *
     * @param inTicets
     * @return
     */
    private void verifInTicket(List<Ticket> inTicets) {
        for (Ticket t : inTicets) {
            t.setInTicketSurplus((BigDecimal.valueOf(t.getTicketMoney()).subtract(BigDecimal.valueOf(t.getTicketOdd())))
                    .multiply(new BigDecimal(1).subtract(BigDecimal.valueOf(t.getInPoint() / 100)))
                    .subtract(BigDecimal.valueOf(t.getCertifyFee())).subtract(BigDecimal.valueOf(t.getOtherFee())).doubleValue());
        }
    }

    /**
     * 验证出票金额计算，前端有计算，确保数据的准确性，这里再验证一下,
     * 最终以这里计算为准
     * 出票公式=票面金额*（1-出票点数）
     *
     * @param outTicets
     */
    private void verifOutTicket(List<Ticket> outTicets) {
        for (Ticket t : outTicets) {
            t.setOutTicketSurplus(BigDecimal.valueOf(t.getTicketMoney()).multiply(new BigDecimal(1).
                    subtract(BigDecimal.valueOf(t.getOutPoint() / 100))).doubleValue());
        }
    }

    //汇总进票金额
    private double sumInTicketMoney(List<Ticket> inTicets) {
        BigDecimal totalInTicketMoney = new BigDecimal(0);
        for (Ticket t : inTicets) {
            totalInTicketMoney = totalInTicketMoney.add(BigDecimal.valueOf(t.getInTicketSurplus()));
        }
        return totalInTicketMoney.doubleValue();
    }

    //汇总出票金额
    public double sumOutTicketMoney(List<Ticket> outTicets) {
        BigDecimal totalOutTicketMoney = new BigDecimal(0);
        for (Ticket t : outTicets) {
            totalOutTicketMoney = totalOutTicketMoney.add(BigDecimal.valueOf(t.getOutTicketSurplus()));
        }
        return totalOutTicketMoney.doubleValue();
    }

    @Override
    public long statTradeByAll() {
        return tradeMapper.statTradeByAll();
    }

    @Override
    public long statTradeByDay(Date date) {
        return tradeMapper.statTradeByDay(DateUtil.formateDate(date));
    }
}
