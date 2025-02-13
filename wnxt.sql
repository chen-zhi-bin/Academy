/*
 Navicat Premium Data Transfer

 Source Server         : localhost(本地)
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : wnxt

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 17/09/2022 09:09:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wnxt_chapter
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_chapter`;
CREATE TABLE `wnxt_chapter`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '章节ID',
  `course_id` bigint(20) NOT NULL COMMENT '课程ID',
  `title` varchar(255) NOT NULL COMMENT '章节名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '课程章节表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for wnxt_comment
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_comment`;
CREATE TABLE `wnxt_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '培训师ID',
  `course_id` bigint(20) NOT NULL COMMENT '课程id',
  `trainer_id` bigint(20) NOT NULL COMMENT '培训师id',
  `user_id` bigint(20) NOT NULL COMMENT '会员id',
  `nickname` varchar(255) NULL DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) NULL DEFAULT NULL COMMENT '会员头像',
  `content` varchar(255) NULL DEFAULT NULL COMMENT '评论内容',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_trainer_id`(`trainer_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wnxt_course
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_course`;
CREATE TABLE `wnxt_course`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `trainer_id` bigint(20) NOT NULL COMMENT '课程培训师ID',
  `subject_id` bigint(20) NOT NULL COMMENT '课程专业ID',
  `subjepct_arent_id` bigint(20) NOT NULL COMMENT '课程专业父级ID',
  `title` varchar(255) NOT NULL COMMENT '课程标题',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int(10) NOT NULL DEFAULT 0 COMMENT '总课时',
  `cover` varchar(255) NOT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint(10) NOT NULL DEFAULT 0 COMMENT '销售数量',
  `view_count` bigint(10) NOT NULL DEFAULT 0 COMMENT '浏览数量',
  `version` bigint(20) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `status` varchar(10) NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  `is_deleted` tinyint(3) NULL DEFAULT NULL COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title`) USING BTREE,
  INDEX `idx_subject_id`(`subject_id`) USING BTREE,
  INDEX `idx_trainer_id`(`trainer_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '课程表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for wnxt_course_collect
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_course_collect`;
CREATE TABLE `wnxt_course_collect`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
  `course_id` bigint(20) NOT NULL COMMENT '课程培训师ID',
  `user_id` bigint(20) NOT NULL COMMENT '课程专业ID',
  `is_deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '课程收藏表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for wnxt_course_description
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_course_description`;
CREATE TABLE `wnxt_course_description`  (
  `id` bigint(20) NOT NULL COMMENT '课程ID',
  `description` text NULL COMMENT '课程简介',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '课程简介表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wnxt_subject
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_subject`;
CREATE TABLE `wnxt_subject`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程类别ID',
  `title` varchar(10) NOT NULL COMMENT '类别名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '课程科目表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for wnxt_trainer
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_trainer`;
CREATE TABLE `wnxt_trainer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '培训师ID',
  `name` varchar(255) NOT NULL COMMENT '培训师姓名',
  `intro` varchar(255) NOT NULL DEFAULT '' COMMENT '培训师简介',
  `career` varchar(255) DEFAULT NULL COMMENT '培训师资历,一句话说明培训师',
  `level` int(10) UNSIGNED NOT NULL COMMENT '头衔 1高级培训师 2首席培训师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '培训师头像',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE
) ENGINE = InnoDB COMMENT = '培训师表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wnxt_video
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_video`;
CREATE TABLE `wnxt_video`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `course_id` bigint(20) NOT NULL COMMENT '课程ID',
  `chapter_id` bigint(20) NOT NULL COMMENT '章节ID',
  `title` varchar(255) NOT NULL COMMENT '节点名称',
  `video_source_id` bigint(20) DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `play_count` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '播放次数',
  `is_free` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT 0 COMMENT '视频时长（秒）',
  `status` varchar(20) NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
  `size` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '视频源文件大小（字节）',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '课程视频表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for wnxt_user
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_user`;
CREATE TABLE `wnxt_user`  (
  `id` char(19) NOT NULL COMMENT '会员id',
  `openid` varchar(255) NULL DEFAULT NULL COMMENT '第三方授权ID',
  `mobile` varchar(255) NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `username` varchar(255) NOT NULL COMMENT '用户账号',
  `password` varchar(255) NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) UNSIGNED NULL DEFAULT NULL COMMENT '性别 1 女，2 男',
  `age` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) NULL DEFAULT NULL COMMENT '用户头像',
  `user_type` tinyint(1) DEFAULT 1 COMMENT '用户类型（0:系统用户 1:平台用户）',
  `sign` varchar(100) NULL DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '会员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wnxt_role
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_role`;
CREATE TABLE `wnxt_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wnxt_user_role
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_user_role`;
CREATE TABLE `wnxt_user_role`  (
  `user_id` char(19) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wnxt_menu
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_menu`;
CREATE TABLE `wnxt_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) NULL DEFAULT '#' COMMENT '菜单图标',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2006 COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wnxt_menu
-- ----------------------------
INSERT INTO `wnxt_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, '', 1, 'M', '0', '0', '', 'system', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, '', 1, 'M', '0', '0', '', 'monitor', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 'C', '0', '0', 'system:user:list', 'user', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 'C', '0', '0', 'system:role:list', 'peoples', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 'C', '0', '0', 'system:menu:list', 'tree-table', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (106, '参数设置', 1, 4, 'config', 'system/config/index', '', 1, 'C', '0', '0', 'system:config:list', 'edit', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (107, '通知公告', 1, 5, 'notice', 'system/notice/index', '', 1, 'C', '0', '0', 'system:notice:list', 'message', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (108, '日志管理', 1, 6, 'log', '', '', 1, 'M', '0', '0', '', 'log', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (111, '数据监控', 2, 1, 'druid', 'monitor/druid/index', '', 1, 'C', '0', '0', 'monitor:druid:list', 'druid', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (112, '服务监控', 2, 2, 'server', 'monitor/server/index', '', 1, 'C', '0', '0', 'monitor:server:list', 'server', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 'C', '0', '0', 'monitor:operlog:list', 'form', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1000, '用户查询', 100, 1, '', '', '', 1, 'F', '0', '0', 'system:user:query', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1001, '用户新增', 100, 2, '', '', '', 1, 'F', '0', '0', 'system:user:add', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1002, '用户修改', 100, 3, '', '', '', 1, 'F', '0', '0', 'system:user:edit', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1003, '用户删除', 100, 4, '', '', '', 1, 'F', '0', '0', 'system:user:remove', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1004, '用户导出', 100, 5, '', '', '', 1, 'F', '0', '0', 'system:user:export', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1005, '用户导入', 100, 6, '', '', '', 1, 'F', '0', '0', 'system:user:import', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1006, '重置密码', 100, 7, '', '', '', 1, 'F', '0', '0', 'system:user:resetPwd', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1007, '角色查询', 101, 1, '', '', '', 1, 'F', '0', '0', 'system:role:query', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1008, '角色新增', 101, 2, '', '', '', 1, 'F', '0', '0', 'system:role:add', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1009, '角色修改', 101, 3, '', '', '', 1, 'F', '0', '0', 'system:role:edit', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1010, '角色删除', 101, 4, '', '', '', 1, 'F', '0', '0', 'system:role:remove', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1011, '角色导出', 101, 5, '', '', '', 1, 'F', '0', '0', 'system:role:export', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1012, '菜单查询', 102, 1, '', '', '', 1, 'F', '0', '0', 'system:menu:query', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1013, '菜单新增', 102, 2, '', '', '', 1, 'F', '0', '0', 'system:menu:add', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1014, '菜单修改', 102, 3, '', '', '', 1, 'F', '0', '0', 'system:menu:edit', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1015, '菜单删除', 102, 4, '', '', '', 1, 'F', '0', '0', 'system:menu:remove', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1030, '参数查询', 106, 1, '#', '', '', 1, 'F', '0', '0', 'system:config:query', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1031, '参数新增', 106, 2, '#', '', '', 1, 'F', '0', '0', 'system:config:add', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1032, '参数修改', 106, 3, '#', '', '', 1, 'F', '0', '0', 'system:config:edit', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1033, '参数删除', 106, 4, '#', '', '', 1, 'F', '0', '0', 'system:config:remove', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1034, '参数导出', 106, 5, '#', '', '', 1, 'F', '0', '0', 'system:config:export', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1035, '公告查询', 107, 1, '#', '', '', 1, 'F', '0', '0', 'system:notice:query', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1036, '公告新增', 107, 2, '#', '', '', 1, 'F', '0', '0', 'system:notice:add', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1037, '公告修改', 107, 3, '#', '', '', 1, 'F', '0', '0', 'system:notice:edit', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1038, '公告删除', 107, 4, '#', '', '', 1, 'F', '0', '0', 'system:notice:remove', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1039, '操作查询', 500, 1, '#', '', '', 1, 'F', '0', '0', 'monitor:operlog:query', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1040, '操作删除', 500, 2, '#', '', '', 1, 'F', '0', '0', 'monitor:operlog:remove', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1041, '日志导出', 500, 3, '#', '', '', 1, 'F', '0', '0', 'monitor:operlog:export', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1042, '登录查询', 501, 1, '#', '', '', 1, 'F', '0', '0', 'monitor:logininfor:query', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1043, '登录删除', 501, 2, '#', '', '', 1, 'F', '0', '0', 'monitor:logininfor:remove', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1044, '日志导出', 501, 3, '#', '', '', 1, 'F', '0', '0', 'monitor:logininfor:export', '#', '2023-06-07 15:45:00', NULL);
INSERT INTO `wnxt_menu` VALUES (1045, '账户解锁', 501, 4, '#', '', '', 1, 'F', '0', '0', 'monitor:logininfor:unlock', '#', '2023-06-07 15:45:00', NULL);

-- ----------------------------
-- Table structure for wnxt_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `wnxt_role_menu`;
CREATE TABLE `wnxt_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
