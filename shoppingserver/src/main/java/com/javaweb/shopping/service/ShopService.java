package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.Product;
import com.javaweb.shopping.entity.Shop;
import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.vo.ResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopService {
    List <Product> selectProductFromShopID(String shopID);
    List<Shop> listAllShop();

    void addProduct(Product product);
    void deleteProduct(int ID);

    void updateUserToShopKeeper(int ID);

    void addShop(Shop shop);

    /*根据用户id注销该用户的商店*/
    void updateShopKeeperToUser(int ID);
    /*删除商店表里该用户的店铺*/
    void deleteShop(int ID);
}
