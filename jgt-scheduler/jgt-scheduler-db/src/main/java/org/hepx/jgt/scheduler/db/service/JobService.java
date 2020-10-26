package org.hepx.jgt.scheduler.db.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/23 14:59
 */
@Service
@Slf4j
public abstract class JobService {

    @Autowired
    private Scheduler scheduler;


    /**
     * 不同类型的JOB创建不一样，定义为abstract,让具体子类来实现
     *
     * @param jobInfo
     */
    public abstract void add(JobInfo jobInfo) throws SchedulerException;

    /**
     * 不同类型的JOB更新不一样，定义为abstract,让具体子类来实现
     *
     * @param jobInfo
     */
    public abstract void update(JobInfo jobInfo) throws SchedulerException;

    /**
     * 删除
     *
     * @param jobInfo
     * @return
     * @throws SchedulerException
     */
    public boolean delete(JobInfo jobInfo) throws SchedulerException {
        log.info("delete:{}",jobInfo);
        return scheduler.deleteJob(getJobKey(jobInfo));
    }

    /**
     * 暂停
     *
     * @param jobInfo
     * @throws SchedulerException
     */
    public void pause(JobInfo jobInfo) throws SchedulerException {
        log.info("pause:{}",jobInfo);
        scheduler.pauseJob(getJobKey(jobInfo));
    }

    /**
     * 愎复
     *
     * @param jobInfo
     * @throws SchedulerException
     */
    public void resume(JobInfo jobInfo) throws SchedulerException {
        log.info("resume:{}",jobInfo);
        scheduler.resumeJob(getJobKey(jobInfo));
    }

    /**
     * 立即执行
     *
     * @param jobInfo
     * @throws SchedulerException
     */
    public void run(JobInfo jobInfo) throws SchedulerException {
        log.info("run:{}",jobInfo);
        scheduler.triggerJob(getJobKey(jobInfo));
    }

    /**
     * 查询所有JOB
     *
     * @return
     */
    public List<JobInfo> queryAllJob() throws SchedulerException {
        List<JobInfo> jobInfos = new ArrayList<>();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                JobInfo jobInfo = new JobInfo();
                jobInfo.setName(jobKey.getName());
                jobInfo.setGroupName(jobKey.getGroup());
                jobInfo.setDescription(trigger.getDescription());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                jobInfo.setStatus(triggerState.name());
                String time = "";
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    time = cronTrigger.getCronExpression();
                } else if (trigger instanceof SimpleTrigger) {
                    SimpleTrigger simpleTrigger = (SimpleTrigger) trigger;
                    time = String.valueOf(simpleTrigger.getRepeatInterval());
                }
                jobInfo.setTime(time);
                jobInfos.add(jobInfo);
            }
        }
        return jobInfos;
    }


    private JobKey getJobKey(JobInfo jobInfo) {
        return new JobKey(jobInfo.getName(), jobInfo.getGroupName());
    }

}
