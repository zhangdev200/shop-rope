package com.javaweb.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "shoptable")
public class Shop {
    /* 主键id
    * */
    @Id
    @Column(name="shop_id")
    private String shopID;

    /*
    * 店名*/
    @Column(name = "shop_name")
    private String shopName;

    /*
    * 店家id,对应user表的user_id*/
    @Column(name = "shopkeeper_id")
    private String shopKeeperID;

    /*
    * 店主名字,对应user表里的user_name*/
    @Column(name = "shopkeeper_name")
    private String shopKeeperName;

    /*
    店铺描述
    */
    @Column(name = "shop_description")
    private String shopDescription;

    /*
    * 店铺图片*/
    @Column(name = "shop_img")
    private String shopImg;

    public String getShopID() {
        return shopID;
    }

    public Shop() {
    }

    public Shop(String shopID, String shopName, String shopKeeperID, String shopKeeperName,
                String shopDescription, String shopImg) {
        this.shopID = shopID;
        this.shopName = shopName;
        this.shopKeeperID = shopKeeperID;
        this.shopKeeperName = shopKeeperName;
        this.shopDescription = shopDescription;
        this.shopImg = shopImg;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopKeeperID() {
        return shopKeeperID;
    }

    public void setShopKeeperID(String shopKeeperID) {
        this.shopKeeperID = shopKeeperID;
    }

    public String getShopKeeperName() {
        return shopKeeperName;
    }

    public void setShopKeeperName(String shopKeeperName) {
        this.shopKeeperName = shopKeeperName;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }
}
