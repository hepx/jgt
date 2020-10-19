package org.hepx.jgt.locks;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/19 17:51
 */
class LockServiceTest extends JgtLocksApplicationTests {

    @Autowired
    private LockService lockService;

    private String key = "sis:pc:id:lock";
    private String val = "lock";

    @Test
    void lock() {
         Assert.assertTrue(lockService.lock(key,val));
    }

    @Test
    void unlock() {
        Assert.assertTrue(lockService.unlock(key,val));
    }

    @Test
    public void lock_test(String[] args) throws InterruptedException {
        String key = "distributed.lock";
        String value = UUID.randomUUID().toString();

        //1:先加锁
        if (lockService.lock(key, value)) {
            System.out.println("加锁成功," + value + "。");
        } else {
            System.out.println("加锁失败," + value + "。");
            return;
        }
        //2:模拟操作 这里让线程停5秒
        Thread.sleep(5000L);

        //3:执行完解锁
        if (lockService.unlock(key, value)) {
            System.out.println("解锁成功," + value + "。");
        } else {
            System.out.println("解锁失败," + value + "。");
        }
    }
}