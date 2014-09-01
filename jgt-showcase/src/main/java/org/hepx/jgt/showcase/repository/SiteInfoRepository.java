package org.hepx.jgt.showcase.repository;

import org.hepx.jgt.showcase.domain.SiteInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Koala
 * @Date: 14-8-25 下午2:05
 * @Version: 1.0
 */
public interface SiteInfoRepository extends PagingAndSortingRepository<SiteInfo,Long> {

    public List<SiteInfo> findAll();

    @Query("select t from SiteInfo t where t.webName like :keyword or t.webUrl like :keyword")
    public Page<SiteInfo> findAll(@Param("keyword")String keyword,Pageable pageable);
}
