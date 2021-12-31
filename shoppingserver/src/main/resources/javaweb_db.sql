/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.6.50-log : Database - db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 分类id主键',
  `category_name` varchar(32) NOT NULL COMMENT '分类名称 分类名称',
  `category_level` int(11) NOT NULL COMMENT '分类层级 分类得类型，\n1:一级大分类\n2:二级分类\n3:三级小分类',
  `parent_id` int(11) NOT NULL COMMENT '父层级id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级',
  `category_icon` varchar(64) DEFAULT NULL COMMENT '图标 logo',
  `category_slogan` varchar(64) DEFAULT NULL COMMENT '口号',
  `category_pic` varchar(64) DEFAULT NULL COMMENT '分类图',
  `category_bg_color` varchar(32) DEFAULT NULL COMMENT '背景颜色',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品分类';

/*Data for the table `category` */

insert  into `category`(`category_id`,`category_name`,`category_level`,`parent_id`,`category_icon`,`category_slogan`,`category_pic`,`category_bg_color`) values 
(1,'手机',1,0,'cake.png','手机','act1.png','black'),
(2,'饼干/膨化',1,0,'cookies.png','健康饮食远离我','act2.png','black'),
(3,'熟食/肉类',1,0,'meat.png','nice！nice！nice！','act3.png','black'),
(4,'素食/卤味',1,0,'bamboo.png',NULL,'act1.png','black'),
(5,'坚果/炒货',1,0,'nut.png','酥酥脆脆，回味无穷','act2.png','black'),
(6,'糖果/蜜饯',1,0,'candy.png',NULL,'act3.png','black'),
(7,'巧克力',1,0,'chocolate.png',NULL,'act1.png','black'),
(8,'海味/河鲜',1,0,'fish.png',NULL,'act2.png','black'),
(9,'花茶/果茶',1,0,'tea.png','健康快乐每一天','act3.png','black'),
(10,'品牌/礼包',1,0,'package.png','每日一个小惊喜','act1.png','black');

/*Table structure for table `index_img` */

DROP TABLE IF EXISTS `index_img`;

