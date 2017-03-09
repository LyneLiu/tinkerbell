/*
Navicat MySQL Data Transfer

Source Server         : MySQL56
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : tinkerbell

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-03-09 13:15:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_perm
-- ----------------------------
DROP TABLE IF EXISTS `auth_perm`;
CREATE TABLE `auth_perm` (
  `PermissionId` int(11) NOT NULL COMMENT '权限ID',
  `PermissionName` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `Description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `DataChange_LastTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'DataChange_LastTime',
  PRIMARY KEY (`PermissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
