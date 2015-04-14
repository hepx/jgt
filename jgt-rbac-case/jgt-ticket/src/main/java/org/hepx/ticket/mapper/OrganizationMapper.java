package org.hepx.ticket.mapper;

import org.apache.ibatis.annotations.Param;
import org.hepx.ticket.entity.Organization;
import org.hepx.ticket.entity.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationMapper {

    public int createOrganization(Organization organization);

    public int updateOrganization(Organization organization);

    public int deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);

    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);

    public int move(@Param("t_ids")String t_ids,@Param("s_ids")String s_ids);

}
