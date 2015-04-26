package org.hepx.ticket.service;

import org.hepx.ticket.entity.Customer;
import org.hepx.ticket.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-4-15
 * Time: 上午9:46
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer createCustomer(Customer customer) {
        customerMapper.createCustomer(customer);
        return customer;
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    @Override
    public int deleteCustomer(Long customerId) {
        return customerMapper.deleteCustomer(customerId);
    }

    @Override
    public Customer findOne(Long customerId) {
        return customerMapper.findOne(customerId);
    }

    @Override
    public List<Customer> findAll() {
        return customerMapper.findAll();
    }

    @Override
    public List<Customer> findJson(String name) {
        return customerMapper.findJson(name);
    }

    @Override
    public Customer criteriaQuery(Customer customer) {
        return customerMapper.criteriaQuery(customer);
    }
}
