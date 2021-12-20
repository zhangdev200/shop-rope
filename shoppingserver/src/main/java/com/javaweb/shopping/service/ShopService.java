package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.Product;
import com.javaweb.shopping.entity.Shop;
import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.vo.ResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopService {
    ResultVO selectProductFromShopID(String shopID);
    ResultVO listAllShop();

    ResultVO addProduct(Product product);
    ResultVO deleteProduct(int ID);

    ResultVO updateUserToShopKeeper(int ID);

    ResultVO addShop(Shop shop,String userId);

    /*根据用户id注销该用户的商店*/
    ResultVO updateShopKeeperToUser(int ID);
    /*删除商店表里该用户的店铺*/
    ResultVO deleteShop(String ID,String userId);

}
