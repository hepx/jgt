package org.hepx.jgt.ssm.repository;

import org.apache.ibatis.annotations.Param;
import org.hepx.jgt.ssm.common.mybatis.pagehelper.Page;
import org.hepx.jgt.ssm.entity.Task;

import java.util.List;

@MyBatisRepository
public interface TaskDao {

    //Page<Task> findByUserId(Long id, Pageable pageRequest);

    Task get(Long id);

    List<Task> getAll();

    void save(Task task);

    void delete(Long id);

    void deleteByUserId(@Param("userId")Long userId);

    List<Task> findAll(Page page);
}
