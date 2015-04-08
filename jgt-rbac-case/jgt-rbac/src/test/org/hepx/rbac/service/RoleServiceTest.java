package org.hepx.rbac.service;

import org.hepx.rbac.entity.Role;
import org.hepx.rbac.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;

/**
 * User: hepanxi
 * Date: 15-3-23
 * Time: 上午10:59
 */
public class RoleServiceTest extends BaseTest {

    Long [] roleIds =new Long[]{11L,21L};
    Long [] roleIds_up =new Long[]{11L,21L,31L};

    @Autowired
    private RoleService roleService;

    @Test
    public void testCreateRole() throws Exception {
        Role role =new Role();
        role.setRole("test");
        role.setDescription("测试人员");
        role.setAvailable(true);
        role.setResourceIds(Arrays.asList(roleIds));
        Role newRole=roleService.createRole(role);
        System.out.println(newRole.toString());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateRole() throws Exception {
        Role role = roleService.findOne(5L);
        System.out.println("修改前："+role.toString());
        role.setResourceIds(Arrays.asList(roleIds_up));
        role.setDescription("测试职位");
        role.setAvailable(false);
        Role newRole = roleService.updateRole(role);
        System.out.println("修改后："+newRole.toString());
    }

    @Test
    @Rollback
    public void testDeleteRole() throws Exception {
        roleService.deleteRole(5l);
    }

    @Test
    public void testFindOne() throws Exception {
        Role role = roleService.findOne(4l);
        System.out.println(role.toString());
    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testFindRoles() throws Exception {
        Set<String> roles = roleService.findRoles(1L,4L);
        System.out.println(Arrays.toString(roles.toArray()));
    }

    @Test
    public void testFindPermissions() throws Exception {
        Long [] roleIds =new Long[]{1L,4L};
        Set<String>permissions = roleService.findPermissions(roleIds);
        System.out.println(Arrays.toString(permissions.toArray()));
    }
}
