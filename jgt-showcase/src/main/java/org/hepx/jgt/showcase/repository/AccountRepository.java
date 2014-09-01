package org.hepx.jgt.showcase.repository;

import org.hepx.jgt.showcase.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Koala
 * @Date: 14-7-29 下午2:26
 * @Version: 1.0
 */
public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {

    public List<Account> findAll();

    public Page<Account> findAllByStatus(int status,Pageable pageable);

    @Query("select t from Account t where t.status=:status and t.realName like :keyword")
    public Page<Account> find(@Param("status")int status,@Param("keyword")String keyword,Pageable pageable);

    @Query("select t from Account t where t.realName like :keyword")
    public Page<Account> find(@Param("keyword")String keyword,Pageable pageable);
}
