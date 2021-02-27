### 学习笔记



##### 建表sql

~~~sql

-- 用户基本信息
drop table  if EXISTS j_user;
CREATE TABLE if not exists `j_user` (
  `id` bigint unsigned NOT NULL COMMENT '用户id',
  `nick_name` varchar (50) NOT NULL COMMENT '昵称',
  `passwrod` varchar (255) NOT NULL COMMENT '密码,加密',
  `default_address_id` bigint unsigned COMMENT '默认地址id，可为空',
  `phone` char (11) NOT NULL COMMENT '手机号',
  `status` char (1) NOT NULL COMMENT '用户状态，0-禁用，1-正常',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar (50) NOT NULL COMMENT '创建人',
  `updaed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar (50) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) COMMENT = "用户基本信息" ENGINE = innodb DEFAULT CHARACTER SET = "utf8mb4" COLLATE = "utf8mb4_general_ci";



--用户收货地址
drop table  if EXISTS j_user_address;
CREATE TABLE if not exists `j_user_address` (
  `id` bigint unsigned NOT NULL COMMENT '地址id',
  `user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `name` varchar (50) NOT NULL COMMENT '收货人姓名',
  `phone` char (11) NOT NULL COMMENT '收货人手机号',
  `province` char(2) not null COMMENT '省份代码值',
  `city` char(4) not null COMMENT '市代码值',
  `region` char(4) not null COMMENT '区域代码值',
  `detail` varchar(256) not null comment '详细地址',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar (50) NOT NULL COMMENT '创建人',
  `updaed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar (50) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) COMMENT = "用户收货地址" ENGINE = innodb DEFAULT CHARACTER SET = "utf8mb4" COLLATE = "utf8mb4_general_ci";


--订单
drop table  if EXISTS j_order;
CREATE TABLE if not exists `j_order` (
  `id` bigint unsigned NOT NULL COMMENT '订单id',
  `harvest_info` varchar (255) NOT NULL COMMENT '收货信息',
  `totalprice` decimal(10,2) NOT NULL COMMENT '订单总价',
  `status` char (1) NOT NULL COMMENT '订单状态，1-待付款，2-待发货，3-待收货，4-待评价，5-退款/售后',
  `payment_time` datetime COMMENT '付款时间',
  `ship_time` datetime COMMENT '发货时间',
  `deal_time` datetime COMMENT '成交时间',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar (50) NOT NULL COMMENT '创建人',
  `updaed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar (50) NOT NULL COMMENT '更新人',
   PRIMARY KEY (`id`)
) COMMENT = "用户订单表" ENGINE = innodb DEFAULT CHARACTER SET = "utf8mb4" COLLATE = "utf8mb4_general_ci";

--订单关联表
drop table  if EXISTS j_order_relation;
CREATE TABLE if not exists `j_order_relation` (
  `id` bigint unsigned NOT NULL COMMENT '主键id',
  `order_id` bigint unsigned NOT NULL COMMENT '订单id',
  `user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `goods_id` bigint unsigned NOT NULL COMMENT '商品id',
  `category_id` bigint unsigned not null comment '类目id',
  `name` varchar (255) NOT NULL COMMENT '商品名称',
  `goods_count` int unsigned NOT NULL COMMENT '商品数量',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `img_url` varchar (255) NOT NULL COMMENT '商品图片地址',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar (50) NOT NULL COMMENT '创建人',
  `updaed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar (50) NOT NULL COMMENT '更新人',
   PRIMARY KEY (`id`)
) COMMENT = "订单关联表" ENGINE = innodb DEFAULT CHARACTER SET = "utf8mb4" COLLATE = "utf8mb4_general_ci";



-- 交易快照
drop table  if EXISTS j_transaction_snapshot;
CREATE TABLE if not exists `j_transaction_snapshot` (
  `id` bigint unsigned NOT NULL COMMENT 'id',
  `order_id` bigint unsigned NOT NULL COMMENT '订单id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar (50) NOT NULL COMMENT '创建人',
  `updaed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar (50) NOT NULL COMMENT '更新人',
   PRIMARY KEY (`id`)
) COMMENT = "交易快照" ENGINE = innodb DEFAULT CHARACTER SET = "utf8mb4" COLLATE = "utf8mb4_general_ci";


-- 交易快照和商品关联表
drop table  if EXISTS j_ts_goods;
CREATE TABLE if not exists `j_ts_goods` (
  `id` bigint unsigned NOT NULL COMMENT 'id',
  `snapshot_id` bigint unsigned NOT NULL COMMENT '快照id',
  `goods_id` bigint unsigned NOT NULL COMMENT '商品id',
  `category_id` bigint unsigned not null comment '类目id',
  `name` varchar (255) NOT NULL COMMENT '商品名称',
  `goods_count` int unsigned NOT NULL COMMENT '商品数量',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `img_url` varchar (255) NOT NULL COMMENT '商品图片地址',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar (50) NOT NULL COMMENT '创建人',
  `updaed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar (50) NOT NULL COMMENT '更新人',
   PRIMARY KEY (`id`)
) COMMENT = "交易快照和商品关联表" ENGINE = innodb DEFAULT CHARACTER SET = "utf8mb4" COLLATE = "utf8mb4_general_ci";


-- 商品类目
drop table  if EXISTS j_goods_category;
CREATE TABLE if not exists `j_goods_category` (
  `id` bigint unsigned NOT NULL COMMENT '类目id',
  `p_id` bigint unsigned not null comment '父类目id',
  `name` varchar (255) NOT NULL COMMENT '类目名称',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar (50) NOT NULL COMMENT '创建人',
  `updaed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar (50) NOT NULL COMMENT '更新人',
   PRIMARY KEY (`id`)
) COMMENT = "商品类目" ENGINE = innodb DEFAULT CHARACTER SET = "utf8mb4" COLLATE = "utf8mb4_general_ci";

-- 商品
drop table  if EXISTS j_goods;
CREATE TABLE if not exists `j_goods` (
  `id` bigint unsigned NOT NULL COMMENT '商品id',
  `category_id` bigint unsigned not null comment '类目id',
  `name` varchar (255) NOT NULL COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `img_url` varchar (255) NOT NULL COMMENT '商品图片地址',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar (50) NOT NULL COMMENT '创建人',
  `updaed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar (50) NOT NULL COMMENT '更新人',
   PRIMARY KEY (`id`)
) COMMENT = "商品表" ENGINE = innodb DEFAULT CHARACTER SET = "utf8mb4" COLLATE = "utf8mb4_general_ci";

-- 商品库存
drop table  if EXISTS j_goods_stock;
CREATE TABLE if not exists `j_goods_stock` (
  `id` bigint unsigned NOT NULL COMMENT '主键id',
  `goods_id` bigint unsigned not null comment '商品id',
  `goods_total` bigint unsigned NOT NULL COMMENT '商品总量',
  `goods_saled` bigint unsigned NOT NULL COMMENT '商品已售总量',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar (50) NOT NULL COMMENT '创建人',
  `updaed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar (50) NOT NULL COMMENT '更新人',
   PRIMARY KEY (`id`)
) COMMENT = "商品库存" ENGINE = innodb DEFAULT CHARACTER SET = "utf8mb4" COLLATE = "utf8mb4_general_ci";



~~~

