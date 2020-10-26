package org.hepx.jgt.scheduler.db.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hepx.jgt.scheduler.db.service.JobInfo;
import org.hepx.jgt.scheduler.db.service.JobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Cron
 *
 * @author hepx
 * @date 2020/10/23 15:42
 */
@Service
@Slf4j
public class CronJobServiceImpl extends JobService {

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建JOB
     * @param jobInfo
     * @throws SchedulerException
     */
    @Override
    public void add(JobInfo jobInfo) throws SchedulerException {
        log.info("add:{}",jobInfo);
        //检查JOB是否已经存在
        if(scheduler.checkExists(new JobKey(jobInfo.getName(),jobInfo.getGroupName()))){
            throw new ObjectAlreadyExistsException("Job已经存在");
        }
        JobDetail jobDetail = JobBuilder.newJob(jobInfo.getJobClass())
                .withIdentity(jobInfo.getName(), jobInfo.getGroupName())
                .withDescription(jobInfo.getDescription())
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobInfo.getName(), jobInfo.getGroupName())
                .withDescription(jobInfo.getDescription())
                .withSchedule(CronScheduleBuilder.cronSchedule(jobInfo.getTime()).withMisfireHandlingInstructionFireAndProceed())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 更新JOB
     * @param jobInfo
     * @throws SchedulerException
     */
    @Override
    public void update(JobInfo jobInfo) throws SchedulerException {
        log.info("update:{}",jobInfo);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobInfo.getName(), jobInfo.getGroupName());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(jobInfo.getTime()).withMisfireHandlingInstructionFireAndProceed())
                .build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }
}
