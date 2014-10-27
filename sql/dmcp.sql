/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : dmcp

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-10-27 18:43:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '新闻ID',
  `newsTitle` varchar(512) DEFAULT NULL COMMENT '新闻标题',
  `newsUrl` varchar(512) DEFAULT NULL,
  `newsMedia` varchar(512) DEFAULT NULL COMMENT '新闻媒体',
  `newsAuthor` varchar(125) DEFAULT NULL COMMENT '新闻作者',
  `newsType` varchar(125) DEFAULT NULL COMMENT '新闻类型',
  `newsStatus` varchar(20) DEFAULT NULL COMMENT '新闻状态:0(无效)，1(正常)',
  `newsPosttime` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for `news_content`
-- ----------------------------
DROP TABLE IF EXISTS `news_content`;
CREATE TABLE `news_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext COMMENT '正文',
  `newsId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_content
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `roleDesc` varchar(512) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(45) DEFAULT '' COMMENT '邮件地址',
  `name` varchar(50) DEFAULT '' COMMENT '用户名称',
  `password` varchar(32) DEFAULT '' COMMENT '用户密码',
  `status` varchar(20) DEFAULT '' COMMENT '状态：0 隐藏 1 显示',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'dmcp@163.com', 'dmcp', 'b106a48c84b6cd332e61c36285966321', 'normal', '2014-02-17 10:59:07');
