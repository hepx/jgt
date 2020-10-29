package org.hepx.jgt.delayedmsg.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/19 17:34
 */
@Configuration
public class RedisConfig {
    /**
     * 集群方式的redis客户端，注：单机，集群方式处理一样。
     */
    @Value(value = "${jgt.redis.host}")
    private String host;

    @Value(value = "${jgt.redis.port}")
    private Integer port;

    @Bean
    public JedisCluster getJedis() {
        return new JedisCluster(new HostAndPort(host, port));
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
