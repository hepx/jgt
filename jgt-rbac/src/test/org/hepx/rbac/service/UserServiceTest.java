package org.hepx.rbac.service;

import org.hepx.rbac.entity.User;
import org.hepx.rbac.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

/**
 * User: hepanxi
 * Date: 15-3-21
 * Time: 上午11:15
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test

    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("xixi");
        user.setPassword("aaaaa");
        user.setOrganizationId(1L);
        user.setSalt("aaaaaa");
        userService.createUser(user);
        System.out.println(user.toString());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateUser() throws Exception {
        User user = userService.findByUsername("xixi");
        System.out.println("修改前："+user.toString());
        user.setLocked(true);
        user.setPassword("55555");
        user.setSalt("44444");
        user.setOrganizationId(2l);
        user.setRoleIdsStr("1,2");
        userService.updateUser(user);
        System.out.println("修改后：" + user);
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteUser() throws Exception {
        userService.deleteUser(8l);
        User user = userService.findOne(8l);
        Assert.isNull(user);
    }

    @Test
    @Transactional
    @Rollback
    public void testChangePassword() throws Exception {
        System.out.println("修改前："+userService.findOne(8l).getPassword());
        userService.changePassword(8l,"88989");
        System.out.println("修改后："+userService.findOne(8l).getPassword());
    }

    @Test
    public void testFindOne() throws Exception {
        User user = userService.findOne(1l);
        System.out.println(user.toString());
    }

    @Test
    public void testFindAll()throws Exception{
        List<User> users = userService.findAll();
        for(User user : users){
            System.out.println(user.toString());
        }
    }

    @Test
    public void testFindByUsername() throws Exception {
       System.out.println(userService.findByUsername("xixi"));
    }

    @Test
    public void testFindRoles() throws Exception {
        Set<String> roles = userService.findRoles("xixi");
        System.out.println(roles);
    }


    @Test
    public void testFindPermissions() throws Exception {
       Set<String>permissions = userService.findPermissions("xixi");
        System.out.println("permissions = " + permissions);
    }
}
