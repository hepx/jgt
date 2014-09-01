package org.hepx.jgt.showcase.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 字典
 * @author: Koala
 * @Date: 14-8-7 上午10:02
 * @Version: 1.0
 */
@Controller
@RequestMapping("dic")
public class DicController {

    private static Logger logger = LoggerFactory.getLogger(DicController.class);

    @RequestMapping("list")
    public String list(Model model){

        return "sys/dic";
    }
}
