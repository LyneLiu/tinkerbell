/*
Navicat MySQL Data Transfer

Source Server         : MySQL56
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : tinkerbell

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-03-09 13:17:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for owner_table
-- ----------------------------
DROP TABLE IF EXISTS `owner_table`;
CREATE TABLE `owner_table` (
  `OwnerId` int(11) NOT NULL DEFAULT '0' COMMENT '个人ID',
  `OwnerName` varchar(50) DEFAULT NULL COMMENT '个人姓名',
  `OwnerAge` int(11) DEFAULT NULL COMMENT '个人年龄',
  `OwnerAddress` varchar(100) DEFAULT NULL,
  `DataChange_LastTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'DataChange_LastTime',
  PRIMARY KEY (`OwnerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
