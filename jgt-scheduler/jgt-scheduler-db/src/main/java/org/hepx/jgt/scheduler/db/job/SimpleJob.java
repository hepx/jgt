package org.hepx.jgt.scheduler.db.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.time.LocalDateTime;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/23 16:51
 */

@Slf4j
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey =  context.getJobDetail().getKey();
        log.info("SimpleJob says: " + jobKey + " executing at " + LocalDateTime.now());
    }
}
