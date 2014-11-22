package org.hepx.jgt.cache.memcache;

import net.spy.memcached.MemcachedClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 对SpyMemcached Client的二次封装,提供常用函数的同步与异步操作封装.
 * <p/>
 * 未提供封装的函数可直接调用getClient()取出Spy的原版MemcachedClient来使用.
 *
 * @author calvin
 */
public class SpyMemcachedClient implements DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(SpyMemcachedClient.class);

    private MemcachedClient memcachedClient;

    private long shutdownTimeout = 2500;//延迟时间

    private long updateTimeout = 2000; //操作超时

    private int defaultExpiredTime = 0;//默认不过期，注意：memcache默认是30天，并不是永不过期

    @Override
    public void destroy() throws Exception {
        if (memcachedClient != null) {
            memcachedClient.shutdown(shutdownTimeout, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 从缓存中取值
     * 转换结果类型并屏蔽异常, 仅返回Null
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        try {
            return (T) memcachedClient.get(key);
        } catch (RuntimeException e) {
            handleException(e, key);
            return null;
        }
    }

    /**
     * GetBulk方法, 转换结果类型并屏蔽异常.
     *
     * @param keys
     * @param <T>
     * @return
     */
    public <T> Map<String, T> getBulk(Collection<String> keys) {
        try {
            return (Map<String, T>) memcachedClient.getBulk(keys);
        } catch (RuntimeException e) {
            handleException(e, StringUtils.join(keys, ","));
            return null;
        }
    }

    /**
     * 异步Set方法, 不考虑执行结果.采用默认过期时间
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        memcachedClient.set(key, defaultExpiredTime, value);
    }

    /**
     * 异步Set方法, 不考虑执行结果.
     *
     * @param key
     * @param expiredTime
     * @param value
     */
    public void set(String key, int expiredTime, Object value) {
        memcachedClient.set(key, expiredTime, value);
    }

    /**
     * 安全的Set方法, 保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作。采用默认过期时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean safeSet(String key, Object value) {
        Future<Boolean> future = memcachedClient.set(key, defaultExpiredTime, value);
        try {
            return future.get(updateTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return false;
    }

    /**
     * 安全的Set方法, 保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作
     *
     * @param key
     * @param expiredTime
     * @param value
     * @return
     */
    public boolean safeSet(String key, int expiredTime, Object value) {
        Future<Boolean> future = memcachedClient.set(key, expiredTime, value);
        try {
            return future.get(updateTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return false;
    }

    /**
     * 异步 Delete方法, 不考虑执行结果.
     *
     * @param key
     */
    public void delete(String key) {
        memcachedClient.delete(key);
    }

    /**
     * 安全的Delete方法, 保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作.
     *
     * @param key
     * @return
     */
    public boolean safeDelete(String key) {
        Future<Boolean> future = memcachedClient.delete(key);
        try {
            return future.get(updateTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return false;
    }

    /**
     * 替换缓存中的内容，不考虑执行结果，采用默认过期时间
     *
     * @param key
     * @param value
     */
    public void replace(String key, Object value) {
        memcachedClient.replace(key, defaultExpiredTime, value);
    }

    /**
     * 替换缓存中的内容，不考虑执行结果
     *
     * @param key
     * @param expiredTime
     * @param value
     */
    public void replace(String key, int expiredTime, Object value) {
        memcachedClient.replace(key, expiredTime, value);
    }

    /**
     * 安全的替换方法，保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作.采用默认过期时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean safeReplace(String key, Object value) {
        Future<Boolean> future = memcachedClient.replace(key, defaultExpiredTime, value);
        try {
            return future.get(updateTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return false;
    }

    /**
     * 安全的替换方法，保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作.
     *
     * @param key
     * @param expiredTime
     * @param value
     * @return
     */
    public boolean safeReplace(String key, int expiredTime, Object value) {
        Future<Boolean> future = memcachedClient.replace(key, expiredTime, value);
        try {
            return future.get(updateTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            future.cancel(false);
        }
        return false;
    }

    /**
     * Incr方法.
     */
    public long incr(String key, int by, long defaultValue) {
        return memcachedClient.incr(key, by, defaultValue);
    }

    /**
     * Decr方法.
     */
    public long decr(String key, int by, long defaultValue) {
        return memcachedClient.decr(key, by, defaultValue);
    }

    /**
     * 异步Incr方法, 不支持默认值, 若key不存在返回-1.
     */
    public Future<Long> asyncIncr(String key, int by) {
        return memcachedClient.asyncIncr(key, by);
    }

    /**
     * 异步Decr方法, 不支持默认值, 若key不存在返回-1.
     */
    public Future<Long> asyncDecr(String key, int by) {
        return memcachedClient.asyncDecr(key, by);
    }

    private void handleException(Exception e, String key) {
        logger.warn("spymemcached client receive an exception with key:" + key, e);
    }

    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public void setUpdateTimeout(long updateTimeout) {
        this.updateTimeout = updateTimeout;
    }

    public void setShutdownTimeout(long shutdownTimeout) {
        this.shutdownTimeout = shutdownTimeout;
    }

    public int getDefaultExpiredTime() {
        return defaultExpiredTime;
    }

    public void setDefaultExpiredTime(int defaultExpiredTime) {
        this.defaultExpiredTime = defaultExpiredTime;
    }

    public long getUpdateTimeout() {
        return updateTimeout;
    }

    public long getShutdownTimeout() {
        return shutdownTimeout;
    }
}