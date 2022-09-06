/*
 Navicat Premium Data Transfer

 Source Server         : shopping
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 121.40.110.36:3306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 04/01/2022 14:53:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 分类id主键',
  `category_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称 分类名称',
  `category_level` int(11) NOT NULL COMMENT '分类层级 分类得类型，\n1:一级大分类\n2:二级分类\n3:三级小分类',
  `parent_id` int(11) NOT NULL COMMENT '父层级id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级',
  `category_icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标 logo',
  `category_slogan` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '口号',
  `category_pic` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类图',
  `category_bg_color` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '背景颜色',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '手机', 1, 0, 'cake.png', '手机', 'act1.png', 'black');
INSERT INTO `category` VALUES (2, '饼干/膨化', 1, 0, 'cookies.png', '健康饮食远离我', 'act2.png', 'black');
INSERT INTO `category` VALUES (3, '熟食/肉类', 1, 0, 'meat.png', 'nice！nice！nice！', 'act3.png', 'black');
INSERT INTO `category` VALUES (4, '素食/卤味', 1, 0, 'bamboo.png', NULL, 'act1.png', 'black');
INSERT INTO `category` VALUES (5, '坚果/炒货', 1, 0, 'nut.png', '酥酥脆脆，回味无穷', 'act2.png', 'black');
INSERT INTO `category` VALUES (6, '糖果/蜜饯', 1, 0, 'candy.png', NULL, 'act3.png', 'black');
INSERT INTO `category` VALUES (7, '巧克力', 1, 0, 'chocolate.png', NULL, 'act1.png', 'black');
INSERT INTO `category` VALUES (8, '海味/河鲜', 1, 0, 'fish.png', NULL, 'act2.png', 'black');
INSERT INTO `category` VALUES (9, '花茶/果茶', 1, 0, 'tea.png', '健康快乐每一天', 'act3.png', 'black');
INSERT INTO `category` VALUES (10, '品牌/礼包', 1, 0, 'package.png', '每日一个小惊喜', 'act1.png', 'black');

-- ----------------------------
-- Table structure for index_img
-- ----------------------------
DROP TABLE IF EXISTS `index_img`;
CREATE TABLE `index_img`  (
  `img_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `img_url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片 图片地址',
  `img_bg_color` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '背景色 背景颜色',
  `prod_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id 商品id',
  `category_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类id 商品分类id',
  `index_type` int(11) NULL DEFAULT NULL COMMENT '轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类',
  `seq` int(11) NULL DEFAULT NULL COMMENT '轮播图展示顺序 轮播图展示顺序，从小到大',
  `status` int(11) NULL DEFAULT NULL COMMENT '是否展示:1表示正常显示，0表示下线 是否展示，1：展示    0：不展示',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间 创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间 更新',
  PRIMARY KEY (`img_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '轮播图 ' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of index_img
-- ----------------------------
INSERT INTO `index_img` VALUES ('18699339', 'img/O1CN01OeXvhT1vJCvornx7d_!!6000000006151-0-octopus_2021123101161118680371.jpg', NULL, '19561127', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `index_img` VALUES ('66981602', 'img/O1CN01zUGonn24NdIP2SHVq_!!6000000007379-0-octopus_2021123101154666976889.jpg', NULL, '62087398', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `index_img` VALUES ('67729381', 'img/O1CN01ApeetX1Etp7DebjkK_!!6000000000410-0-octopus_2021123101165667715550.jpg', NULL, '87006304', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单项ID',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `product_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID',
  `product_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_img` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `sku_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'skuID',
  `sku_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sku名称',
  `product_price` decimal(32, 8) NOT NULL COMMENT '商品价格',
  `buy_counts` int(11) NOT NULL COMMENT '购买数量',
  `total_amount` decimal(32, 8) NULL DEFAULT NULL COMMENT '商品总金额',
  `basket_date` datetime NULL DEFAULT NULL COMMENT '加入购物车时间',
  `buy_time` datetime NULL DEFAULT NULL COMMENT '购买时间',
  `is_comment` int(11) NULL DEFAULT NULL COMMENT '评论状态： 0 未评价  1 已评价',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单项/快照 ' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('164095092252969179', '87a12c3dba8d4b2ea007bf8a1f568f33', '52363235', 'OPPO Find N', NULL, '94828632', NULL, 8999.00000000, 1, 8999.00000000, '2021-12-31 19:42:03', '2021-12-31 19:42:03', 0);
INSERT INTO `order_item` VALUES ('164095856004462497', 'ec5f5bf1ead74148b10d116cc0c0d872', '19561127', '小米12 Pro', NULL, '69466429', NULL, 4999.00000000, 1, 4999.00000000, '2021-12-31 21:49:20', '2021-12-31 21:49:20', 0);
INSERT INTO `order_item` VALUES ('164104350520466011', '38a505a6733a4f5898bddc66bb92976b', '18554393', '三星 Galaxy Z Fold3', 'img/三星_2021123100491225618805.jp', '23010839', NULL, 14799.00000000, 1, 14799.00000000, '2022-01-01 21:25:05', '2022-01-01 21:25:05', 0);
INSERT INTO `order_item` VALUES ('164109378148510091', 'f7dbea4bc0d246ae867029eb75ebccd2', '19561127', '小米12 Pro', 'img/xiaomi.jpg__2021123100555771', '69466429', NULL, 4999.00000000, 1, 4999.00000000, '2022-01-02 11:23:01', '2022-01-02 11:23:01', 0);
INSERT INTO `order_item` VALUES ('164109378150619079', 'f7dbea4bc0d246ae867029eb75ebccd2', '66232947', '荣耀 Magic3 至臻版', 'img/magic.jpg__20211231005317785', '76620727', NULL, 6999.00000000, 1, 6999.00000000, '2022-01-02 11:23:02', '2022-01-02 11:23:02', 0);
INSERT INTO `order_item` VALUES ('164125699007587866', 'ca1e9b98999b444ba0030d826dbf0e35', '8653137', '三只松鼠每日坚果', 'img/7aa9c2fad2d8275b_20220103164', '8787477', NULL, 149.00000000, 1, 149.00000000, '2022-01-04 08:43:10', '2022-01-04 08:43:10', 0);
INSERT INTO `order_item` VALUES ('164126219867167322', '81fc8c2143ce4aec8f6bc7bd682c3ee6', '8653137', '三只松鼠每日坚果', 'img/7aa9c2fad2d8275b_20220103164', '60964669', NULL, 149.00000000, 10000, 1490000.00000000, '2022-01-04 10:09:59', '2022-01-04 10:09:59', 0);
INSERT INTO `order_item` VALUES ('164126479313670275', 'ef5f8dde6a064c2fa5d90b061e48e5ab', '8653137', '三只松鼠每日坚果', 'img/7aa9c2fad2d8275b_20220103164', '6037983', NULL, 149.00000000, 50, 7450.00000000, '2022-01-04 10:53:13', '2022-01-04 10:53:13', 0);
INSERT INTO `order_item` VALUES ('164126485226096827', '1bced483a35447e584b29178a43efc97', '8653137', '三只松鼠每日坚果', 'img/7aa9c2fad2d8275b_20220103164', '6037983', NULL, 149.00000000, 10000, 1490000.00000000, '2022-01-04 10:54:12', '2022-01-04 10:54:12', 0);
INSERT INTO `order_item` VALUES ('164126686721931350', '61024619acc046b2882a951a7c680af5', '8653137', '三只松鼠每日坚果', NULL, '6037983', NULL, 149.00000000, 1, 149.00000000, '2022-01-04 11:27:47', '2022-01-04 11:27:47', 0);
INSERT INTO `order_item` VALUES ('164127466854143054', '2a510aab864440ada4f064a9f173f3b7', '66232947', '荣耀 Magic3 至臻版', NULL, '76620727', NULL, 6999.00000000, 1, 6999.00000000, '2022-01-04 13:37:49', '2022-01-04 13:37:49', 0);
INSERT INTO `order_item` VALUES ('164127682090562882', '5f7e7e83e8974603ae35c2d0099a8b85', '6432001', '手机', 'img/hai_dai_20220103221220941251', '84070005', NULL, 1000.00000000, 10, 10000.00000000, '2022-01-04 14:13:41', '2022-01-04 14:13:41', 0);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID 同时也是订单编号',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `untitled` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称（多个产品用,隔开）',
  `receiver_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人快照',
  `receiver_mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人手机号快照',
  `receiver_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址快照',
  `total_amount` decimal(32, 8) NULL DEFAULT NULL COMMENT '订单总价格',
  `actual_amount` int(11) NULL DEFAULT NULL COMMENT '实际支付总价格',
  `pay_type` int(11) NULL DEFAULT NULL COMMENT '支付方式 1:微信 2:支付宝',
  `order_remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:已完成 6:已关闭',
  `delivery_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送方式',
  `delivery_flow_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流单号',
  `order_freight` decimal(32, 8) NULL DEFAULT 0.00000000 COMMENT '订单运费 默认可以为零，代表包邮',
  `delete_status` int(11) NULL DEFAULT 0 COMMENT '逻辑删除状态 1: 删除 0:未删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间（成交时间）',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '付款时间',
  `delivery_time` datetime NULL DEFAULT NULL COMMENT '发货时间',
  `flish_time` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `close_type` int(11) NULL DEFAULT NULL COMMENT '订单关闭类型1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单 ' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1bced483a35447e584b29178a43efc97', '13', '三只松鼠每日坚果,', 'zxf', '17879632526', '南昌', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, '2022-01-04 10:54:12', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `orders` VALUES ('2a510aab864440ada4f064a9f173f3b7', '2', '荣耀 Magic3 至臻版', 'zyy', '13188888888', '江西省南昌市南昌大学前湖校区', NULL, NULL, NULL, NULL, '3', NULL, NULL, NULL, NULL, '2022-01-04 13:37:49', NULL, NULL, '2022-01-04 13:38:08', '2022-01-04 13:38:14', NULL, NULL);
INSERT INTO `orders` VALUES ('38a505a6733a4f5898bddc66bb92976b', '2', '三星 Galaxy Z Fold3,', 'zyy', '12345456', '江西省南昌市南昌大学前湖校区', NULL, NULL, NULL, NULL, '3', NULL, NULL, NULL, NULL, '2022-01-01 21:25:05', NULL, NULL, '2022-01-01 23:08:52', '2022-01-04 12:09:14', NULL, NULL);
INSERT INTO `orders` VALUES ('5f7e7e83e8974603ae35c2d0099a8b85', '9', '手机', 'gjl', '13217065163', '南昌市南昌大学', NULL, NULL, NULL, NULL, '3', NULL, NULL, NULL, NULL, '2022-01-04 14:13:41', NULL, NULL, '2022-01-04 14:14:01', '2022-01-04 14:14:19', NULL, NULL);
INSERT INTO `orders` VALUES ('61024619acc046b2882a951a7c680af5', '9', '三只松鼠每日坚果', 'gjl', '13217065163', '南昌市南昌大学', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, '2022-01-04 11:27:47', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `orders` VALUES ('81fc8c2143ce4aec8f6bc7bd682c3ee6', '13', '三只松鼠每日坚果,', 'zxf', '17879632526', '南昌', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, '2022-01-04 10:09:59', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `orders` VALUES ('87a12c3dba8d4b2ea007bf8a1f568f33', '4', 'OPPO Find N', 'zyy', '12345678909', '江西省南昌市南昌大学前湖校区', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, '2021-12-31 19:42:03', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `orders` VALUES ('ca1e9b98999b444ba0030d826dbf0e35', '2', '三只松鼠每日坚果,', 'zyy', '13188888888', '江西省南昌市南昌大学前湖校区', NULL, NULL, NULL, NULL, '3', NULL, NULL, NULL, NULL, '2022-01-04 08:43:10', NULL, NULL, '2022-01-04 08:43:58', '2022-01-04 08:44:14', NULL, NULL);
INSERT INTO `orders` VALUES ('ec5f5bf1ead74148b10d116cc0c0d872', '9', '小米12 Pro', 'gjl', '13217065163', '南昌市南昌大学', NULL, NULL, NULL, NULL, '3', NULL, NULL, NULL, NULL, '2021-12-31 21:49:20', NULL, NULL, '2021-12-31 21:49:59', '2021-12-31 21:50:08', NULL, NULL);
INSERT INTO `orders` VALUES ('ef5f8dde6a064c2fa5d90b061e48e5ab', '13', '三只松鼠每日坚果,', 'zxf', '17879632526', '南昌', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, '2022-01-04 10:53:13', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `orders` VALUES ('f7dbea4bc0d246ae867029eb75ebccd2', '2', '小米12 Pro,荣耀 Magic3 至臻版,', 'zyy', '12345456', '江西省南昌市南昌大学前湖校区', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, '2022-01-02 11:23:01', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品主键id',
  `product_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称 商品名称',
  `category_id` int(11) NOT NULL COMMENT '分类外键id 分类id',
  `root_category_id` int(11) NOT NULL COMMENT '一级分类外键id 一级分类id，用于优化查询',
  `sold_num` int(11) NOT NULL COMMENT '销量 累计销售',
  `product_status` int(11) NOT NULL COMMENT '默认是1，表示正常状态, -1表示删除, 0下架 默认是1，表示正常状态, -1表示删除, 0下架',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品内容 商品内容',
  `shop_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('16302465', '玫瑰花茶', 9, 0, 0, 1, '北京同仁堂玫瑰花茶 中华老字号', '50085027');
INSERT INTO `product` VALUES ('18554393', '三星 Galaxy Z Fold3', 1, 0, 5, 1, '屏下摄像折叠屏 双模5G手机 Spen书写 IPX8防水', '22432443');
INSERT INTO `product` VALUES ('19561127', '小米12 Pro', 1, 0, 3, 1, '骁龙8 Gen 2K AMOLED 120Hz高刷 5000万超清三主摄', '22432443');
INSERT INTO `product` VALUES ('25031649', '优乐麦干烙蛋糕', 2, 0, 0, 1, '优乐麦干烙蛋糕饼干3斤整箱8斤干酪金典鸡蛋芝麻煎饼散装零食品\n', '3');
INSERT INTO `product` VALUES ('25959471', 'iPhone 13 Pro Max', 1, 0, 0, 1, 'iPhone 13 Pro 强得很', '22432443');
INSERT INTO `product` VALUES ('26990382', '牛肉干', 3, 0, 0, 1, '大片真牛肉 鲜香嚼着爽 有嚼劲不干硬', '50085027');
INSERT INTO `product` VALUES ('2840370', '内蒙古酱牛肉', 3, 0, 1, 1, '内蒙古酱牛肉五香卤味熟食牛腱真空开袋即食健身代餐熟食酱牛肉\n', '3');
INSERT INTO `product` VALUES ('34793911', '青豌豆', 5, 0, 0, 1, '颗粒饱满、颗颗入味、回味悠久', '61949229');
INSERT INTO `product` VALUES ('37437797', '河鲜', 8, 0, 0, 1, '江南特产苏州三白白鱼白虾银鱼小鱼干货鱼干年货鱼类零食品河鲜\n', '1');
INSERT INTO `product` VALUES ('42122875', '麻辣', 4, 0, 0, 1, '卤味素食麻辣大礼包散混装整箱小吃湖南休闲充饥夜宵熟食零食\n', '3');
INSERT INTO `product` VALUES ('52363235', 'OPPO Find N', 1, 0, 1, 1, '5G新品 折叠旗舰 全网通', '22432443');
INSERT INTO `product` VALUES ('54170782', '茶叶', 9, 0, 0, 1, '蜜桃白桃乌龙茶绿茶茶包花茶组合養生茶叶冷泡茶袋泡水果茶小包', '1');
INSERT INTO `product` VALUES ('6033768', '百草味-坚果', 5, 0, 0, 1, '百草味-坚果大礼包1600g/9袋混合干果礼盒年货休闲零食整箱送礼\n', '50085027');
INSERT INTO `product` VALUES ('62087398', '坚果大礼包', 10, 0, 0, 1, '春节年货礼盒装定制每日坚果大礼包过新年送礼品置办走亲戚送长辈\n', '1');
INSERT INTO `product` VALUES ('63031777', '大白兔奶糖', 6, 0, 0, 1, '严选原料 浓香醇厚 嚼劲十足', '50085027');
INSERT INTO `product` VALUES ('6432001', '手机', 1, 0, 1, 1, '好', '32554047');
INSERT INTO `product` VALUES ('64967901', '8848钛金手机M6', 1, 0, 0, 1, '旗舰新品 加密 轻奢 商务 全网通 双卡双待', '22432443');
INSERT INTO `product` VALUES ('66232947', '荣耀 Magic3 至臻版', 1, 0, 3, 1, '多主摄计算摄影 骁龙888Plus 纳米微晶面板', '22432443');
INSERT INTO `product` VALUES ('71662231', '进口混合坚果礼盒装', 10, 0, 0, 1, '美荻斯进口混合坚果礼盒装高档木箱干果大礼包春节送礼品年货团购', '1');
INSERT INTO `product` VALUES ('75436070', '实木提篮', 10, 0, 0, 1, '新年年货礼盒套装送礼实木提篮每日坚果大礼包定制年会礼品置办\n', '1');
INSERT INTO `product` VALUES ('77885075', '红黑巧克力', 7, 0, 0, 1, '俄罗斯网红黑巧克力纯可可脂每日黑巧苦巧原装进口食品袋装零食\n', '50085027');
INSERT INTO `product` VALUES ('79875193', '雪海梅乡果干果脯酸梅干', 6, 0, 0, 1, '雪海梅乡果干果脯酸梅干10口味500g话梅干清平乐蜜饯休闲零食梅果\n', '50085027');
INSERT INTO `product` VALUES ('80836525', 'vivo X70 Pro', 1, 0, 1, 1, '5nm旗舰芯片 专业影像芯片V1 蔡司光学镜头 120Hz高刷', '22432443');
INSERT INTO `product` VALUES ('81759814', 'aa', 7, 0, 2, 1, 'aa', '22432443');
INSERT INTO `product` VALUES ('86223418', '混合坚果礼盒装', 10, 0, 0, 1, '混合坚果礼盒装送长辈团购定制logo组过新春节年货大礼包品走亲戚\n', '1');
INSERT INTO `product` VALUES ('8653137', '三只松鼠每日坚果', 5, 0, 17, 1, '750g/30袋 年货零食大礼包坚果礼盒送礼儿童孕妇混合干果腰果夏威夷果核桃仁开心果巴旦木', '38872933');
INSERT INTO `product` VALUES ('87006304', '绝味鸭脖', 4, 0, 1, 1, '绝味鸭脖 麻辣卤鸭脖 鲜货套餐 休闲零食', '50085027');
INSERT INTO `product` VALUES ('95595959', 'HUAWEI P50 Pocket', 1, 0, 2, 1, '超光谱影像系统 创新双屏操作体验', '22432443');

-- ----------------------------
-- Table structure for product_comments
-- ----------------------------
DROP TABLE IF EXISTS `product_comments`;
CREATE TABLE `product_comments`  (
  `comm_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `product_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `order_item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单项(商品快照)ID 可为空',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论用户id 用户名须脱敏',
  `is_anonymous` int(11) NULL DEFAULT NULL COMMENT '是否匿名（1:是，0:否）',
  `comm_type` int(11) NULL DEFAULT NULL COMMENT '评价类型（1好评，0中评，-1差评）',
  `comm_level` int(11) NULL DEFAULT NULL COMMENT '评价等级 1：好评 2：中评 3：差评',
  `comm_content` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价内容',
  `comm_imgs` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价晒图(JSON {img1:url1,img2:url2}  )',
  `sepc_name` datetime NULL DEFAULT NULL COMMENT '评价时间 可为空',
  `reply_status` int(11) NULL DEFAULT NULL COMMENT '是否回复（0:未回复，1:已回复）',
  `reply_content` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复内容',
  `reply_time` datetime NULL DEFAULT NULL COMMENT '回复时间',
  `is_show` int(11) NULL DEFAULT NULL COMMENT '是否显示（1:是，0:否）',
  PRIMARY KEY (`comm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品评价 ' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of product_comments
-- ----------------------------
INSERT INTO `product_comments` VALUES ('1', '3', '咪咪虾条', '10000001', '4', 1, 1, 1, '我买这不是来吃的，就是玩儿', '{}', '2021-04-27 14:51:10', 0, NULL, '2021-04-27 14:51:32', 1);
INSERT INTO `product_comments` VALUES ('1640958406397', '19561127', NULL, NULL, '2', NULL, 5, NULL, '非常好！！！', NULL, NULL, NULL, NULL, '2021-12-31 21:46:46', NULL);
INSERT INTO `product_comments` VALUES ('1640958623994', '19561127', NULL, NULL, '9', NULL, 3, NULL, '不错', NULL, NULL, NULL, NULL, '2021-12-31 21:50:24', NULL);
INSERT INTO `product_comments` VALUES ('1641257089413', '8653137', NULL, NULL, '2', NULL, 5, NULL, '哦口感很好吃，干湿分离，里面的干果很脆，益生菌的，酸酸甜甜的，小孩子也喜欢吃。蔓越莓干，和蓝莓干都好吃，包装很好，物流很快，快递公司送货上门。棒棒哒！', NULL, NULL, NULL, NULL, '2022-01-04 08:44:49', NULL);
INSERT INTO `product_comments` VALUES ('1641269411288', '18554393', NULL, NULL, '2', NULL, 4, NULL, '不错!', NULL, NULL, NULL, NULL, '2022-01-04 12:10:11', NULL);
INSERT INTO `product_comments` VALUES ('1641274707132', '66232947', NULL, NULL, '2', NULL, 5, NULL, '好！！！', NULL, NULL, NULL, NULL, '2022-01-04 13:38:27', NULL);
INSERT INTO `product_comments` VALUES ('1641276880760', '6432001', NULL, NULL, '9', NULL, 5, NULL, '很好', NULL, NULL, NULL, NULL, '2022-01-04 14:14:41', NULL);
INSERT INTO `product_comments` VALUES ('2', '3', '咪咪虾条', '10000002', '5', 0, 1, 1, 'nice', '{img1:“mmxt2.png”}', '2021-04-27 14:53:20', 1, '你好我也好', '2021-04-27 14:53:37', 1);
INSERT INTO `product_comments` VALUES ('3', '3', '咪咪虾条', '10000003', '1', 1, 1, 1, '评价内容3', '{}', '2021-04-27 09:36:36', 0, NULL, NULL, 1);
INSERT INTO `product_comments` VALUES ('4', '3', '咪咪虾条', '10000004', '2', 1, 1, 1, '评价内容3', '{}', '2021-04-28 09:37:28', 0, NULL, NULL, 1);
INSERT INTO `product_comments` VALUES ('5', '3', '咪咪虾条', '10000005', '2', 1, 0, 1, '评价内容5', '{}', '2021-04-28 09:38:13', 0, NULL, NULL, 1);
INSERT INTO `product_comments` VALUES ('6', '3', '咪咪虾条', '10000006', '3', 1, 0, 1, '评价内容6', '{}', '2021-04-21 09:39:16', 0, NULL, NULL, 1);
INSERT INTO `product_comments` VALUES ('7', '3', '咪咪虾条', '10000007', '4', 0, -1, 1, '评价内容7', '{}', '2021-04-06 09:40:01', 0, NULL, NULL, 1);

-- ----------------------------
-- Table structure for product_img
-- ----------------------------
DROP TABLE IF EXISTS `product_img`;
CREATE TABLE `product_img`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片主键',
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品外键id 商品外键id',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址 图片地址',
  `sort` int(11) NOT NULL COMMENT '顺序 图片顺序，从小到大',
  `is_main` int(11) NOT NULL COMMENT '是否主图 是否主图，1：是，0：否',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品图片 ' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of product_img
-- ----------------------------
INSERT INTO `product_img` VALUES ('07403', '25959471', 'img/2_2021123100503001429.jpg', 1, 1, '2021-12-31 16:50:30', '2021-12-31 16:50:30');
INSERT INTO `product_img` VALUES ('13708601', '8653137', 'img/7aa9c2fad2d8275b_2022010316422113689922.jpg', 1, 1, '2022-01-04 08:42:21', '2022-01-04 08:42:21');
INSERT INTO `product_img` VALUES ('17333613', '16302465', 'img/mei_gui_hua_cha_2021123103410117328424.jpg', 1, 1, '2021-12-31 19:41:02', '2021-12-31 19:41:02');
INSERT INTO `product_img` VALUES ('17495471', '62087398', 'img/O1CN01QpD2UM1jrlPvs6tJo_!!2206892234602.jpg_430x430q90_2021123100461117490172.jpg', 1, 1, '2021-12-31 16:46:12', '2021-12-31 16:46:12');
INSERT INTO `product_img` VALUES ('19360763', '81759814', 'img/tu_2022010318151119301769.png', 1, 1, '2022-01-04 10:15:12', '2022-01-04 10:15:12');
INSERT INTO `product_img` VALUES ('25629717', '18554393', 'img/三星_2021123100491225618805.jpg', 1, 1, '2021-12-31 16:49:13', '2021-12-31 16:49:13');
INSERT INTO `product_img` VALUES ('26829136', '25031649', 'img/TB2ntOehY9YBuNjy0FgXXcxcXXa_!!2115121601_2021123101094226802655.jpg', 1, 1, '2021-12-31 17:09:43', '2021-12-31 17:09:43');
INSERT INTO `product_img` VALUES ('27927320', '26990382', 'img/niu_rou_gan_2021123103384227917980.jpg', 1, 1, '2021-12-31 19:38:43', '2021-12-31 19:38:43');
INSERT INTO `product_img` VALUES ('33790728', '64967901', 'img/6_2021123100422333787529.jpg', 1, 1, '2021-12-31 16:42:23', '2021-12-31 16:42:23');
INSERT INTO `product_img` VALUES ('37711725', '34793911', 'img/3dac14cf0173948cfa47a31afe859335_2022010221461337684033.jpeg', 1, 1, '2022-01-03 13:46:14', '2022-01-03 13:46:14');
INSERT INTO `product_img` VALUES ('40206283', '37437797', 'img/O1CN01tW4l9o27MfIMP1ydD_!!0-item_pic_2021123100530440180404.jpg', 1, 1, '2021-12-31 16:53:04', '2021-12-31 16:53:04');
INSERT INTO `product_img` VALUES ('43801671', '42122875', 'img/O1CN01BoKp7C2FnFCzx8hqz_!!0-item_pic.jpg_430x430q90_2021123101063443793683.jpg', 1, 1, '2021-12-31 17:06:34', '2021-12-31 17:06:34');
INSERT INTO `product_img` VALUES ('55794171', '54170782', 'img/O1CN01OSebdD1kxXw7cxEwF_!!2205579824750-0-lubanu-s.jpg_430x430q90_2021123100510555781899.jpg', 1, 1, '2021-12-31 16:51:06', '2021-12-31 16:51:06');
INSERT INTO `product_img` VALUES ('64450576', '63031777', 'img/da_bai_tu_2021123103335664421938.jpg', 1, 1, '2021-12-31 19:33:56', '2021-12-31 19:33:56');
INSERT INTO `product_img` VALUES ('71030420', '19561127', 'img/xiaomi.jpg__2021123100555771033113.webp', 1, 1, '2021-12-31 16:55:57', '2021-12-31 16:55:57');
INSERT INTO `product_img` VALUES ('73328098', '71662231', 'img/O1CN01zDl6LT2AGexhKgh3f_!!0-item_pic.jpg_430x430q90_2021123100491773313968.jpg', 1, 1, '2021-12-31 16:49:17', '2021-12-31 16:49:17');
INSERT INTO `product_img` VALUES ('7357995', '6033768', 'img/O1CN01lc3xKf2Ldyu2dLCEA_!!628189716.jpg_430x430q90_202112310102507346086.jpg', 1, 1, '2021-12-31 17:02:51', '2021-12-31 17:02:51');
INSERT INTO `product_img` VALUES ('73828719', '95595959', 'img/4_2021123100415773813326.jpg', 1, 1, '2021-12-31 16:41:57', '2021-12-31 16:41:57');
INSERT INTO `product_img` VALUES ('76675537', '75436070', 'img/O1CN01yO2UpM1Er4jmZkdSz_!!2208376580404.jpg_430x430q90_2021123100452776676209.jpg', 1, 1, '2021-12-31 16:45:28', '2021-12-31 16:45:28');
INSERT INTO `product_img` VALUES ('7812705', '2840370', 'img/O1CN01qNS3Pj1DHyl64gk7R_!!2200749900192_202112310108107772738.jpg', 1, 1, '2021-12-31 17:08:11', '2021-12-31 17:08:11');
INSERT INTO `product_img` VALUES ('78569170', '66232947', 'img/magic.jpg__2021123100531778561864.webp', 1, 1, '2021-12-31 16:53:18', '2021-12-31 16:53:18');
INSERT INTO `product_img` VALUES ('80689425', '77885075', 'img/O1CN01T2rrG81uB8IzhtLGl_!!656035998_2021123100565880616806.jpg', 1, 1, '2021-12-31 16:56:58', '2021-12-31 16:56:58');
INSERT INTO `product_img` VALUES ('81251055', '79875193', 'img/O1CN01HR4CZa2Gx9IKrrxwb_!!1837119081.jpg_430x430q90_2021123101001881245181.jpg', 1, 1, '2021-12-31 17:00:18', '2021-12-31 17:00:18');
INSERT INTO `product_img` VALUES ('84571370', '80836525', 'img/vivo.jpg__2021123105084884387482.webp', 1, 1, '2021-12-31 21:08:48', '2021-12-31 21:08:48');
INSERT INTO `product_img` VALUES ('87702391', '86223418', 'img/O1CN01zCPA2L2L7vB8S9DYk_!!0-item_pic.jpg_430x430q90_2021123100474887695415.jpg', 1, 1, '2021-12-31 16:47:49', '2021-12-31 16:47:49');
INSERT INTO `product_img` VALUES ('88135665', '87006304', 'img/jue_wei_ya_bo_2021123103361888120741.jpg', 1, 1, '2021-12-31 19:36:19', '2021-12-31 19:36:19');
INSERT INTO `product_img` VALUES ('9249662', '7689934', 'img\\HUAWEI Mate X2_202201031743309202839.png', 1, 1, '2022-01-04 09:43:31', '2022-01-04 09:43:31');
INSERT INTO `product_img` VALUES ('9429203', '6432001', 'img/hai_dai_202201032212209412510.jpg', 1, 1, '2022-01-04 14:12:21', '2022-01-04 14:12:21');
INSERT INTO `product_img` VALUES ('96583745', '52363235', 'img/3_2021123100423996586974.jpg', 1, 1, '2021-12-31 16:42:40', '2021-12-31 16:42:40');

-- ----------------------------
-- Table structure for product_params
-- ----------------------------
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
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品参数 ' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of product_params
-- ----------------------------
INSERT INTO `product_params` VALUES ('1', '3', '中国湖北武汉', '12个月', '咪咪', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO `product_params` VALUES ('2', '19', '中国湖北武汉', '12个月', '卫龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO `product_params` VALUES ('3', '20', '中国湖北武汉', '12个月', '威龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO `product_params` VALUES ('4', '21', '中国湖北武汉', '12个月', '卫龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO `product_params` VALUES ('5', '22', '中国湖北武汉', '12个月', '威龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO `product_params` VALUES ('6', '23', '中国湖北武汉', '12个月', '老干妈', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO `product_params` VALUES ('7', '24', '中国湖北武汉', '12个月', '卫龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO `product_params` VALUES ('8', '1', '中国湖北武汉', '12个月', '良品铺子', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku`  (
  `sku_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'sku编号',
  `product_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `sku_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sku名称',
  `sku_img` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sku图片',
  `untitled` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性组合（格式是p1:v1;p2:v2）',
  `original_price` int(11) NULL DEFAULT NULL COMMENT '原价',
  `sell_price` int(11) NOT NULL COMMENT '销售价格',
  `discounts` decimal(4, 2) NULL DEFAULT NULL COMMENT '折扣力度',
  `stock` int(11) NOT NULL COMMENT '库存',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` int(11) NULL DEFAULT NULL COMMENT 'sku状态(1启用，0禁用，-1删除)',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of product_sku
-- ----------------------------
INSERT INTO `product_sku` VALUES ('1', '3', '咪咪虾条 单包 50g', 'mmxt_1.png', '{口味:[\"原味\",\"烧烤\",\"芥末\"],包装:[\"盒装\",\"袋装\"]}', 2, 1, 0.70, 316, '2021-04-26 15:58:00', '2021-04-25 15:58:00', 1);
INSERT INTO `product_sku` VALUES ('10', '24', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('11', '25', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('12', '26', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('13', '27', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('14', '28', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('15', '29', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('16117777', '62087398', NULL, NULL, NULL, NULL, 159, NULL, 123, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('16443529', '16302465', NULL, NULL, NULL, NULL, 59, NULL, 1000, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('19265527', '81759814', NULL, NULL, NULL, NULL, 100, NULL, 5000, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('2', '3', '咪咪虾条 6连包', 'mmxt_1.png', '{口味:[\"原味\",\"烧烤\"],包装:[\"新包装\",\"老包装\"]}', 6, 5, 0.60, 171, '2021-04-26 14:48:21', '2021-04-26 14:48:25', 1);
INSERT INTO `product_sku` VALUES ('25175381', '25031649', NULL, NULL, NULL, NULL, 10, NULL, 123, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('27140116', '26990382', NULL, NULL, NULL, NULL, 40, NULL, 1000, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('29768327', '80836525', NULL, NULL, NULL, NULL, 5499, NULL, 999, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('2980963', '2840370', NULL, NULL, NULL, NULL, 13, NULL, 11, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('3', '3', '咪咪虾条 整箱50包', 'mmxt_1.png', '{口味:[\"原味\",\"烧烤\",\"孜然\"]}', 50, 40, 0.50, 37, '2021-04-26 14:49:20', '2021-04-26 14:49:23', 1);
INSERT INTO `product_sku` VALUES ('31061668', '30692436', NULL, NULL, NULL, NULL, 10, NULL, 100, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('32214301', '64967901', NULL, NULL, NULL, NULL, 16999, NULL, 1000, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('34949944', '34793911', NULL, NULL, NULL, NULL, 10, NULL, 100, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('37564960', '37437797', NULL, NULL, NULL, NULL, 18, NULL, 123, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('4', '1', '奥利奥分享装', 'ala_1.png', '{口味:[\"草莓\",\"巧克力\",\"牛奶\",\"原味\"]}', 10, 8, 0.80, 137, '2021-04-27 11:38:55', '2021-04-27 11:39:00', 1);
INSERT INTO `product_sku` VALUES ('42180569', '95595959', NULL, NULL, NULL, NULL, 8988, NULL, 5, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('42276740', '42122875', NULL, NULL, NULL, NULL, 10, NULL, 13, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('5', '19', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('54313881', '54170782', NULL, NULL, NULL, NULL, 29, NULL, 123, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('6', '20', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('6037983', '8653137', NULL, NULL, NULL, NULL, 149, NULL, 2526, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('6183901', '6033768', NULL, NULL, NULL, NULL, 98, NULL, 98, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('63223929', '63031777', NULL, NULL, NULL, NULL, 24, NULL, 1000, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('69466429', '19561127', NULL, NULL, NULL, NULL, 4999, NULL, 997, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('7', '21', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('71802836', '71662231', NULL, NULL, NULL, NULL, 189, NULL, 123, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('75581249', '75436070', NULL, NULL, NULL, NULL, 100, NULL, 100, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('76620727', '66232947', NULL, NULL, NULL, NULL, 6999, NULL, 997, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('78033086', '77885075', NULL, NULL, NULL, NULL, 12, NULL, 123, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('8', '22', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('80022159', '79875193', NULL, NULL, NULL, NULL, 12, NULL, 2343, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('81444046', '3590577', NULL, NULL, NULL, NULL, 11, NULL, 11, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('84070005', '6432001', NULL, NULL, NULL, NULL, 1000, NULL, 9990, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('86366975', '86223418', NULL, NULL, NULL, NULL, 132, NULL, 100, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('87179212', '87006304', NULL, NULL, NULL, NULL, 60, NULL, 999, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('9', '23', '加力加 100g', 'jlj_1.png', '{口味:[\"原味\",\"微辣\",\"BT\"]}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO `product_sku` VALUES ('94828632', '52363235', NULL, NULL, NULL, NULL, 8999, NULL, 999, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('95644198', '18554393', NULL, NULL, NULL, NULL, 14799, NULL, 3999, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('97673059', '25959471', NULL, NULL, NULL, NULL, 8999, NULL, 1000, NULL, NULL, 1);
INSERT INTO `product_sku` VALUES ('98446027', '68569563', NULL, NULL, NULL, NULL, 11, NULL, 1110, NULL, NULL, 1);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID',
  `sku_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'skuID',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `cart_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购物车商品数量',
  `cart_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '添加购物车时间',
  `product_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '添加购物车时商品价格',
  `sku_props` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选择的套餐的属性',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车 ' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (26, '32187642', '67369225', '6', '1', '2021-12-30 06:31:41', 100.00000000, NULL);
INSERT INTO `shopping_cart` VALUES (33, '31746426', '32138688', '2', '1', '2021-12-30 09:43:36', 100.00000000, NULL);
INSERT INTO `shopping_cart` VALUES (34, '31746426', '32138688', '1', '1', '2021-12-30 10:47:06', 100.00000000, NULL);
INSERT INTO `shopping_cart` VALUES (36, '18554393', '23010839', '4', '1', '2021-12-31 03:41:55', 14799.00000000, NULL);
INSERT INTO `shopping_cart` VALUES (45, '18554393', '23010839', '1', '1', '2022-01-02 06:45:11', 14799.00000000, NULL);
INSERT INTO `shopping_cart` VALUES (46, '87006304', '87179212', '10', '1', '2022-01-02 09:36:19', 60.00000000, NULL);
INSERT INTO `shopping_cart` VALUES (73, '8653137', '6037983', '2', '1', '2022-01-03 08:10:41', 149.00000000, NULL);

-- ----------------------------
-- Table structure for shoptable
-- ----------------------------
DROP TABLE IF EXISTS `shoptable`;
CREATE TABLE `shoptable`  (
  `shop_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shopkeeper_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shopkeeper_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shop_img` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`shop_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shoptable
-- ----------------------------
INSERT INTO `shoptable` VALUES ('1', '好利来1', '1', '123', '经济实惠', NULL, 1);
INSERT INTO `shoptable` VALUES ('22432443', '好利来', '2', 'admin', '好', NULL, 1);
INSERT INTO `shoptable` VALUES ('3', '好利来3', '3', 'HQF3', '经济实惠', NULL, 1);
INSERT INTO `shoptable` VALUES ('32272500', 'zyy的廉价店铺', '6', 'zyy', '价格实惠', NULL, 1);
INSERT INTO `shoptable` VALUES ('32554047', '123', '14', '123', '123', NULL, 1);
INSERT INTO `shoptable` VALUES ('37599754', '444的小店', '8', 'zyy', '444的小小小店', NULL, 0);
INSERT INTO `shoptable` VALUES ('38872933', 'zxf的小店', '13', 'zxf', '欢迎光临本店，本店新开张，诚信经营，只赚信誉不赚钱，谢谢。', NULL, 1);
INSERT INTO `shoptable` VALUES ('50085027', '好名字', '4', 'aaa', '好', NULL, 1);
INSERT INTO `shoptable` VALUES ('50574924', 'zyy的小吃店', '12', 'zyy', '专卖鸭脖', NULL, 1);
INSERT INTO `shoptable` VALUES ('61949229', '万德隆购物商店', '10', '江晨', '买的东西包括衣服、吃的、用的', NULL, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_Img` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_birth` datetime NULL DEFAULT NULL,
  `user_regtime` datetime NULL DEFAULT NULL,
  `isAdmin` tinyint(1) NULL DEFAULT NULL,
  `isShopKeeper` tinyint(1) NULL DEFAULT NULL,
  `isVIP` tinyint(1) NULL DEFAULT NULL,
  `user_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123', '202cb962ac59075b964b07152d234b70', '123', 'zyy', 'img/ani36_2021123100400551534461.jpg', '12345456', '1234567@qq.com', '男', NULL, NULL, 1, 1, 1, '江西省南昌市南昌大学前湖校区');
INSERT INTO `user` VALUES (2, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'zyy', 'img/ani74_202112310113109702232.jpg', '13188888888', '1234567@qq.com', '男', NULL, NULL, 1, 1, 1, '江西省南昌市南昌大学前湖校区');
INSERT INTO `user` VALUES (3, '111', '698d51a19d8a121ce581499d7b701668', '111', 'zyy', 'img/ani37_2021123101044444381266.jpg', '12345456', '1234567@qq.com', '男', NULL, NULL, 0, 1, 1, '江西省南昌市南昌大学前湖校区');
INSERT INTO `user` VALUES (4, '222', 'bcbe3365e6ac95ea2c0343a2395834dd', '222', 'zyy', 'img/ani52_2021123100543441872855.jpg', '12345678909', '123456@qq.com', '男', NULL, NULL, 0, 1, 1, '江西省南昌市南昌大学前湖校区');
INSERT INTO `user` VALUES (7, '333', '310dcbbf4cce62f762a2aaa148d556bd', 'userdb6a206a07', NULL, 'img/default.png', NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL);
INSERT INTO `user` VALUES (8, '444', '550a141f12de6341fba65b0ad0433500', 'user7558c98d6c', NULL, 'img/default.png', NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `user` VALUES (9, 'gjl', '202cb962ac59075b964b07152d234b70', 'xsgkkadsf', 'gjl', 'img/tutu_2022010320401881787784.png', '13217065163', '2642440908@qq.com', '男', NULL, NULL, 0, 0, 1, '南昌市南昌大学');
INSERT INTO `user` VALUES (10, '111111', '96e79218965eb72c92a549dd5a330112', 'user772024c825', NULL, 'img/default.png', NULL, NULL, NULL, NULL, NULL, 0, 1, 1, NULL);
INSERT INTO `user` VALUES (12, '555', '15de21c670ae7c3f6f3f1f37029303c9', 'user519ad144b0', NULL, 'img/default.png', NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `user` VALUES (13, 'zxf', 'e10adc3949ba59abbe56e057f20f883e', 'user61d11a1e6e', 'zxf', 'img/default.png', '17879632526', '2311997861@qq.com', NULL, NULL, NULL, 0, 1, 0, '南昌');
INSERT INTO `user` VALUES (14, '123456', '202cb962ac59075b964b07152d234b70', '123', '123', 'img/tou_ xiang_2022010322082339823975.jpg', '13307911234', '1234567@qq.com', '女', NULL, NULL, 0, 1, 0, '江西省');

SET FOREIGN_KEY_CHECKS = 1;
