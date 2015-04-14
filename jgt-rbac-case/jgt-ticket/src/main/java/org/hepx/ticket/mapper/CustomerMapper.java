package org.hepx.ticket.mapper;

import org.hepx.ticket.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-3-28
 * Time: 上午9:39
 */
@Repository
public interface CustomerMapper {
    
    public int createCustomer(Customer customer);

    public int updateCustomer(Customer customer);

    public int deleteCustomer(Long customerId);

    public Customer findOne(Long customerId);

    public List<Customer> findAll();

}
