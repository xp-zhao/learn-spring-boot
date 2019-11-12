drop table if exists tbl_userinfo;
create table `tbl_userinfo`(
	`id` int(11) not null AUTO_INCREMENT comment '用户 id',
	`user_name` varchar(20) not null comment '用户名称',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

drop table if exists tbl_role;
create table `tbl_role`(
	`id` int(11) not null AUTO_INCREMENT comment '角色 id',
	`role_name` varchar(20) not null comment '角色名称',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

drop table if exists tbl_user_role;
create table `tbl_user_role`(
	`id` int(11) not null AUTO_INCREMENT,
	`user_id` int(11) not null comment '用户 id',
	`role_id` int(11) not null comment '角色 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

drop table if exists tbl_usergroup;
create table `tbl_usergroup`(
	`id` int(11) not null AUTO_INCREMENT comment '用户组 id',
	`usergroup_name` varchar(20) not null comment '用户组名称',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组表';

drop table if exists tbl_usergroup_user;
create table `tbl_usergroup_user`(
	`id` int(11) not null AUTO_INCREMENT,
	`usergroup_id` int(11) not null comment '用户组 id',
	`user_id` int(11) not null comment '用户 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组与用户表';

drop table if exists tbl_usergroup_role;
create table `tbl_usergroup_role`(
	`id` int(11) not null AUTO_INCREMENT,
	`usergroup_id` int(11) not null comment '用户组 id',
	`role_id` int(11) not null comment '角色 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组与角色表';

drop table if exists tbl_menu;
create table `tbl_menu`(
	`id` int(11) not null AUTO_INCREMENT comment '菜单 id',
	`menu_name` varchar(20) not null comment '菜单名称',
	`menu_url` varchar(100) default null comment '菜单 url',
	`parent_id` int(11) default 0 comment '父菜单 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

drop table if exists tbl_pageelement;
create table `tbl_pageelement`(
	`id` int(11) not null AUTO_INCREMENT comment '页码元素 id',
	`element_name` varchar(100) not null comment '元素名称',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面元素表';

drop table if exists tbl_file;
create table `tbl_file`(
	`id` int(11) not null AUTO_INCREMENT comment '文件 id',
	`file_name` varchar(50) not null comment '文件名称',
	`file_url` varchar(100) default null comment '文件路径',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件表';

drop table if exists tbl_power;
create table `tbl_power`(
	`id` int(11) not null AUTO_INCREMENT comment '权限 id',
	`power_name` varchar(100) not null comment '权限名称',
	`power_type` varchar(50) not null comment '权限类型',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

drop table if exists tbl_power_menu;
create table `tbl_power_menu`(
	`id` int(11) not null AUTO_INCREMENT,
	`power_id` int(11) not null comment '权限 id',
	`menu_id` int(11) not null comment '菜单 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限与菜单关联表';

drop table if exists tbl_power_page;
create table `tbl_power_page`(
	`id` int(11) not null AUTO_INCREMENT,
	`power_id` int(11) not null comment '权限 id',
	`page_id` int(11) not null comment '页面元素 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限与页面元素关联表';

drop table if exists tbl_power_file;
create table `tbl_power_file`(
	`id` int(11) not null AUTO_INCREMENT,
	`power_id` int(11) not null comment '权限 id',
	`file_id` int(11) not null comment '文件 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限与文件关联表';

drop table if exists tbl_operation;
create table `tbl_operation`(
	`id` int(11) not null AUTO_INCREMENT comment '功能 id',
	`operation_name` varchar(50) not null comment '操作名称',
	`operation_code` varchar(50) not null comment '操作编码',
	`prefix_url` varchar(100) default null comment '拦截的 url 前缀',
	`parent_id` int(11) not null comment '父操作 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='功能操作表';

drop table if exists tbl_power_operation;
create table `tbl_power_operation`(
	`id` int(11) not null AUTO_INCREMENT,
	`power_id` int(11) not null comment '权限 id',
	`operation_id` int(11) not null comment '操作 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限与功能操作关联表';

drop table if exists tbl_role_power;
create table `tbl_role_power`(
	`id` int(11) not null AUTO_INCREMENT comment '用户 id',
	`role_id` int(11) not null comment '角色 id',
	`power_id` int(11) not null comment '权限 id',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与权限关联表';

drop table if exists tbl_operation_log;
create table `tbl_operation_log`(
	`id` int(11) not null AUTO_INCREMENT,
	`operation_id` int(11) not null comment '操作 id',
	`user_id` int(11) not null comment '用户 id',
	`operation_content` varchar(50) comment '操作内容',
	`create_date` datetime not null comment '创建时间',
	`update_date` datetime not null comment '更新时间',
	primary key (`id`)
)engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';