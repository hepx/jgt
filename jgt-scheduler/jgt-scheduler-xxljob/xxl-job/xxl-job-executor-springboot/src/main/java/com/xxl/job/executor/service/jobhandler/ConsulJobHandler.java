package com.xxl.job.executor.service.jobhandler;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.health.model.HealthService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.GsonTool;
import com.xxl.job.core.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

/**
 * consul JOB
 *
 * @author nico
 * @date 2018/9/27
 */
@Component
public class ConsulJobHandler extends IJobHandler {

    @Autowired
    private ConsulClient consulClient;

    private Random random = new Random();

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        if (StringUtils.isEmpty(param)) {
            XxlJobLogger.log("参数为空，使用consul调用时参数必填。");
            return ReturnT.FAIL;
        }
        TaskParam taskParam = GsonTool.fromJson(param, TaskParam.class);
        if (StringUtils.isEmpty(taskParam.getServiceName()) || StringUtils.isEmpty(taskParam.getMethod())) {
            XxlJobLogger.log("使用consul调用时，consul服务名和调用方法必填。{0}",taskParam.toString());
            return ReturnT.FAIL;
        }
        String httpUrl = getUrlByServiceName(taskParam.getServiceName());
        if (StringUtils.isEmpty(httpUrl)) {
            XxlJobLogger.log("consul服务{0}不可用，请检查服务是否正常。", taskParam.getServiceName());
            return ReturnT.FAIL;
        }
        httpUrl = httpUrl + "/" + taskParam.getMethod();
        String responseStr = null;
        try {
        if ("GET".equals(taskParam.getRequest().toUpperCase())) {
            responseStr = HttpUtils.get(httpUrl);
        } else {
            responseStr = HttpUtils.postJson(httpUrl, null);
        }
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            XxlJobLogger.log(e);
            return ReturnT.FAIL;
        }
        XxlJobLogger.log("请求路径：{0}，请求方法：{1}",httpUrl,taskParam.getRequest());
        XxlJobLogger.log("请求响应：{0}", responseStr);
        return ReturnT.SUCCESS;
    }

    /**
     * 根据cosul服务名封装URL
     *
     * @param serviceName
     * @return
     */
    private String getUrlByServiceName(String serviceName) {
        List<HealthService> healthServiceList = consulClient.getHealthServices(serviceName, true, QueryParams.DEFAULT).getValue();
        if (!healthServiceList.isEmpty()) {
            HealthService hs = healthServiceList.get(random.nextInt(healthServiceList.size()));
            StringBuffer sb = new StringBuffer("http://");
            sb.append(hs.getService().getAddress());
            sb.append(":");
            sb.append(hs.getService().getPort());
            return sb.toString();
        }
        return null;
    }
}
