package org.hepx.jgt.showcase.service;

import org.hepx.jgt.showcase.domain.CommissionRate;
import org.hepx.jgt.showcase.repository.CommissionRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Koala
 * @Date: 14-7-29 上午11:37
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class CommissionRateService {

    @Autowired
    private CommissionRateRepository commissionRateRepository;

    public List<CommissionRate> list(){
        return this.commissionRateRepository.findAll();
    }

    public CommissionRate find(Long id){
        return this.commissionRateRepository.findOne(id);
    }

    @Transactional
    public void saveOrUpdate(CommissionRate commissionRate){
        this.commissionRateRepository.save(commissionRate);
    }

    @Transactional
    public void delete(Long id){
        this.commissionRateRepository.delete(id);
    }

    public BigDecimal findByCategory(String category){
        CommissionRate commissionRate=this.commissionRateRepository.findByCategory(category);
        if(commissionRate!=null){
            return commissionRate.getRate();
        }else{
            return null;
        }
    }
}
