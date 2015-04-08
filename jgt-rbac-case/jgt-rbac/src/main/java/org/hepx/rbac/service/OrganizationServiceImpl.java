package org.hepx.rbac.service;

import org.hepx.rbac.mapper.OrganizationMapper;
import org.hepx.rbac.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public Organization createOrganization(Organization organization) {
        organizationMapper.createOrganization(organization);
        return organization;
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        organizationMapper.updateOrganization(organization);
        return organization;
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationMapper.deleteOrganization(organizationId);
    }

    @Override
    public Organization findOne(Long organizationId) {
        return organizationMapper.findOne(organizationId);
    }

    @Override
    public List<Organization> findAll() {
        return organizationMapper.findAll();
    }

    @Override
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        return organizationMapper.findAllWithExclude(excludeOraganization);
    }

    @Override
    public void move(Organization source, Organization target) {
        Organization organization = new Organization();
        organization.setId(source.getId());
        organization.setParentId(target.getId());
        organization.setParentIds(target.getParentIds());
        organizationMapper.updateOrganization(organization);

        String t_ids = target.getSelfAsParentIds();
        String s_ids = source.getSelfAsParentIds();
        organizationMapper.move(t_ids,s_ids);
    }
}