CREATE TABLE `index_img` (
  `img_id` varchar(64) NOT NULL COMMENT '主键',
  `img_url` varchar(128) NOT NULL COMMENT '图片 图片地址',
  `img_bg_color` varchar(32) DEFAULT NULL COMMENT '背景色 背景颜色',
  `prod_id` varchar(64) DEFAULT NULL COMMENT '商品id 商品id',
  `category_id` varchar(64) DEFAULT NULL COMMENT '商品分类id 商品分类id',
  `index_type` int(11) DEFAULT NULL COMMENT '轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类',
  `seq` int(11) DEFAULT NULL COMMENT '轮播图展示顺序 轮播图展示顺序，从小到大',
  `status` int(11) DEFAULT NULL COMMENT '是否展示:1表示正常显示，0表示下线 是否展示，1：展示    0：不展示',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间 创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间 更新',
  PRIMARY KEY (`img_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='轮播图 ';

/*Data for the table `index_img` */

insert  into `index_img`(`img_id`,`img_url`,`img_bg_color`,`prod_id`,`category_id`,`index_type`,`seq`,`status`,`create_time`,`update_time`) values 
('1','ad1.jpg','green','101',NULL,1,1,1,'2021-04-14 17:21:13','2021-04-14 17:21:18'),
('3','ad3.jpg','black','103',NULL,1,3,1,'2021-04-14 17:22:23','2021-04-14 17:22:27'),
('5','ad5.jpg','yellow','101',NULL,1,2,0,'2021-04-24 09:34:01','2021-04-25 09:34:05');

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `item_id` varchar(64) NOT NULL COMMENT '订单项ID',
  `order_id` varchar(64) NOT NULL COMMENT '订单ID',
  `product_id` varchar(64) NOT NULL COMMENT '商品ID',
  `product_name` varchar(32) NOT NULL COMMENT '商品名称',
  `product_img` varchar(32) DEFAULT NULL COMMENT '商品图片',
  `sku_id` varchar(128) NOT NULL COMMENT 'skuID',
  `sku_name` varchar(32) DEFAULT NULL COMMENT 'sku名称',
  `product_price` decimal(32,8) NOT NULL COMMENT '商品价格',
  `buy_counts` int(11) NOT NULL COMMENT '购买数量',
  `total_amount` decimal(32,8) DEFAULT NULL COMMENT '商品总金额',
  `basket_date` datetime DEFAULT NULL COMMENT '加入购物车时间',
  `buy_time` datetime DEFAULT NULL COMMENT '购买时间',
  `is_comment` int(11) DEFAULT NULL COMMENT '评论状态： 0 未评价  1 已评价',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单项/快照 ';

/*Data for the table `order_item` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `order_id` varchar(64) NOT NULL COMMENT '订单ID 同时也是订单编号',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `untitled` varchar(32) DEFAULT NULL COMMENT '产品名称（多个产品用,隔开）',
  `receiver_name` varchar(32) NOT NULL COMMENT '收货人快照',
  `receiver_mobile` varchar(32) NOT NULL COMMENT '收货人手机号快照',
  `receiver_address` varchar(128) NOT NULL COMMENT '收货地址快照',
  `total_amount` decimal(32,8) DEFAULT NULL COMMENT '订单总价格',
  `actual_amount` int(11) DEFAULT NULL COMMENT '实际支付总价格',
  `pay_type` int(11) DEFAULT NULL COMMENT '支付方式 1:微信 2:支付宝',
  `order_remark` varchar(32) DEFAULT NULL COMMENT '订单备注',
  `status` varchar(32) DEFAULT NULL COMMENT '订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:已完成 6:已关闭',
  `delivery_type` varchar(32) DEFAULT NULL COMMENT '配送方式',
  `delivery_flow_id` varchar(32) DEFAULT NULL COMMENT '物流单号',
  `order_freight` decimal(32,8) DEFAULT '0.00000000' COMMENT '订单运费 默认可以为零，代表包邮',
  `delete_status` int(11) DEFAULT '0' COMMENT '逻辑删除状态 1: 删除 0:未删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间（成交时间）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `flish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `close_type` int(11) DEFAULT NULL COMMENT '订单关闭类型1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单 ';

/*Data for the table `orders` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` varchar(64) NOT NULL COMMENT '商品主键id',
  `product_name` varchar(32) NOT NULL COMMENT '商品名称 商品名称',
  `category_id` int(11) NOT NULL COMMENT '分类外键id 分类id',
  `root_category_id` int(11) NOT NULL COMMENT '一级分类外键id 一级分类id，用于优化查询',
  `sold_num` int(11) NOT NULL COMMENT '销量 累计销售',
  `product_status` int(11) NOT NULL COMMENT '默认是1，表示正常状态, -1表示删除, 0下架 默认是1，表示正常状态, -1表示删除, 0下架',
  `content` text NOT NULL COMMENT '商品内容 商品内容',
  `shop_id` varchar(64) NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表';

/*Data for the table `product` */

insert  into `product`(`product_id`,`product_name`,`category_id`,`root_category_id`,`sold_num`,`product_status`,`content`,`shop_id`) values 
('31746426','aaa',1,0,5,1,'1111','22432443'),
('32187642','a',3,0,0,1,'aaaa','22432443');

/*Table structure for table `product_comments` */

DROP TABLE IF EXISTS `product_comments`;

CREATE TABLE `product_comments` (
  `comm_id` varchar(64) NOT NULL COMMENT 'ID',
  `product_id` varchar(64) NOT NULL COMMENT '商品id',
  `product_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `order_item_id` varchar(64) DEFAULT NULL COMMENT '订单项(商品快照)ID 可为空',
  `user_id` varchar(64) DEFAULT NULL COMMENT '评论用户id 用户名须脱敏',
  `is_anonymous` int(11) DEFAULT NULL COMMENT '是否匿名（1:是，0:否）',
  `comm_type` int(11) DEFAULT NULL COMMENT '评价类型（1好评，0中评，-1差评）',
  `comm_level` int(11) DEFAULT NULL COMMENT '评价等级 1：好评 2：中评 3：差评',
  `comm_content` varchar(128) NOT NULL COMMENT '评价内容',
  `comm_imgs` varchar(32) DEFAULT NULL COMMENT '评价晒图(JSON {img1:url1,img2:url2}  )',
  `sepc_name` datetime DEFAULT NULL COMMENT '评价时间 可为空',
  `reply_status` int(11) DEFAULT NULL COMMENT '是否回复（0:未回复，1:已回复）',
  `reply_content` varchar(32) DEFAULT NULL COMMENT '回复内容',
  `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
  `is_show` int(11) DEFAULT NULL COMMENT '是否显示（1:是，0:否）',
  PRIMARY KEY (`comm_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品评价 ';

/*Data for the table `product_comments` */

insert  into `product_comments`(`comm_id`,`product_id`,`product_name`,`order_item_id`,`user_id`,`is_anonymous`,`comm_type`,`comm_level`,`comm_content`,`comm_imgs`,`sepc_name`,`reply_status`,`reply_content`,`reply_time`,`is_show`) values 
('1','3','咪咪虾条','10000001','4',1,1,1,'我买这不是来吃的，就是玩儿','{}','2021-04-27 14:51:10',0,NULL,'2021-04-27 14:51:32',1),
('1640912432010','31746426',NULL,NULL,'2',NULL,5,NULL,'好！',NULL,NULL,NULL,NULL,'2021-12-31 09:00:32',NULL),
('2','3','咪咪虾条','10000002','5',0,1,1,'nice','{img1:“mmxt2.png”}','2021-04-27 14:53:20',1,'你好我也好','2021-04-27 14:53:37',1),
('3','3','咪咪虾条','10000003','1',1,1,1,'评价内容3','{}','2021-04-27 09:36:36',0,NULL,NULL,1),
('4','3','咪咪虾条','10000004','2',1,1,1,'评价内容3','{}','2021-04-28 09:37:28',0,NULL,NULL,1),
('5','3','咪咪虾条','10000005','2',1,0,1,'评价内容5','{}','2021-04-28 09:38:13',0,NULL,NULL,1),
('6','3','咪咪虾条','10000006','3',1,0,1,'评价内容6','{}','2021-04-21 09:39:16',0,NULL,NULL,1),
('7','3','咪咪虾条','10000007','4',0,-1,1,'评价内容7','{}','2021-04-06 09:40:01',0,NULL,NULL,1);

/*Table structure for table `product_img` */

DROP TABLE IF EXISTS `product_img`;

CREATE TABLE `product_img` (
  `id` varchar(64) NOT NULL COMMENT '图片主键',
  `item_id` varchar(64) NOT NULL COMMENT '商品外键id 商品外键id',
  `url` varchar(128) NOT NULL COMMENT '图片地址 图片地址',
  `sort` int(11) NOT NULL COMMENT '顺序 图片顺序，从小到大',
  `is_main` int(11) NOT NULL COMMENT '是否主图 是否主图，1：是，0：否',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品图片 ';

/*Data for the table `product_img` */

insert  into `product_img`(`id`,`item_id`,`url`,`sort`,`is_main`,`created_time`,`updated_time`) values 
('32983813','28744368','img\\tu_2021123016221332941698.png',1,1,'2021-12-31 08:22:13','2021-12-31 08:22:13'),
('35770967','31746426','img\\tu_2021123016413335746012.png',1,1,'2021-12-31 08:41:34','2021-12-31 08:41:34');

/*Table structure for table `product_params` */

DROP TABLE IF EXISTS `product_params`;

CREATE TABLE `product_params` (
  `param_id` varchar(64) NOT NULL COMMENT '商品参数id',
  `product_id` varchar(32) NOT NULL COMMENT '商品id',
  `product_place` varchar(32) NOT NULL COMMENT '产地 产地，例：中国江苏',
  `foot_period` varchar(32) NOT NULL COMMENT '保质期 保质期，例：180天',
  `brand` varchar(32) NOT NULL COMMENT '品牌名 品牌名，例：三只大灰狼',
  `factory_name` varchar(32) NOT NULL COMMENT '生产厂名 生产厂名，例：大灰狼工厂',
  `factory_address` varchar(32) NOT NULL COMMENT '生产厂址 生产厂址，例：大灰狼生产基地',
  `packaging_method` varchar(32) NOT NULL COMMENT '包装方式 包装方式，例：袋装',
  `weight` varchar(32) NOT NULL COMMENT '规格重量 规格重量，例：35g',
  `storage_method` varchar(32) NOT NULL COMMENT '存储方法 存储方法，例：常温5~25°',
  `eat_method` varchar(32) NOT NULL COMMENT '食用方式 食用方式，例：开袋即食',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品参数 ';

/*Data for the table `product_params` */

insert  into `product_params`(`param_id`,`product_id`,`product_place`,`foot_period`,`brand`,`factory_name`,`factory_address`,`packaging_method`,`weight`,`storage_method`,`eat_method`,`create_time`,`update_time`) values 
('1','3','中国湖北武汉','12个月','咪咪','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35'),
('2','19','中国湖北武汉','12个月','卫龙','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35'),
('3','20','中国湖北武汉','12个月','威龙','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35'),
('4','21','中国湖北武汉','12个月','卫龙','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35'),
('5','22','中国湖北武汉','12个月','威龙','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35'),
('6','23','中国湖北武汉','12个月','老干妈','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35'),
('7','24','中国湖北武汉','12个月','卫龙','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35'),
('8','1','中国湖北武汉','12个月','良品铺子','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35');

/*Table structure for table `product_sku` */

DROP TABLE IF EXISTS `product_sku`;

CREATE TABLE `product_sku` (
  `sku_id` varchar(64) NOT NULL COMMENT 'sku编号',
  `product_id` varchar(64) NOT NULL COMMENT '商品id',
  `sku_name` varchar(32) DEFAULT NULL COMMENT 'sku名称',
  `sku_img` varchar(32) DEFAULT NULL COMMENT 'sku图片',
  `untitled` varchar(400) DEFAULT NULL COMMENT '属性组合（格式是p1:v1;p2:v2）',
  `original_price` int(11) DEFAULT NULL COMMENT '原价',
  `sell_price` int(11) NOT NULL COMMENT '销售价格',
  `discounts` decimal(4,2) DEFAULT NULL COMMENT '折扣力度',
  `stock` int(11) NOT NULL COMMENT '库存',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(11) DEFAULT NULL COMMENT 'sku状态(1启用，0禁用，-1删除)',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计';

/*Data for the table `product_sku` */

insert  into `product_sku`(`sku_id`,`product_id`,`sku_name`,`sku_img`,`untitled`,`original_price`,`sell_price`,`discounts`,`stock`,`create_time`,`update_time`,`status`) values 
('1','3','咪咪虾条 单包 50g','mmxt_1.png','{口味:[\"原味\",\"烧烤\",\"芥末\"],包装:[\"盒装\",\"袋装\"]}',2,1,0.70,316,'2021-04-26 15:58:00','2021-04-25 15:58:00',1),
('10','24','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('11','25','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('12','26','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('13','27','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('14','28','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('15','29','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('2','3','咪咪虾条 6连包','mmxt_1.png','{口味:[\"原味\",\"烧烤\"],包装:[\"新包装\",\"老包装\"]}',6,5,0.60,171,'2021-04-26 14:48:21','2021-04-26 14:48:25',1),
('3','3','咪咪虾条 整箱50包','mmxt_1.png','{口味:[\"原味\",\"烧烤\",\"孜然\"]}',50,40,0.50,37,'2021-04-26 14:49:20','2021-04-26 14:49:23',1),
('31061668','30692436',NULL,NULL,NULL,NULL,10,NULL,100,NULL,NULL,1),
('32138688','31746426',NULL,NULL,NULL,NULL,100,NULL,91,NULL,NULL,1),
('4','1','奥利奥分享装','ala_1.png','{口味:[\"草莓\",\"巧克力\",\"牛奶\",\"原味\"]}',10,8,0.80,137,'2021-04-27 11:38:55','2021-04-27 11:39:00',1),
('5','19','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('6','20','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('67369225','32187642',NULL,NULL,NULL,NULL,100,NULL,100,NULL,NULL,1),
('7','21','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('8','22','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('81444046','3590577',NULL,NULL,NULL,NULL,11,NULL,11,NULL,NULL,1),
('9','23','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,999,'2021-05-10 09:56:09','2021-05-10 09:56:12',1),
('98446027','68569563',NULL,NULL,NULL,NULL,11,NULL,1110,NULL,NULL,1);

/*Table structure for table `shopping_cart` */

DROP TABLE IF EXISTS `shopping_cart`;

CREATE TABLE `shopping_cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` varchar(32) NOT NULL COMMENT '商品ID',
  `sku_id` varchar(32) DEFAULT NULL COMMENT 'skuID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `cart_num` varchar(32) NOT NULL COMMENT '购物车商品数量',
  `cart_time` varchar(32) NOT NULL COMMENT '添加购物车时间',
  `product_price` decimal(32,8) DEFAULT NULL COMMENT '添加购物车时商品价格',
  `sku_props` varchar(255) DEFAULT NULL COMMENT '选择的套餐的属性',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='购物车 ';

/*Data for the table `shopping_cart` */

/*Table structure for table `shoptable` */

DROP TABLE IF EXISTS `shoptable`;

CREATE TABLE `shoptable` (
  `shop_id` varchar(64) NOT NULL,
  `shop_name` varchar(50) NOT NULL,
  `shopkeeper_id` varchar(64) NOT NULL,
  `shopkeeper_name` varchar(40) NOT NULL,
  `shop_description` varchar(200) DEFAULT NULL,
  `shop_img` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shoptable` */

insert  into `shoptable`(`shop_id`,`shop_name`,`shopkeeper_id`,`shopkeeper_name`,`shop_description`,`shop_img`,`status`) values 
('1','好利来1','1','123','经济实惠',NULL,1),
('22432443','好利来','2','admin','好',NULL,1),
('3','好利来3','3','HQF3','经济实惠',NULL,1),
('50085027','好名字','4','aaa','好',NULL,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `user_Img` varchar(50) DEFAULT NULL,
  `user_mobile` varchar(50) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_sex` varchar(50) DEFAULT NULL,
  `user_birth` datetime DEFAULT NULL,
  `user_regtime` datetime DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  `isShopKeeper` tinyint(1) DEFAULT NULL,
  `isVIP` tinyint(1) DEFAULT NULL,
  `user_address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`nickname`,`realname`,`user_Img`,`user_mobile`,`user_email`,`user_sex`,`user_birth`,`user_regtime`,`isAdmin`,`isShopKeeper`,`isVIP`,`user_address`) values 
(1,'123','202cb962ac59075b964b07152d234b70','123','zyy','img/default.png','12345456','1234567@qq.com','男',NULL,NULL,1,1,1,'江西省南昌市南昌大学前湖校区'),
(2,'admin','21232f297a57a5a743894a0e4a801fc3','admin','zyy','img/default.png','12345456','1234567@qq.com','男',NULL,NULL,1,1,1,'江西省南昌市南昌大学前湖校区'),
(3,'111','698d51a19d8a121ce581499d7b701668','111','zyy','img/default.png','12345456','1234567@qq.com','男',NULL,NULL,0,1,0,'江西省南昌市南昌大学前湖校区'),
(4,'222','bcbe3365e6ac95ea2c0343a2395834dd','222','zyy','img/default.png','12345678909','123456@qq.com','男',NULL,NULL,0,1,1,'江西省南昌市南昌大学前湖校区'),
(5,'1111','b59c67bf196a4758191e42f76670ceba','usera4305476c5',NULL,'img/default.png',NULL,NULL,NULL,NULL,NULL,0,0,0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
