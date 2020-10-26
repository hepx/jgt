package org.hepx.jgt.scheduler.mem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/23 10:12
 */
@Service
@Slf4j
public class SchedulService {

    /**
     * 执行时间=上一次执行后的时间+fixedDelay
     */
    @Scheduled(fixedDelay = 3000)
    public void task_fixedDelay() throws InterruptedException {
        log.info(LocalTime.now()+":execute task_fixedDelay");
        Thread.sleep(3000);
    }

    /**
     * 执行时间=按fixedRate周期执行
     */
    @Scheduled(fixedRate = 5000)
    public void task_fixedRate() throws InterruptedException {
        log.info(LocalTime.now()+":execute task_fixedRate");
        Thread.sleep(6000);
    }

    /**
     * 执行时间=按cron表达式周期执行
     * 在线cron生成：https://cron.qqe2.com/
     *
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void task_cron(){
        log.info(LocalTime.now()+":execute task_cron");
    }
}
