package org.hepx.jgt.cache.memcache;

import net.spy.memcached.MemcachedClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * User: hepanxi
 * Date: 14-11-21
 * Time: 下午4:48
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-memcached.xml")
public class SpyMemcachedClientTest {

    @Autowired
    private SpyMemcachedClient client;

    @Test
    public void normal() throws Exception {

        String key = "consumer:1";
        String value = "admin";

        String key2 = "consumer:2";
        String value2 = "user";

        // get/set
        client.set(key, 60 * 60 * 1, value);
        Thread.sleep(1000);
        String result = client.get(key);
        Assert.assertEquals(result, value);


        // safeSet
        client.safeSet(key2, 60 * 60 * 1, value2);
        result = client.get(key2);
        Assert.assertEquals(result, value2);

        // delete
        client.delete(key);
        Thread.sleep(1000);
        result = client.get(key);
        Assert.assertNull(result);

        client.safeDelete(key);
        result = client.get(key);
        Assert.assertNull(result);
    }

    @Test
    public void incr() {
        String key = "counter";

        Assert.assertEquals(client.incr(key, 1, 1), 1);
        // 注意counter的实际类型是String
        Assert.assertEquals(client.get(key), "1");

        Assert.assertEquals(client.incr(key, 1, 1), 2);
        Assert.assertEquals(client.get(key), "2");

        Assert.assertEquals(client.decr(key, 2, 1), 0);
        Assert.assertEquals(client.get(key), "0");
    }

}
