package org.hepx.jgt.scheduler.db.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.quartz.Job;

import java.io.Serializable;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/10/23 15:17
 */
@Data
@ToString
public class JobInfo implements Serializable {

    private Class<? extends Job> jobClass;
    /**
     * 任务名
     */
    private String name;

    /**
     * 分组
     */
    private String groupName="jgt";

    /**
     * 状态
     */
    private String status;

    /**
     * 时间: 提供两种模式：
     * Simple: 单位：秒，例：5
     * Cron:   cron表达式
     */
    private String time;

    /**
     * 描述
     */
    private String description;

}
