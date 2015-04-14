package org.hepx.ticket.mapper;

import org.hepx.ticket.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    public int createRole(Role role);

    public int updateRole(Role role);

    public int deleteRole(Long roleId);

    public Role findOne(Long roleId);

    public List<Role> findAll();
}
