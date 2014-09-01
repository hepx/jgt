package org.hepx.jgt.showcase.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品---对接商品中心的接口来获取商品数据
 * @author: Koala
 * @Date: 14-8-8 上午10:01
 * @Version: 1.0
 */
@Controller
@RequestMapping("/product")
public class ProductCenterController {

    @RequestMapping("/list")
    public String list(Model model){


        return "product/list";
    }

}
