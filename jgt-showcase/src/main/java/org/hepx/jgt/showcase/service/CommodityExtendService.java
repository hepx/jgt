package org.hepx.jgt.showcase.service;

import org.apache.commons.lang.StringUtils;
import org.hepx.jgt.common.datatable.RequestParams;
import org.hepx.jgt.showcase.domain.CommodityExtend;
import org.hepx.jgt.showcase.domain.DataTablePage;
import org.hepx.jgt.showcase.domain.Product;
import org.hepx.jgt.showcase.repository.CommodityExtendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author: Koala
 * @Date: 14-7-29 下午5:05
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class CommodityExtendService {

    @Autowired
    public CommodityExtendRepository commodityExtendRepository;
    @Autowired
    public CommissionRateService commissionRateService;

    public List<CommodityExtend> list(){
        return this.commodityExtendRepository.findAll();
    }

    public DataTablePage<CommodityExtend> list(RequestParams requestParams){
        Page<CommodityExtend> page=null;
        if(StringUtils.isNotEmpty(requestParams.getSearch().getValue())){
            page=this.commodityExtendRepository.findByNameLike("%"+ requestParams.getSearch().getValue()+"%", requestParams.buildPageable());
        }else{
            page=this.commodityExtendRepository.findAll(requestParams.buildPageable());
        }
        if(page!=null){
            return new DataTablePage<CommodityExtend>(page);
        }else{
            return null;
        }
    }

    public CommodityExtend findOne(Long id){
        return this.commodityExtendRepository.findOne(id);
    }

    @Transactional
    public CommodityExtend saveOrUpdate(CommodityExtend commodityExtend){
        return this.commodityExtendRepository.save(commodityExtend);
    }

    @Transactional
    public void delete(Long id){
        this.commodityExtendRepository.delete(id);
    }

   public void add(Collection<Long >ids){
        for(Long id : ids){
            Product p=ProductData.getProductById(id);
            CommodityExtend c=new CommodityExtend();
            c.setName(p.getName());
            c.setUrl(p.getUrl());
            c.setPictureUrl(p.getImg());
            c.setDescription(p.getDescription());
            c.setPrice(p.getPrice());
            c.setCommodityId(p.getId());
            c.setCreateTime(new Date());
            //根据商品类型设置佣金比率
            c.setStatus(false);
            c.setRate(commissionRateService.findByCategory(p.getCategory()));
            this.commodityExtendRepository.save(c);
        }
   }
}
