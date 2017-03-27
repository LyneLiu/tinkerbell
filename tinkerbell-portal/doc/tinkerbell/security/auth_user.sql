/*
Navicat MySQL Data Transfer

Source Server         : MySQL56
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : tinkerbell

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-03-09 13:16:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `UserName` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `Password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `PasswordConfirm` varchar(100) DEFAULT NULL COMMENT '密码确认',
  `Description` varchar(255) DEFAULT NULL COMMENT '用户信息描述',
  `DataChange_LastTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DateChange_LastTime',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
