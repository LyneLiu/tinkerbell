CREATE TABLE `owner_table` (
`OwnerId`  int(11) NOT NULL COMMENT '个人ID' ,
`OwnerName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人姓名' ,
`OwnerAge`  int(11) NULL DEFAULT NULL COMMENT '个人年龄' ,
`OwnerAddress`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`DataChange_LastTime`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`OwnerId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=COMPACT
;
