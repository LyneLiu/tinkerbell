CREATE TABLE `auth_role` (
`RoleId`  int NOT NULL COMMENT '角色ID' ,
`RoleName`  varchar(100) NULL COMMENT '角色名称' ,
`DataChange_LastTime`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`RoleId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
