package org.hepx.ticket.service;

import org.hepx.ticket.entity.Payment;
import org.hepx.ticket.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-4-21
 * Time: 下午2:13
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Payment createPayment(Payment payment) {
        paymentMapper.createPayment(payment);
        return payment;
    }

    @Override
    public int updatePayment(Payment payment) {
        return paymentMapper.updatePayment(payment);
    }

    @Override
    public int deletePayment(Long paymentId) {
        return paymentMapper.deletePayment(paymentId);
    }

    @Override
    public Payment findOne(Long paymentId) {
        return paymentMapper.findOne(paymentId);
    }

    @Override
    public List<Payment> findAll() {
        return paymentMapper.findAll();
    }
}
