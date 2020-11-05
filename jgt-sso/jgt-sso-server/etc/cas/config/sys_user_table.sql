CREATE TABLE `sys_user`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(100) DEFAULT NULL COMMENT '登录名',
    `password` varchar(100) DEFAULT NULL COMMENT '密码',
    `emp_name` varchar(100) DEFAULT NULL COMMENT '员工姓名',
    `mobile`   varchar(11)  DEFAULT NULL COMMENT '手机号码',
    `email`    varchar(30)  DEFAULT NULL COMMENT '邮箱',
    `disabled` tinyint(1)   DEFAULT '0' COMMENT '是否禁用',
    `expired`  tinyint(1)   DEFAULT '0' COMMENT '是否过期',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `username_uq` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;