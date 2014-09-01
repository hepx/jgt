package org.hepx.jgt.showcase.service;

import org.apache.commons.lang.StringUtils;
import org.hepx.jgt.common.datatable.RequestParams;
import org.hepx.jgt.showcase.domain.Account;
import org.hepx.jgt.showcase.domain.DataTablePage;
import org.hepx.jgt.showcase.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Koala
 * @Date: 14-7-29 下午2:30
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public DataTablePage<Account> list(Integer status,RequestParams requestParams){
        Page<Account> page=null;
        if(status!=null){
            if(StringUtils.isNotEmpty(requestParams.getSearch().getValue())){
                page=this.accountRepository.find(status,"%"+ requestParams.getSearch().getValue()+"%", requestParams.buildPageable());
            }else{
                page=this.accountRepository.findAllByStatus(status, requestParams.buildPageable());
            }
        }else{
            if(StringUtils.isNotEmpty(requestParams.getSearch().getValue())){
                page=this.accountRepository.find("%"+ requestParams.getSearch().getValue()+"%", requestParams.buildPageable());
            }else{
                page=this.accountRepository.findAll(requestParams.buildPageable());
            }
        }
        if(page!=null){
            return new DataTablePage<Account>(page);
        }else{
            return null;
        }
    }

/*    public Page<Account> list(Pageable pageable){
        return this.accountRepository.findAll(pageable);
    }*/

    public Account findOne(Long id){
        return this.accountRepository.findOne(id);
    }

    @Transactional
    public void update(Account account){
         if(!account.isNew()){
             this.accountRepository.save(account);
         }
    }
}
