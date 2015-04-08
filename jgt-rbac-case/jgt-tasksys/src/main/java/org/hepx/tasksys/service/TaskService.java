package org.hepx.tasksys.service;

import org.hepx.tasksys.entity.Task;

import java.util.List;

public interface TaskService {

    public Task createTask(Task task);

    public int updateTask(Task task);

    public int deleteTask(Long taskId);

    public Task findOne(Long taskId);

    public List<Task> findAll();

    public List<Task> findByUserId(Long userId);

    public Long getTotalTask();
}
