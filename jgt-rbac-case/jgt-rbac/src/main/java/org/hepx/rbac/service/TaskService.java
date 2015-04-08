package org.hepx.rbac.service;

import org.hepx.rbac.entity.Task;

import java.util.List;

public interface TaskService {

    public Task createTask(Task task);

    public int updateTask(Task task);

    public int deleteTask(Long taskId);

    public Task findOne(Long taskId);

    public List<Task> findAll();

    public List<Task> findByUserId(Long userId);
}
