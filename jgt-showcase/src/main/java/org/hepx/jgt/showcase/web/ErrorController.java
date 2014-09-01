package org.hepx.jgt.showcase.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Koala
 * @Date: 14-6-30 下午5:03
 * @Version: 1.0
 */
@Controller
public class ErrorController {

    @RequestMapping("/404")
    public String to404(){
        return "error/404";
    }
    @RequestMapping("/500")
    public String to500(){
        return "error/500";
    }

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }

}
