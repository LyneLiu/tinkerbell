/*
Navicat MySQL Data Transfer

Source Server         : MySQL56
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : tinkerbell

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-03-09 13:16:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `UserId` int(11) DEFAULT NULL COMMENT '用户ID',
  `RoleId` int(11) DEFAULT NULL COMMENT '角色ID',
  KEY `FKj41nq92kqfhr0bfq8ydd5jwfd` (`RoleId`),
  KEY `FKra8vxoypgaiv6smaw01btrwij` (`UserId`),
  CONSTRAINT `FKra8vxoypgaiv6smaw01btrwij` FOREIGN KEY (`UserId`) REFERENCES `auth_user` (`UserId`),
  CONSTRAINT `FK9gbj9wmrlx8xqhrpniffqeirh` FOREIGN KEY (`RoleId`) REFERENCES `auth_role` (`RoleId`),
  CONSTRAINT `FKhmg49mqcid14f615wjeyixivp` FOREIGN KEY (`UserId`) REFERENCES `auth_user` (`UserId`),
  CONSTRAINT `FKj41nq92kqfhr0bfq8ydd5jwfd` FOREIGN KEY (`RoleId`) REFERENCES `auth_role` (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
