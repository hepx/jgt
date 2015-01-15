package org.hepx.jgt.ssm.service.task;

import org.hepx.jgt.ssm.common.mybatis.pagehelper.Page;
import org.hepx.jgt.ssm.entity.Task;
import org.hepx.jgt.ssm.repository.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class TaskService {

    @Autowired
	private TaskDao taskDao;

	public Task getTask(Long id) {
		return taskDao.get(id);
	}

	public void saveTask(Task entity) {
		taskDao.save(entity);
	}

    public void updateTask(Task entity){
        taskDao.update(entity);
    }

	public void deleteTask(Long id) {
		taskDao.delete(id);
	}

	public List<Task> getAllTask() {
		return taskDao.getAll();
	}

	public Page<Task> getUserTask(Map<String, Object> searchParams, int pageNumber, int pageSize) {
        Page<Task>page =new Page<Task>();
        page.setPageNo(pageNumber);
        page.setPageSize(pageSize);
        page.setParams(searchParams);
        List<Task> list = taskDao.findAll(page);
		page.setResults(list);
        return page;
	}

}
