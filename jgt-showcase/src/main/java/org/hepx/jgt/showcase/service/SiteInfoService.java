package org.hepx.jgt.showcase.service;

import org.apache.commons.lang.StringUtils;
import org.hepx.jgt.common.datatable.RequestParams;
import org.hepx.jgt.showcase.domain.DataTablePage;
import org.hepx.jgt.showcase.domain.SiteInfo;
import org.hepx.jgt.showcase.repository.SiteInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Koala
 * @Date: 14-8-25 下午2:10
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class SiteInfoService {

    @Autowired
    private SiteInfoRepository siteInfoRepository;

    public List<SiteInfo> findAll(){
        return this.siteInfoRepository.findAll();
    }

    public DataTablePage<SiteInfo> findAll(RequestParams requestParams){
        Page<SiteInfo> page=null;
        if(StringUtils.isNotEmpty(requestParams.getSearch().getValue())){
            page =  this.siteInfoRepository.findAll("%"+ requestParams.getSearch().getValue()+"%", requestParams.buildPageable());
        }else{
            page = this.siteInfoRepository.findAll(requestParams.buildPageable());
        }
        if(page!=null){
            return new DataTablePage<SiteInfo>(page);
        }else{
            return null;
        }
    }

    public SiteInfo findOne(Long id){
        return this.siteInfoRepository.findOne(id);
    }

    @Transactional
    public SiteInfo saveOrUpdate(SiteInfo siteInfo){
        return this.siteInfoRepository.save(siteInfo);
    }

    @Transactional
    public void delete(Long id){
        this.siteInfoRepository.delete(id);
    }
}
