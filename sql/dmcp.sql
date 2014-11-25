/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : dmcp

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-11-10 17:07:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `funcgroup_func`
-- ----------------------------
DROP TABLE IF EXISTS `funcgroup_func`;
CREATE TABLE `funcgroup_func` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`functionId`  int(11) NOT NULL ,
`functionGroupId`  int(11) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `function`
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`funcName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`funcDesc`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`actionUrl`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`param`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `functiongroup`
-- ----------------------------
DROP TABLE IF EXISTS `functiongroup`;
CREATE TABLE `functiongroup` (
`id`  int(11) NOT NULL DEFAULT 0 ,
`name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`actionUrl`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`functions`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
`id`  bigint(11) NOT NULL AUTO_INCREMENT ,
`newsTitle`  varchar(500) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT '0' ,
`newsUrl`  varchar(500) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`newsMedia`  varchar(200) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`newsAuthor`  varchar(300) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`newsType`  int(10) NOT NULL DEFAULT 0 ,
`newsStatus`  int(1) NOT NULL ,
`newsPosttime`  datetime NOT NULL ,
PRIMARY KEY (`id`),
INDEX `news_id` (`id`) USING BTREE ,
INDEX `news_title` (`newsTitle`) USING BTREE 
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci
AUTO_INCREMENT=6609957

;

-- ----------------------------
-- Table structure for `news_content`
-- ----------------------------
DROP TABLE IF EXISTS `news_content`;
CREATE TABLE `news_content` (
`id`  bigint(11) NOT NULL AUTO_INCREMENT ,
`newsId`  bigint(11) NOT NULL ,
`content`  mediumtext CHARACTER SET gbk COLLATE gbk_chinese_ci NULL ,
PRIMARY KEY (`id`),
INDEX `newsId_content` (`newsId`) USING BTREE 
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci
AUTO_INCREMENT=4309936

;

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
`id`  int(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID' ,
`roleName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称' ,
`roleDesc`  varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `role_func`
-- ----------------------------
DROP TABLE IF EXISTS `role_func`;
CREATE TABLE `role_func` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`roleId`  int(11) NULL DEFAULT NULL ,
`funcId`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`userId`  bigint(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID' ,
`email`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮件地址' ,
`name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户名称' ,
`password`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户密码' ,
`status`  int(2) NULL DEFAULT NULL COMMENT '状态：0 隐藏 1 显示' ,
`createTime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
PRIMARY KEY (`userId`),
UNIQUE INDEX `idx_email` (`email`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=40

;

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`useId`  int(11) NOT NULL ,
`roleId`  int(11) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `content` text,
  `status` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Auto increment value for `funcgroup_func`
-- ----------------------------
ALTER TABLE `funcgroup_func` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `function`
-- ----------------------------
ALTER TABLE `function` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `news`
-- ----------------------------
ALTER TABLE `news` AUTO_INCREMENT=6609957;

-- ----------------------------
-- Auto increment value for `news_content`
-- ----------------------------
ALTER TABLE `news_content` AUTO_INCREMENT=4309936;

-- ----------------------------
-- Auto increment value for `role`
-- ----------------------------
ALTER TABLE `role` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `role_func`
-- ----------------------------
ALTER TABLE `role_func` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `user`
-- ----------------------------
ALTER TABLE `user` AUTO_INCREMENT=40;

-- ----------------------------
-- Auto increment value for `user_role`
-- ----------------------------
ALTER TABLE `user_role` AUTO_INCREMENT=1;
