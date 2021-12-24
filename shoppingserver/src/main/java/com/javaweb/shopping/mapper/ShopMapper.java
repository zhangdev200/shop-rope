package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.Product;
import com.javaweb.shopping.entity.ProductVO;
import com.javaweb.shopping.entity.Shop;
import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMapper  extends GeneralDAO<Shop> {

    /*
    * 根据店的id获取改该店的产品*/
    List <ProductVO> selectProductFromShopID(@Param("CID") String shopID);

    /*找出所有的商店*/
    List <Shop> listAllShop();

    /*找出所有的待审核的商店*/
    List <Shop> listAllCheckingShop();

    /*增加商品*/
    /*@Param("ID") int product_id, @Param("name")  String productName, @Param("categoryID") int category,
                       @Param("RCID") int RootCID, @Param("num") int soldNum, @Param("status") int status, @Param("content") String content,
                       @Param("shopID") String shopId*/

    void addProduct(Product product);

    /*根据商品id删除商品*/
    void deleteProduct (@Param("ID") int product_id);




    /*在shop表中增加一个商店,这个函数与updateUserToShopKeeper一起使用,修改shop表*/
    void addShop(Shop shop);

    //店铺申请审核通过
    /*根据用户id注册该用户为商家,修改user表*/
    void updateUserToShopKeeper(int ID);
    //店铺申请审核不通过
    /*根据用户id注销该用户的商店，修改user表*/
    void updateShopKeeperToUser(int ID);

    /*通过shop_id 删除商店表里该用户的店铺，修改shop表*/
    void deleteShop(String ID,String userId);

    /*根据用户id获取该用户的所有商店id*/
    String [] getShopIdByUserID(int userID);


    /*修改店铺状态为通过*/
    void pass(int ID);

}
