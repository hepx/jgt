package org.hepx.ticket.service;

import org.hepx.ticket.mapper.RoleMapper;
import org.hepx.ticket.entity.Role;
import org.hepx.ticket.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceService resourceService;

    public Role createRole(Role role) {
        roleMapper.createRole(role);
        return role;
    }

    public Role updateRole(Role role) {
        roleMapper.updateRole(role);
        return role;
    }

    public void deleteRole(Long roleId) {
        roleMapper.deleteRole(roleId);
    }

    @Override
    public Role findOne(Long roleId) {
        return roleMapper.findOne(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }
}
