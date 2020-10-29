package org.hepx.jgt.delayedmsg.redis.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/29 10:14
 */
@Component
public class MsgConsumer {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 通过 zrangebyscore key min max withscores limit 0 1 查询最早的一条任务，来进行消费
     *
     * @param key
     */
    public String receiveMsg(String key) {
        String s = null;
        Set<String> r = jedisCluster.zrangeByScore(key, 0, System.currentTimeMillis(), 0, 1);
        if (!r.isEmpty()) {
            s = r.iterator().next();
            jedisCluster.zrem(key, s);
        }
        return s;
    }

}
