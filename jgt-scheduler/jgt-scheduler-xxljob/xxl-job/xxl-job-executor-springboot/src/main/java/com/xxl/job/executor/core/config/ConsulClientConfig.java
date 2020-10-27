package com.xxl.job.executor.core.config;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * consul config
 *
 * @author hepx
 * @date 2020/10/27 17:34
 */
@Configuration
public class ConsulClientConfig {

    @Value("${consul.host}")
    private String host;

    @Value("${consul.port}")
    private String port;

    @Bean
    public ConsulClient buidConsulClient() {
        if (!StringUtils.isEmpty(host) && !StringUtils.isEmpty(port)) {
            return new ConsulClient(this.host, Integer.parseInt(this.port));
        }
        return null;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
