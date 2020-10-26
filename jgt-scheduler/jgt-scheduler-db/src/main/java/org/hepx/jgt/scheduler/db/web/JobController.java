package org.hepx.jgt.scheduler.db.web;

import org.hepx.jgt.scheduler.db.service.JobInfo;
import org.hepx.jgt.scheduler.db.service.impl.CronJobServiceImpl;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 通过springMVC 提供API接口，前端页面可自由定制
 *
 * @author hepx
 * @date 2020/10/26 18:14
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CronJobServiceImpl jobService;

    public ResponseEntity add(@RequestBody JobInfo jobInfo) {
        try {
            jobService.add(jobInfo);
            return ResponseEntity.ok().build();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity delete(@RequestBody JobInfo jobInfo) {
        try {
            jobService.delete(jobInfo);
            return ResponseEntity.ok().build();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity update(@RequestBody JobInfo jobInfo) {
        try {
            jobService.update(jobInfo);
            return ResponseEntity.ok().build();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<JobInfo>> list() {
        try {
            List<JobInfo> jobInfos = jobService.queryAllJob();
            return ResponseEntity.ok(jobInfos);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

}
