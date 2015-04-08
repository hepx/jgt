package org.hepx.rbac.mapper;

import org.hepx.rbac.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-3-28
 * Time: 上午9:39
 */
@Repository
public interface TaskMapper {
    
    public int createTask(Task task);

    public int updateTask(Task task);

    public int deleteTask(Long taskId);

    public Task findOne(Long taskId);

    public List<Task> findAll();

    public List<Task> findByUserId(Long userId);
}
