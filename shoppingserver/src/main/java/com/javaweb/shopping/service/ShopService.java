package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.Product;
import com.javaweb.shopping.entity.ProductVO;
import com.javaweb.shopping.entity.Shop;

import java.util.List;

public interface ShopService {
    List<ProductVO> selectProductFromShopID(String shopID);
    List<Shop> listAllShop();

    void addProduct(Product product);
    void deleteProduct(int ID);

    void updateUserToShopKeeper(int ID);

    void addShop(Shop shop);

    /*根据用户id注销该用户的商店*/
    void updateShopKeeperToUser(int ID);
    /*删除商店表里该用户的店铺*/
    void deleteShop(String ID);
}
