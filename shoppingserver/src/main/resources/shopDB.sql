/*这是数据库的语句文件  */

select * from customers;

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

insert into admin values(1,'胡权富','男','123');
select  *from admin;

alter table shop.customers add column description varchar(40);