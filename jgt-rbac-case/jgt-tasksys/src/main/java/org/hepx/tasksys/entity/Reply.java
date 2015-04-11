package org.hepx.tasksys.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务回复
 * User: hepanxi
 * Date: 15-4-11
 * Time: 上午9:21
 */
@Alias("o_reply")
public class Reply extends IdEntity implements Serializable {

    private String content; //回复内容

    private Date createTime;//回复时间

    private Long userId;    //回复人ID

    private Long taskId;    //回复对应的任务

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
