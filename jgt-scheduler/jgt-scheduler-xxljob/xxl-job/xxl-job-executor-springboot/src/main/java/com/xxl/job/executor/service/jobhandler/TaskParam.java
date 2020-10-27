package com.xxl.job.executor.service.jobhandler;

import java.io.Serializable;

/**
 * 任务参数
 *
 * @author nico
 * @date 2018/9/27
 */
public class TaskParam implements Serializable {

    /**
     * consul 服务名
     */
    private String serviceName;

    /**
     * 具体的请求方法
     */
    private String method;

    /**
     * get or post
     */
    private String request="GET";

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "TaskParam{" +
                "serviceName='" + serviceName + '\'' +
                ", method='" + method + '\'' +
                ", request='" + request + '\'' +
                '}';
    }
}
