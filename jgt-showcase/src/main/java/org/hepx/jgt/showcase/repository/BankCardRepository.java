package org.hepx.jgt.showcase.repository;

import org.hepx.jgt.showcase.domain.BankCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: Koala
 * @Date: 14-8-6 下午5:55
 * @Version: 1.0
 */
public interface BankCardRepository extends CrudRepository<BankCard,Long> {

    public List<BankCard> findByUserId(Long userId);

}
