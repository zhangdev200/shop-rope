package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.Product;
import com.javaweb.shopping.entity.ShoppingCart;
import com.javaweb.shopping.entity.ShoppingCartVO;
import com.javaweb.shopping.mapper.ShoppingCartMapper;
import com.javaweb.shopping.service.ShoppingCartService;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServicelmpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public ResultVO addShoppingCart(ShoppingCart cart) {
        cart.setCartTime(sdf.format(new Date()));
        if(cart.getCartNum()==null){
            cart.setCartNum("1");
        }

        List<ShoppingCartVO> list = shoppingCartMapper.selectShopcartByUserId(Integer.parseInt(cart.getUserId()));
        for(ShoppingCartVO shoppingCartVO: list){
            if(shoppingCartVO.getProductId().equals(cart.getProductId())){
                return updateCartNum(shoppingCartVO.getCartId(),Integer.parseInt(shoppingCartVO.getCartNum())+Integer.parseInt(cart.getCartNum()));
            }
        }

        int i = shoppingCartMapper.insert(cart);
        if(i>0){
            return new ResultVO(ResStatus.OK,"success",null);
        }else{
            return new ResultVO(ResStatus.NO,"fail",null);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO listShoppingCartsByUserId(int userId) {
        List<ShoppingCartVO> list = shoppingCartMapper.selectShopcartByUserId(userId);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", list);
        return resultVO;
    }

    @Override
    public ResultVO updateCartNum(int cartId, int cartNum) {
        int i = shoppingCartMapper.updateCartnumByCartid(cartId, cartNum);
        if(i>0){
            return new ResultVO(ResStatus.OK,"update success",null);
        }else{
            return new ResultVO(ResStatus.NO,"update fail",null);
        }
    }

    @Override
    public ResultVO listShoppingCartsByCids(String cids) {
        //使用tkmapper只能查询到某张表中拥有的字段，因此没法查询到商品名称、图片、单价等信息
        String[] arr = cids.split(",");
        List<Integer> cartIds = new ArrayList<>();
        for (int i=0; i<arr.length; i++){
            cartIds.add(Integer.parseInt(arr[i]));
        }
        List<ShoppingCartVO> list = shoppingCartMapper.selectShopcartByCids(cartIds);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", list);
        return resultVO;
    }
    @Override
    public ResultVO deleteShoppingCart(String cids) {
        String[] arr = cids.split(",");
        List<Integer> cartIds = new ArrayList<>();
        for (int i=0; i<arr.length; i++){
            cartIds.add(Integer.parseInt(arr[i]));
        }

        Example example = new Example(ShoppingCart.class);
        Example.Criteria criteria = example.createCriteria();
        for(int cartId: cartIds){
            criteria.orEqualTo("cartId",cartId);
        }
        int ret = shoppingCartMapper.deleteByExample(example);

        if(ret==0){
            return new ResultVO(ResStatus.NO, "找不到记录", null);
        }else if(cartIds.size()==ret){
            return new ResultVO(ResStatus.OK, "success", null);
        }else{
            return new ResultVO(ResStatus.NO, "部分删除失败", null);
        }
//        List<ShoppingCartVO> list = shoppingCartMapper.selectShopcartByCids(cartIds);
//        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", list);
//        return resultVO;
    }

}
