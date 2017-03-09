CREATE TABLE `auth_user` (
`UserId`  int NOT NULL AUTO_INCREMENT COMMENT '用户ID' ,
`UserName`  varchar(100) NULL COMMENT '用户名称' ,
`Password`  varchar(100) NULL COMMENT '用户密码' ,
`PasswordConfirm`  varchar(100) NULL COMMENT '密码确认' ,
`Description`  varchar(255) NULL COMMENT '用户信息描述' ,
`DataChange_LastTime`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`UserId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
