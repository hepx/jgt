package org.hepx.jgt.showcase.service;

import org.hepx.jgt.showcase.domain.BankCard;
import org.hepx.jgt.showcase.repository.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Koala
 * @Date: 14-8-6 下午6:11
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class BankCardService {

    @Autowired
    private BankCardRepository bankCardRepository;

    public List<BankCard> list(Long userId){
        return this.bankCardRepository.findByUserId(userId);
    }
}
