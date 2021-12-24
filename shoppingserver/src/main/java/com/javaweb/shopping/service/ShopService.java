package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.*;
import com.javaweb.shopping.vo.ResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopService {
    ResultVO selectProductFromShopID(String shopID);
    ResultVO listAllShop();
    ResultVO listShopByUserId(String userId);
    ResultVO addProduct(ProductVO productVO);
    //添加商品参数信息
    ResultVO addProductParams(ProductParams params);


    ResultVO deleteProduct(String ID);

    ResultVO updateUserToShopKeeper(int ID);

    ResultVO addShop(Shop shop,String userId);

    ResultVO addProductImg(ProductImg productImg);

    /*根据用户id注销该用户的商店*/
    ResultVO updateShopKeeperToUser(int ID);
    /*删除商店表里该用户的店铺*/
    ResultVO deleteShop(String ID,String userId);
    ResultVO updateProduct(ProductVO productVO);

    ResultVO updateProductParams(ProductParams productParams);

    String [] getShopIdByUserID(int userID);

}