package org.hepx.rbac.service;

import org.hepx.rbac.entity.Organization;
import org.hepx.rbac.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-3-23
 * Time: 下午5:48
 */
public class OrganizationServiceTest extends BaseTest {

    @Autowired
    private OrganizationService organizationService;

    @Test
    @Transactional
    @Rollback
    public void testCreateOrganization() throws Exception {
        Organization organization =new Organization();
        organization.setName("分公司22");
        organization.setAvailable(Boolean.TRUE);
        organization.setParentId(1L);
        organization.setParentIds("0/1/");
        organizationService.createOrganization(organization);
        Assert.assertNotNull(organization.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateOrganization() throws Exception {
        Organization organization = organizationService.findOne(1L);
        organization.setAvailable(Boolean.FALSE);
        organization.setName("总公司11");
        organizationService.updateOrganization(organization);
        Organization organization_up = organizationService.findOne(1L);
        Assert.assertEquals("总公司11",organization_up.getName());
        Assert.assertFalse(organization_up.getAvailable());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteOrganization() throws Exception {
        organizationService.deleteOrganization(1L);
        Assert.assertNull(organizationService.findOne(1L));
    }

    @Test
    public void testFindOne() throws Exception {
        Organization organization = organizationService.findOne(1L);
        Assert.assertEquals("总公司",organization.getName());
    }

    @Test
    public void testFindAllWithExclude() throws Exception {
        Organization organization = organizationService.findOne(2L);
        List<Organization> lists = organizationService.findAllWithExclude(organization);
        for(Organization o:lists){
            System.out.println(o.toString());
        }
    }
}
