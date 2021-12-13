package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.ShoppingCart;
import com.javaweb.shopping.vo.ResultVO;

public interface ShoppingCartService {
    public ResultVO addShoppingCart(ShoppingCart cart);

    public ResultVO listShoppingCartsByUserId(int userId);

    public ResultVO updateCartNum(int cartId,int cartNum);

    public ResultVO listShoppingCartsByCids(String cids);
}
