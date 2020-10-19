package org.hepx.jgt.locks;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/19 17:31
 */
public interface LockService {

    /**
     * 加锁
     *
     * @param key
     * @param value
     * @return true 表示加锁成功，false 表示失败
     */
    boolean lock(String key, String value);

    /**
     * 解锁
     * @param key
     * @param value
     * @return true 表示解锁成功，false 表示失败
     */
    boolean unlock(String key, String value);
}
