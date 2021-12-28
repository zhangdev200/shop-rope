package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.*;
import com.javaweb.shopping.vo.ResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShopService {
    ResultVO selectProductFromShopID(String shopID);
    ResultVO listAllShop();
    ResultVO listAllCheckingShop();
    ResultVO listShopByUserId(String userId);
    ResultVO addProduct(ProductVO productVO);
    //添加商品参数信息
    ResultVO addProductParams(ProductParams params);


    ResultVO deleteProduct(String ID);

    //店铺申请审核通过
    ResultVO updateUserToShopKeeper(int ID);

    //修改店铺状态通过
    ResultVO pass(int ID);


    ResultVO addShop(Shop shop, String userId);

    ResultVO addProductImg(ProductImg productImg);

    /*根据用户id注销该用户的商店*/
    //店铺申请审核不通过
    ResultVO updateShopKeeperToUser(int ID);

//    /*更新店铺状态为通过*/
//    ResultVO updateShopStatus(int ID);
    /*删除商店表里该用户的店铺*/
    ResultVO deleteShop(String ID);

    ResultVO updateProduct(ProductVO productVO);

    ResultVO updateProductParams(ProductParams productParams);

    String [] getShopIdByUserID(int userID);

    //开店审核不通过

}