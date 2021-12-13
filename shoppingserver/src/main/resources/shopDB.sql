/*这是数据库的语句文件  */



create table user (   /*创建用户表*/
    user_Id INTEGER primary key ,
    username varchar(50) ,
    password varchar(30),
    nickname varchar(50),
    realname varchar(50),
    user_Img varchar(50),
    user_mobile varchar(50),
    user_email varchar(50),
    user_sex varchar(50),
    user_birth DATETIME,      /*生日与注册时间*/
    user_regtime DATETIME,
    isAdmin boolean,      /*p判断是否为管理员*/
    isShopKeeper boolean , /*判断是否为店家*/
    isVIP boolean /*判断是否为会员*/
);

create table store(      /*商家的店铺信息*/
    store_id INTEGER primary key ,
    keeper_id INTEGER ,
    store_name varchar(50),
    keeper_name varchar(50),
    store_description varchar(50)
);

create table product( /*商家的物品*/
    product_id INTEGER PRIMARY KEY ,
    product_name varchar(50),
    category_id INTEGER(50) ,
    root_category_id INTEGER(50),
    sold_num INTEGER,
    product_status INTEGER,
    content varchar(50)

);
drop table product;

DROP TABLE IF EXISTS `product_params`;
CREATE TABLE `product_params`  (
  `param_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品参数id',
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `product_place` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产地 产地，例：中国江苏',
  `foot_period` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保质期 保质期，例：180天',
  `brand` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌名 品牌名，例：三只大灰狼',
  `factory_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生产厂名 生产厂名，例：大灰狼工厂',
  `factory_address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生产厂址 生产厂址，例：大灰狼生产基地',
  `packaging_method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '包装方式 包装方式，例：袋装',
  `weight` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格重量 规格重量，例：35g',
  `storage_method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '存储方法 存储方法，例：常温5~25°',
  `eat_method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '食用方式 食用方式，例：开袋即食',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`param_id`) USING BTREE
) ;









