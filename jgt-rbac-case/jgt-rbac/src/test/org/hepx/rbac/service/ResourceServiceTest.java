package org.hepx.rbac.service;

import org.hepx.rbac.entity.Resource;
import org.hepx.rbac.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * User: hepanxi
 * Date: 15-3-23
 * Time: 下午4:33
 */
public class ResourceServiceTest extends BaseTest {

    @Autowired
    private ResourceService resourceService;

    @Test
    public void testCreateResource() throws Exception {

    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateResource() throws Exception {
        Resource resource =resourceService.findOne(45L);
        resource.setName("TEST");
        resource.setAvailable(Boolean.FALSE);
        resourceService.updateResource(resource);
        Resource resource_up =resourceService.findOne(45L);
        Assert.assertEquals("TEST",resource_up.getName());
        Assert.assertFalse(resource.getAvailable());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteResource() throws Exception {
        resourceService.deleteResource(45L);
        Assert.assertNull(resourceService.findOne(45l));
    }

    @Test
    public void testFindOne() throws Exception {
        Resource resource = resourceService.findOne(45L);
        Assert.assertTrue(resource.getAvailable());
    }

    @Test
    public void testFindPermissions() throws Exception {
        Set<Long> res=new HashSet<Long>();
        res.add(11L);
        res.add(12l);
        Set<String>perms = resourceService.findPermissions(res);
        System.out.println(Arrays.toString(perms.toArray()));
    }

    @Test
    public void testFindMenus() throws Exception {
        Set<Long> res=new HashSet<Long>();
        res.add(11L);
        res.add(12l);
        Set<String> perms = resourceService.findPermissions(res);
        List<Resource> relist = resourceService.findMenus(perms);
        for(Resource r : relist){
            System.out.println(r.getName());
        }
    }
}
