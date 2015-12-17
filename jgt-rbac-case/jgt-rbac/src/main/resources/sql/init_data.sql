/*
Navicat MySQL Data Transfer

Source Server         : 172.16.30.76
Source Server Version : 50709
Source Host           : 172.16.30.76:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50599
File Encoding         : 65001

Date: 2015-12-17 18:13:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
BEGIN;
INSERT INTO `sys_organization` VALUES ('1', '总公司', '0', '0/', '1'), ('2', '分公司1', '1', '0/1/', '1'), ('3', '分公司2', '1', '0/1/', '1'), ('4', '分公司11', '2', '0/1/2/', '1');
COMMIT;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES ('1', '资源', 'menu', '', '0', '0/', '', null, '1'), ('11', '组织机构管理', 'menu', '/organization', '1', '0/1/', 'organization:*', null, '1'), ('12', '组织机构新增', 'button', '', '11', '0/1/11/', 'organization:create', null, '1'), ('13', '组织机构修改', 'button', '', '11', '0/1/11/', 'organization:update', null, '1'), ('14', '组织机构删除', 'button', '', '11', '0/1/11/', 'organization:delete', null, '1'), ('15', '组织机构查看', 'button', '', '11', '0/1/11/', 'organization:view', null, '1'), ('21', '用户管理', 'menu', '/user', '1', '0/1/', 'user:*', null, '1'), ('22', '用户新增', 'button', '', '21', '0/1/21/', 'user:create', null, '1'), ('23', '用户修改', 'button', '', '21', '0/1/21/', 'user:update', null, '1'), ('24', '用户删除', 'button', '', '21', '0/1/21/', 'user:delete', null, '1'), ('25', '用户查看', 'button', '', '21', '0/1/21/', 'user:view', null, '1'), ('31', '资源管理', 'menu', '/resource', '1', '0/1/', 'resource:*', null, '1'), ('32', '资源新增', 'button', '', '31', '0/1/31/', 'resource:create', null, '1'), ('33', '资源修改', 'button', '', '31', '0/1/31/', 'resource:update', null, '1'), ('34', '资源删除', 'button', '', '31', '0/1/31/', 'resource:delete', null, '1'), ('35', '资源查看', 'button', '', '31', '0/1/31/', 'resource:view', null, '1'), ('41', '角色管理', 'menu', '/role', '1', '0/1/', 'role:*', null, '1'), ('42', '角色新增', 'button', '', '41', '0/1/41/', 'role:create', null, '1'), ('43', '角色修改', 'button', '', '41', '0/1/41/', 'role:update', null, '1'), ('44', '角色删除', 'button', '', '41', '0/1/41/', 'role:delete', null, '1'), ('45', '角色查看', 'button', '', '41', '0/1/41/', 'role:view', null, '1');
COMMIT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', '11,21,31,41', '1'), ('3', 'dev', '开发者', '25', null);
COMMIT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', 'd3c59d25033dbf980d29554025c23a75', null, null, '8d78869f470951332959580424d4bf4f', '1', '0', '1'), ('3', 'hepx', '775634a551ed8c8fadf032087719516c', '13312974164', 'hepanxi@163.com', '023f8fdbc8a7432277925ae5f3d7bf8f', '3', '0', '1');
COMMIT;
