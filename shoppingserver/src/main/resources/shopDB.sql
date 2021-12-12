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









