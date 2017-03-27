/*
Navicat MySQL Data Transfer

Source Server         : MySQL56
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : tinkerbell

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-03-09 13:15:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_perm`;
CREATE TABLE `auth_role_perm` (
  `PermissionId` int(11) DEFAULT NULL,
  `RoleId` int(11) DEFAULT NULL,
  KEY `FKbxyodexn6chxywu2n78dl39fs` (`PermissionId`),
  KEY `FKaflbpp7jd5cgeen7dortnpyax` (`RoleId`),
  CONSTRAINT `FKaflbpp7jd5cgeen7dortnpyax` FOREIGN KEY (`RoleId`) REFERENCES `auth_role` (`RoleId`),
  CONSTRAINT `FKbxyodexn6chxywu2n78dl39fs` FOREIGN KEY (`PermissionId`) REFERENCES `auth_perm` (`PermissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
