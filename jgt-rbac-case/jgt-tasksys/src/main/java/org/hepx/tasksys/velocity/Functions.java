package org.hepx.tasksys.velocity;

import org.hepx.tasksys.entity.Organization;
import org.hepx.tasksys.entity.Resource;
import org.hepx.tasksys.entity.Role;
import org.hepx.tasksys.entity.User;
import org.hepx.tasksys.service.OrganizationService;
import org.hepx.tasksys.service.ResourceService;
import org.hepx.tasksys.service.RoleService;
import org.hepx.tasksys.service.UserService;
import org.hepx.tasksys.spring.SpringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Functions标签
 * Veloctiy Fun tag
 *
 */
public class Functions {

    public static boolean in(Iterable iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }

    /**
     * 根据id显示组织机构名称
     * @param organizationId
     * @return
     */
    public static String organizationName(Long organizationId) {
        Organization organization = getOrganizationService().findOne(organizationId);
        if(organization == null) {
            return "";
        }
        return organization.getName();
    }

    /**
     * 根据id列表显示多个组织机构名称
     * @param organizationIds
     * @return
     */
    public static String organizationNames(Collection<Long> organizationIds) {
        if(CollectionUtils.isEmpty(organizationIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Long organizationId : organizationIds) {
            Organization organization = getOrganizationService().findOne(organizationId);
            if(organization == null) {
                return "";
            }
            s.append(organization.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

    /**
     * 组织架构下拉菜单
     * @param name 组件名
     * @param selectId 选中的值，没有就为null
     * @param style 样式表
     * @return
     */
    public static String organizationSelect(String name,Long selectId,String style){
        if(selectId==null){
            selectId=0L;
        }
        if(style==null){
            style="";
        }
        List<Organization> organizationList = getOrganizationService().findAll();
        StringBuffer sb = new StringBuffer("<select id=\""+name+"\" name=\""+name+"\" class=\""+style+"\">");
        if(CollectionUtils.isEmpty(organizationList)){
            return sb.append("</select>").toString();
        }
        sb.append("<option value=\"\">请选择</option>");
        for(Organization organization : organizationList){
            if(selectId.equals(organization.getId())){
                sb.append("<option value=\""+organization.getId()+"\" selected>"+organization.getName()+"</option>");
            }else{
                sb.append("<option value=\""+organization.getId()+"\">"+organization.getName()+"</option>");
            }
        }
        return sb.append("<select>").toString();
    }


    /**
     * 根据id显示角色名称
     * @param roleId
     * @return
     */
    public static String roleName(Long roleId) {
        Role role = getRoleService().findOne(roleId);
        if(role == null) {
            return "";
        }
        return role.getDescription();
    }

    /**
     * 根据id列表显示多个角色名称
     * @param roleIds
     * @return
     */
    public static String roleNames(Collection<Long> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Long roleId : roleIds) {
            Role role = getRoleService().findOne(roleId);
            if(role == null) {
                return "";
            }
            s.append(role.getDescription());
            s.append(",");
        }
        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

    /**
     * 组织架构下拉菜单
     * @param name 组件名
     * @param selectId 选中的值，没有就为null
     * @param style 样式表
     * @return
     */
    public static String roleSingleSelect(String name,Long selectId,String style){
        if(selectId==null){
            selectId=0L;
        }
        if(style==null){
            style="";
        }
        List<Role> roleList = getRoleService().findAll();
        StringBuffer sb = new StringBuffer("<select id=\""+name+"\" name=\""+name+"\" class=\""+style+"\">");
        if(CollectionUtils.isEmpty(roleList)){
            return sb.append("</select>").toString();
        }
        sb.append("<option value=\"\">请选择</option>");
        for(Role role : roleList){
            if(selectId.equals(role.getId())){
                sb.append("<option value=\""+role.getId()+"\" selected>"+role.getDescription()+"</option>");
            }else{
                sb.append("<option value=\""+role.getId()+"\">"+role.getDescription()+"</option>");
            }
        }
        return sb.append("<select>").toString();
    }

    /**
     * 组织架构下拉菜单
     * @param name 组件名
     * @param selectIds 选中的值，没有就为null
     * @param style 样式表
     * @return
     */
    public static String roleMultipleSelect(String name,Collection<Long> selectIds,String style){
        Iterable<Long> iterator=null;
        if(!CollectionUtils.isEmpty(selectIds)){
            iterator= (Iterable<Long>)Collections.emptyIterator();
        }
        if(style==null){
            style="";
        }
        List<Role> roleList = getRoleService().findAll();
        StringBuffer sb = new StringBuffer("<select id=\""+name+"\"  name=\""+name+"\" class=\""+style+"\" multiple data-placeholder=\"选择角色\">");
        if(CollectionUtils.isEmpty(roleList)){
            return sb.append("</select>").toString();
        }

        for(Role role : roleList){
            if(in(iterator,role.getId())){
                sb.append("<option value=\""+role.getId()+"\" selected>"+role.getDescription()+"</option>");
            }else{
                sb.append("<option value=\""+role.getId()+"\">"+role.getDescription()+"</option>");
            }
        }
        return sb.append("<select>").toString();
    }

    /**
     * 根据id显示资源名称
     * @param resourceId
     * @return
     */
    public static String resourceName(Long resourceId) {
        Resource resource = getResourceService().findOne(resourceId);
        if(resource == null) {
            return "";
        }
        return resource.getName();
    }

    /**
     * 根据id列表显示多个资源名称
     * @param resourceIds
     * @return
     */
    public static String resourceNames(Collection<Long> resourceIds) {
        if(CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Long resourceId : resourceIds) {
            Resource resource = getResourceService().findOne(resourceId);
            if(resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }
        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

    /**
     * 根据ID显示用户名
     * @param userId
     * @return
     */
    public static String userName(Long userId){
        User user =getUserService().findOne(userId);
        if(user == null){
            return "";
        }else{
            return user.getUsername();
        }
    }

    /**
     * 用户下拉菜单
     * @param name 组件名
     * @param selectId 选中的值，没有就为null
     * @param style 样式表
     * @return
     */
    public static String userSelect(String name,Long selectId,String style){
        if(selectId==null){
            selectId=0L;
        }
        if(style==null){
            style="";
        }
        List<User> userList = getUserService().findAll();
        StringBuffer sb = new StringBuffer("<select id=\""+name+"\" name=\""+name+"\" class=\""+style+"\">");
        if(CollectionUtils.isEmpty(userList)){
            return sb.append("</select>").toString();
        }
        sb.append("<option value=\"\">请选择</option>");
        for(User user : userList){
            if(selectId.equals(user.getId())){
                sb.append("<option value=\""+user.getId()+"\" selected>"+user.getUsername()+"</option>");
            }else{
                sb.append("<option value=\""+user.getId()+"\">"+user.getUsername()+"</option>");
            }
        }
        return sb.append("<select>").toString();
    }

    private static OrganizationService organizationService;
    private static RoleService roleService;
    private static ResourceService resourceService;
    private static UserService userService;

    public static OrganizationService getOrganizationService() {
        if(organizationService == null) {
            organizationService = SpringUtils.getBean(OrganizationService.class);
        }
        return organizationService;
    }

    public static RoleService getRoleService() {
        if(roleService == null) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }

    public static ResourceService getResourceService() {
        if(resourceService == null) {
            resourceService = SpringUtils.getBean(ResourceService.class);
        }
        return resourceService;
    }

    public static UserService getUserService(){
        if(userService ==  null){
            userService = SpringUtils.getBean(UserService.class);
        }
        return userService;
    }
}

