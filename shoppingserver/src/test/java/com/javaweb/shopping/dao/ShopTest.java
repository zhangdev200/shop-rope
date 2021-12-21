package com.javaweb.shopping.dao;

import com.javaweb.shopping.ShoppingApplication;
import com.javaweb.shopping.entity.Product;
import com.javaweb.shopping.entity.Shop;
import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.mapper.ShopMapper;
import com.javaweb.shopping.service.ShopService;
import com.javaweb.shopping.vo.ResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class ShopTest {
    @Autowired
    private ShopMapper shopMapper;

    @Test
    public  void test(){
        shopMapper.deleteShop("21");
   /*    Product product=new Product();
       product.setProductId("21");
       product.setProductName("旺旺雪饼");
       product.setCategoryId(1);
       product.setRootCategoryId(1);
       product.setSoldNum(100);
       product.setProductStatus(1);
       product.setContent("很好吃");
       product.setShopID("1");

       shopMapper.addProduct(product);*/

  /* shopMapper.deleteProduct(21);*/
       /* List<Shop> shops = shopMapper.listAllShop();
        for (int i=0;i<shops.size();i++){
            System.out.println(shops.get(i).getShopKeeperName());
        }*/

     /*  shopMapper.updateUserToShopKeeper(1);*/

       /* Shop shop=new Shop();
        shop.setShopID("10");
        shop.setShopName("天虹");
        shop.setShopKeeperID("1");
        shop.setShopKeeperName("hqf");
        shop.setShopDescription("便宜实惠");
        shop.setShopImg("www.");
        shop.setStatus(1);
        shopMapper.addShop(shop); */

       /*shopMapper.updateShopKeeperToUser(1);
       shopMapper.deleteShop(1);*/

//        List<Shop> shops = shopMapper.listAllShop();
//        System.out.println(shops.size());

    }
}
