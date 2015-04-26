package org.hepx.ticket.service;

import org.hepx.ticket.entity.Customer;

import java.util.List;

public interface CustomerService {

    public Customer createCustomer(Customer customer);

    public int updateCustomer(Customer customer);

    public int deleteCustomer(Long customerId);

    public Customer findOne(Long customerId);

    public List<Customer> findAll();

    public List<Customer> findJson(String name);

    public Customer criteriaQuery(Customer customer);

}
