package org.hepx.jgt.scheduler.db.service;

import lombok.extern.slf4j.Slf4j;
import org.hepx.jgt.scheduler.db.JgtSchedulerDbApplicationTests;
import org.hepx.jgt.scheduler.db.job.SimpleJob;
import org.hepx.jgt.scheduler.db.service.impl.CronJobServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/23 16:59
 */
@Slf4j
class CronJobServiceTest extends JgtSchedulerDbApplicationTests {

    @Autowired
    private CronJobServiceImpl jobService;

    private JobInfo jobInfo;

    @BeforeEach
    public void init() {
        jobInfo = new JobInfo();
        jobInfo.setJobClass(SimpleJob.class);
        jobInfo.setName("JOB-2");
        jobInfo.setDescription("test job 2");
    }

    @AfterEach
    public void after() throws InterruptedException {
        Thread.sleep(300L * 1000l);
    }

    @Test
    void add() throws SchedulerException {
        jobInfo.setTime("0/5 * * * * ? ");
        jobService.add(jobInfo);
    }

    @Test
    void update() throws SchedulerException, InterruptedException {
        Thread.sleep(20L * 1000l);
        jobInfo.setTime("0/10 * * * * ? ");
        jobService.update(jobInfo);
    }

    @Test
    void delete() throws InterruptedException, SchedulerException {
        //Thread.sleep(10L * 1000l);
        jobService.delete(jobInfo);
    }

    @Test
    void pauseAndResume() throws InterruptedException, SchedulerException {
        Thread.sleep(20L * 1000l);
        jobService.pause(jobInfo);
        Thread.sleep(20L * 1000l);
        jobService.resume(jobInfo);
    }

    @Test
    void queryAllJob() throws SchedulerException {
        List<JobInfo> jobInfoList = jobService.queryAllJob();
        jobInfoList.forEach(jobInfo ->{
            log.info("JOB:{}",jobInfo.toString());
        });
    }

}