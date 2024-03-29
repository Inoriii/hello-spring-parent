CREATE DATABASE hello_spring CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE USER 'inoriii'@'%' IDENTIFIED BY '123456';
GRANT All privileges ON hello_spring.* TO 'inoriii'@'%';

CREATE TABLE hello_spring.`user_test`
(
    `id`          int(11)                                                 NOT NULL AUTO_INCREMENT,
    `username`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户名称',
    `birthday`    date                                                    NULL     DEFAULT NULL COMMENT '出生日期',
    `sex`         char(1) CHARACTER SET utf8 COLLATE utf8_general_ci      NULL     DEFAULT NULL COMMENT '性别 0:男 1：女',
    `address`     varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '地址',
    `create_time` datetime(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE hello_spring.`user`
(
    `id`          int(11)                                                 NOT NULL AUTO_INCREMENT,
    `username`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户名称',
    `password`    varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
    `enabled`     boolean                                                 NOT NULL DEFAULT TRUE COMMENT '是否可用',
    `create_time` datetime(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE hello_spring.`role`
(
    `id`          int(11)                                                NOT NULL AUTO_INCREMENT,
    `role_name`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
    `enabled`     boolean                                                NOT NULL DEFAULT TRUE COMMENT '是否可用',
    `create_time` datetime(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE hello_spring.`user_role`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT,
    `user_id`     int(11)     NOT NULL COMMENT '用户id',
    `role_id`     int(11)     NOT NULL COMMENT '角色id',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE hello_spring.`permission`
(
    `id`              int(11)                                                NOT NULL AUTO_INCREMENT,
    `permission_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
    `enabled`         boolean                                                NOT NULL DEFAULT TRUE COMMENT '是否可用',
    `create_time`     datetime(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time`     datetime(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE hello_spring.`role_permission`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT,
    `role_id`       int(11)     NOT NULL COMMENT '角色id',
    `permission_id` int(11)     NOT NULL COMMENT '权限id',
    `create_time`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE hello_spring.`resource`
(
    `id`          int(11)                                                NOT NULL AUTO_INCREMENT,
    `url`         varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源url',
    `enabled`     boolean                                                NOT NULL DEFAULT TRUE COMMENT '是否可用',
    `create_time` datetime(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

CREATE TABLE hello_spring.`resource_role_permission`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT,
    `resource_id`   int(11)     NOT NULL COMMENT '资源id',
    `role_id`       int(11) COMMENT '角色id',
    `permission_id` int(11) COMMENT '权限id',
    `create_time`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;