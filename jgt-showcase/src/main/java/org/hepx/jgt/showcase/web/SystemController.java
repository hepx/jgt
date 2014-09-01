package org.hepx.jgt.showcase.web;

import org.hepx.jgt.common.sys.ServerUtil;
import org.hepx.jgt.common.sys.SystemInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统信息
 * @author: Koala
 * @Date: 14-8-7 上午9:34
 * @Version: 1.0
 */
@Controller
@RequestMapping("/sys")
public class SystemController {

    @RequestMapping("info")
    public String sysInfo(HttpServletRequest request,Model model){
        SystemInfo systemInfo= SystemInfo.getInstance(request);
        String serverId= ServerUtil.getServerId();
        model.addAttribute("systemInfo",systemInfo);
        model.addAttribute("serverId",serverId);
        return "sys/sysinfo";
    }

}
