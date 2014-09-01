package org.hepx.jgt.showcase.repository;

import org.hepx.jgt.showcase.domain.CommodityExtend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Koala
 * @Date: 14-7-29 下午5:00
 * @Version: 1.0
 */
public interface CommodityExtendRepository extends PagingAndSortingRepository<CommodityExtend,Long>{

    public List<CommodityExtend> findAll();

    @Query("select c from CommodityExtend c where c.name like :keyword")
    public Page<CommodityExtend> search(@Param("keyword")String keyword,Pageable pageable);

    public Page<CommodityExtend> findByNameLike(String keyword,Pageable pageable);

}
