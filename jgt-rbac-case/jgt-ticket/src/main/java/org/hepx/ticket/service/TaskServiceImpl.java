package org.hepx.ticket.service;

import org.hepx.ticket.entity.Task;
import org.hepx.ticket.mapper.TaskMapper;
import org.hepx.ticket.entity.Task;
import org.hepx.ticket.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * User: hepanxi
 * Date: 15-3-28
 * Time: 上午9:53
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Task createTask(Task task) {
        task.setCreateTime(new Date());//新任务默认当前时间
        taskMapper.createTask(task);
        return task;
    }

    @Override
    public int updateTask(Task task) {
        //每次修改都更新时间
        task.setUpdateTime(new Date());
        return taskMapper.updateTask(task);
    }

    @Override
    public int deleteTask(Long taskId) {
        return taskMapper.deleteTask(taskId);
    }

    @Override
    public Task findOne(Long taskId) {
        return taskMapper.findOne(taskId);
    }

    @Override
    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return taskMapper.findByUserId(userId);
    }
}
