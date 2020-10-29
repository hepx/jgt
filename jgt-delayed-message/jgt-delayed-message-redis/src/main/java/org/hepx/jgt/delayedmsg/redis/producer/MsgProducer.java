package org.hepx.jgt.delayedmsg.redis.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/29 10:14
 */
@Component
public class MsgProducer {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     *
     * @param key
     * @param msg
     * @param expiration
     */
    public void sendMsg(String key,String msg,long expiration){
        jedisCluster.zadd(key,expiration,msg);
    }

}
