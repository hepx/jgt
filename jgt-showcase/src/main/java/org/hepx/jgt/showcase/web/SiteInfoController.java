package org.hepx.jgt.showcase.web;

import org.hepx.jgt.common.datatable.RequestParams;
import org.hepx.jgt.common.json.JsonResult;
import org.hepx.jgt.showcase.domain.DataTablePage;
import org.hepx.jgt.showcase.domain.SiteInfo;
import org.hepx.jgt.showcase.service.SiteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 站点管理
 * @author: Koala
 * @Date: 14-8-25 下午2:24
 * @Version: 1.0
 */
@Controller
@RequestMapping("/site")
public class SiteInfoController {

    private static Logger logger = LoggerFactory.getLogger(SiteInfoController.class);

    @Autowired
    private SiteInfoService siteInfoService;

    @RequestMapping(value="list",method= RequestMethod.GET)
    public String initList(){
        return "site/list";
    }

    /**
     * 分页查询站点信息
     * @return
     */
    @RequestMapping(value="list",method=RequestMethod.POST)
    @ResponseBody
    public DataTablePage<SiteInfo> list(RequestParams requestParams){
        return  this.siteInfoService.findAll(requestParams);
    }

    /**
     * 更新站点状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("update/{id}/{status}")
    @ResponseBody
    public JsonResult update(@PathVariable("id")Long id,@PathVariable("status")Integer status){
        try {
            SiteInfo siteInfo=this.siteInfoService.findOne(id);
            if(siteInfo!=null){
                siteInfo.setStatus(status);
                siteInfo.setUpdateTime(new Date());
                this.siteInfoService.saveOrUpdate(siteInfo);
                return new JsonResult(true);
            }else{
               return new JsonResult("站点信息不存在");
            }
        } catch (Exception e) {
            logger.error("更新站点异常",e);
            return new JsonResult("更新站点异常");
        }
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public JsonResult delete(@PathVariable("id")Long id){
        try {
            this.siteInfoService.delete(id);
            return new JsonResult(true);
        } catch (Exception e) {
            logger.error("删除站点异常",e);
            return new JsonResult("删除站点异常");
        }
    }
}
