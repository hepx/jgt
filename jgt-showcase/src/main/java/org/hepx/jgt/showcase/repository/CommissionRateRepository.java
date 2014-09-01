package org.hepx.jgt.showcase.repository;

import org.hepx.jgt.showcase.domain.CommissionRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Koala
 * @Date: 14-7-29 上午11:35
 * @Version: 1.0
 */
public interface CommissionRateRepository extends CrudRepository<CommissionRate,Long> {

    public List<CommissionRate> findAll();

    @Query("select c from CommissionRate c where c.category=:category")
    public CommissionRate findByCategory(@Param("category")String category);
}
