package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.Product;
import com.javaweb.shopping.entity.Shop;
import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.mapper.ShopMapper;
import com.javaweb.shopping.service.ShopService;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServicelmpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;
    @Override
    public List<Product> selectProductFromShopID(String shopID) {
        List <Product> shopProduct=shopMapper.selectProductFromShopID(shopID);
        return shopProduct;
    }

    @Override
    public List<Shop> listAllShop() {
        return shopMapper.listAllShop();
    }


    @Override
    public void addProduct(Product product) {
         shopMapper.addProduct(product);

    }

    @Override
    public void deleteProduct(int ID) {
        shopMapper.deleteProduct(ID);
    }

    @Override
    public void updateUserToShopKeeper(int ID) {
        shopMapper.updateUserToShopKeeper(1);
    }

    @Override
    public void addShop(Shop shop) {
        shopMapper.addShop(shop);
    }

    @Override
    public void updateShopKeeperToUser(int ID) {
        shopMapper.updateShopKeeperToUser(ID);
    }

    @Override
    public void deleteShop(String ID) {
        shopMapper.deleteShop(ID);
    }


}
