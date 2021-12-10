/*这是数据库的语句文件  */



create table admin (   /*创建管理员表*/
    ad_id INTEGER primary key ,
    ad_name varchar(50) ,
    ad_sex varchar(30),
    ad_password varchar(50)
);

create table store(      /*商家的店铺信息*/
    store_id INTEGER primary key ,
    keeper_id INTEGER ,
    store_name varchar(50),
    keeper_name varchar(50),
    store_description varchar(50)
);

create table goods( /*商家的物品*/
    g_id INTEGER PRIMARY KEY ,
    g_name varchar(50),
    g_price double not null ,
    g_imageURL varchar(200),
    g_quantity INTEGER not null , /*存量*/
    g_description varchar(50)

);
insert into admin values(1,'胡权富','男','123');
select  *from admin;


