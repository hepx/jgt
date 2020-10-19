/**
 * Copyright (c) 2005-2017. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package org.hepx.jgt.locks.impl;

import org.hepx.jgt.locks.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @desc: redis方式实现分布式锁
 * @author：hepx
 * @date: 2020/10/19 17:31
 */
@Service
public class RedisLockServiceImpl implements LockService {

    @Autowired
    JedisCluster jedisCluster;

    /**
     * EX,过期时间以秒为单位
     */
    private static String EXPIRE_UNIT = "EX";
    /**
     * 只在键不存在时，才对键进行设置操作
     */
    private static String SET_IF_NOT_EXIST = "NX";
    /**
     * 锁过期时间，必须要大于业务的处理时间，大一点没事，解锁成功DEL掉。只有当出现异常产生死锁，redis过期清除生效。
     */
    private static long EXPIRE_TIME = 90;
    /**
     * 加锁成功返回标志
     */
    private static String FLAG = "OK";
    /**
     * 解锁成功返回标志
     */
    private static Long UN_FLAG = 1L;


    /**
     * 加锁
     *
     * @param key
     * @param value
     * @return true 表示加锁成功，false 表示失败
     */
    @Override
    public boolean lock(String key, String value) {
        String result = jedisCluster.set(key, value, SET_IF_NOT_EXIST, EXPIRE_UNIT, EXPIRE_TIME);
        if (FLAG.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     * @return true 表示解锁成功，false 表示失败
     */
    @Override
    public boolean unlock(String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedisCluster.eval(script,1,key,value);
        if(UN_FLAG.equals(result)){
            return true;
        }
        return false;
    }




}
