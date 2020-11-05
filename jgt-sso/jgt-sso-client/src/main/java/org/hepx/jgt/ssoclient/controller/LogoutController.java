package org.hepx.jgt.ssoclient.controller;

import org.jasig.cas.client.boot.configuration.CasClientConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/11/5 15:09
 */
@Controller
public class LogoutController {

    @Autowired
    private CasClientConfigurationProperties configurationProperties;

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletResponse response) {
        try {
            response.sendRedirect(configurationProperties.getServerUrlPrefix() + "/logout?service=" + configurationProperties.getClientHostUrl()+"/user");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
