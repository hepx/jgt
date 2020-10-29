package org.hepx.jgt.delayedmsg.redis;

import lombok.extern.slf4j.Slf4j;
import org.hepx.jgt.delayedmsg.redis.consumer.MsgConsumer;
import org.hepx.jgt.delayedmsg.redis.producer.MsgProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.UUID;

@SpringBootTest(classes = JgtDelayedMessageRedisApplication.class)
@Slf4j
class JgtDelayedMessageRedisApplicationTests {

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private MsgConsumer msgConsumer;

    private static final String key = "jgt:delayed:msg:pay";

    @Test
    public void testSendMsg() {
        while (true) {
            String r = UUID.randomUUID().toString();
            long ex = getEx();
            msgProducer.sendMsg(key, r, ex);
            log.info(LocalDateTime.ofInstant(Instant.ofEpochMilli(ex), ZoneId.systemDefault()) + "--->" + r);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void receiveMsg() {
        while (true) {
            String r = msgConsumer.receiveMsg(key);
            //演示，只打印结果，实际情况为业务代码
            if (r != null) {
                log.info(LocalDateTime.now() + "---->" + r);
            }
        }
    }

    private long getEx() {
        Random r = new Random();
        //随机从10-100之间一个值来表示延迟时间/秒
        long i = r.ints(10, 100).limit(1).findFirst().getAsInt();
        //单前时间戳+延时时间=score
        long s = Instant.now().plusSeconds(i).toEpochMilli();
        return s;
    }
}
