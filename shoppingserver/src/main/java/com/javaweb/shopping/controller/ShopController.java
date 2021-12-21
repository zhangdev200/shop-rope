package com.javaweb.shopping.controller;


import com.javaweb.shopping.entity.*;
import com.javaweb.shopping.service.ShopService;
import com.javaweb.shopping.service.UserService;
import com.javaweb.shopping.service.impl.UserServiceImpl;
import com.javaweb.shopping.utils.TokenUtil;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(value = "提供用户的登录和注册接口",tags = "用户管理")
@CrossOrigin
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //添加店铺
    @PostMapping("/add")
    public ResultVO add(@RequestBody Shop shop,@RequestHeader("token")String token){
        ResultVO resultVO = shopService.addShop(shop,TokenUtil.getUserId(token));
        return resultVO;
    }

    //根据店铺ID获取店铺商品
    @GetMapping("/list")
    public ResultVO list(String shopID){
        ResultVO resultVO = shopService.selectProductFromShopID(shopID);
        return resultVO;
    }

    @GetMapping("/listshops")
    public ResultVO listAllShop(){
        ResultVO resultVO = shopService.listAllShop();
        return resultVO;
    }

    //删除店铺
    @GetMapping("/delete")
    public ResultVO delete(String shopID,@RequestHeader("token")String token){
        ResultVO resultVO = shopService.deleteShop(shopID,TokenUtil.getUserId(token));
        return resultVO;
    }

    //添加商品基本信息
    @PostMapping("/addproduct")
    public ResultVO delete(@RequestBody ProductVO productVO, @RequestHeader("token")String token){
        ResultVO resultVO = shopService.addProduct(productVO);
        return resultVO;
    }

    //添加商品参数信息
    @PostMapping("/addproductparam")
    public ResultVO delete(@RequestBody ProductParams productParams, @RequestHeader("token")String token){
        ResultVO resultVO = shopService.addProductParams(productParams);
        return resultVO;
    }

    //删除商品
    @GetMapping("/deleteproduct")
    public ResultVO deleteProduct(String productId,@RequestHeader("token")String token){
        ResultVO resultVO = shopService.deleteProduct(productId);
        return resultVO;
    }


}
