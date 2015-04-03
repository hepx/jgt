package org.hepx.tasksys.mapper;

import org.hepx.tasksys.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceMapper {

    public int createResource(Resource resource);

    public int updateResource(Resource resource);

    public int deleteResource(Long resourceId);

    Resource findOne(Long resourceId);

    List<Resource> findAll();

}
