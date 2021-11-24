-- `order`.`order` definition

CREATE TABLE `order` (
  `order_id` varchar(100) NOT NULL COMMENT '订单ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `vendor_id` varchar(100) NOT NULL COMMENT '商家ID',
  `store_id` varchar(100) NOT NULL COMMENT '门店ID',
  `phone` varchar(100) NOT NULL COMMENT '收货手机号码',
  `addr` varchar(100) NOT NULL COMMENT '收货地址',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态 0-待支付 1-捡货中 2-已发货 3-已收货 4-已取消',
  `price` bigint(20) NOT NULL COMMENT '实付价格',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '系统版本',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `yn` int(4) NOT NULL DEFAULT '1' COMMENT '删除状态',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';


-- `order`.order_ware definition

CREATE TABLE `order_ware` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `order_id` varchar(100) NOT NULL COMMENT '订单号',
  `ware_code` varchar(100) NOT NULL COMMENT '商品编码',
  `ware_name` varchar(100) NOT NULL COMMENT '商品名称',
  `price` bigint(20) NOT NULL COMMENT '商品价格',
  `count` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `order_ware_order_id_IDX` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品表';


-- `order`.`user` definition

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(100) NOT NULL COMMENT '注册手机号码',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '系统版本',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `yn` int(4) NOT NULL DEFAULT '1' COMMENT '删除状态 -1删除 1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


-- `order`.vendor definition

CREATE TABLE `vendor` (
  `id` varchar(100) NOT NULL COMMENT 'id',
  `vendor_id` varchar(30) NOT NULL COMMENT '商家ID',
  `vendor_name` varchar(100) NOT NULL COMMENT '商家名称',
  `store_id` varchar(100) NOT NULL COMMENT '门店ID',
  `store_name` varchar(100) NOT NULL COMMENT '门店名称',
  `store_addr` varchar(100) NOT NULL COMMENT '门店地址',
  `uuid` varchar(100) NOT NULL COMMENT '商家ID+门店ID 删除加时间戳',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '系统版本',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `yn` int(4) NOT NULL DEFAULT '1' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `vendor_UN` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家信息表';


-- `order`.ware definition

CREATE TABLE `ware` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_id` varchar(100) NOT NULL COMMENT '商家ID',
  `store_id` varchar(100) NOT NULL COMMENT '门店ID',
  `ware_code` varchar(100) NOT NULL COMMENT '商品编码',
  `ware_name` varchar(100) NOT NULL COMMENT '商品名称',
  `price` bigint(20) NOT NULL COMMENT '商品价格 保留到分',
  `uuid` varchar(100) NOT NULL COMMENT '商家ID-门店ID-商品编码 删除后加时间戳',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '系统版本',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `yn` int(4) NOT NULL DEFAULT '1' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ware_UN` (`uuid`),
  KEY `ware_ware_code_IDX` (`ware_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';