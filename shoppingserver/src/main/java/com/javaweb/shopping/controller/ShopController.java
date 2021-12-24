package com.javaweb.shopping.controller;


import com.javaweb.shopping.annotation.AuthAdmin;
import com.javaweb.shopping.annotation.AuthShopKeeper;
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
    @AuthAdmin
    @AuthShopKeeper
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

    //列出所有已审核店铺
    @GetMapping("/listshops")
    public ResultVO listAllShop(){
        ResultVO resultVO = shopService.listAllShop();
        return resultVO;
    }

    //列出待审核店铺
    @GetMapping("/listcheckingshops")
    public ResultVO listAllCheckingShop(){
        ResultVO resultVO = shopService.listAllCheckingShop();
        return resultVO;
    }

    //列出用户的店铺
    @GetMapping("/listshopsbyuserid")
    public ResultVO listShopByUserId(@RequestParam String userId){
        ResultVO resultVO = shopService.listShopByUserId(userId);
        return resultVO;
    }


    //删除店铺(开店审核不通过)
    @PostMapping("/delete")
//    @AuthAdmin
//    @AuthShopKeeper
    public ResultVO delete(String shopID,@RequestHeader("token")String token){
        ResultVO resultVO = shopService.deleteShop(shopID,TokenUtil.getUserId(token));
        return resultVO;
    }

    //根据shop_id通过开店审核
    @PostMapping("/pass")
    public ResultVO pass(int ID){
        ResultVO resultVO = shopService.pass(ID);
        return resultVO;
    }

    /*根据用户id注册该用户为商家,修改user表*/
    @PostMapping("/updateToShopKeeper")
    public ResultVO updateUserToShopKeeper(int ID){
        ResultVO resultVO = shopService.updateUserToShopKeeper(ID);
        return resultVO;
    }

    /*根据用户id注销该用户的商店，修改user表*/
    @PostMapping("/updateToUser")
    public ResultVO updateShopKeeperToUser(int ID){
        ResultVO resultVO = shopService.updateShopKeeperToUser(ID);
        return resultVO;
    }

    //添加商品基本信息
    @PostMapping("/addproduct")
    @AuthAdmin
    @AuthShopKeeper
    public ResultVO addProduct(@RequestBody ProductVO productVO, @RequestHeader("token")String token){
        ResultVO resultVO = shopService.addProduct(productVO);
        return resultVO;
    }

    //更新商品基本信息
    @PostMapping("/updateproduct")
    @AuthAdmin
    @AuthShopKeeper
    public ResultVO updateProduct(@RequestBody ProductVO productVO, @RequestHeader("token")String token){
        ResultVO resultVO = shopService.updateProduct(productVO);
        return resultVO;
    }

    //添加商品参数信息
    @PostMapping("/addproductparam")
    @AuthAdmin
    @AuthShopKeeper
    public ResultVO addProductParam(@RequestBody ProductParams productParams, @RequestHeader("token")String token){
        ResultVO resultVO = shopService.addProductParams(productParams);
        return resultVO;
    }

    //更新商品参数信息
    @PostMapping("/updateproductparam")
    @AuthAdmin
    @AuthShopKeeper
    public ResultVO updateProductParam(@RequestBody ProductParams productParams, @RequestHeader("token")String token){
        ResultVO resultVO = shopService.updateProductParams(productParams);
        return resultVO;
    }

    //删除商品
    @GetMapping("/deleteproduct")
    @AuthAdmin
    @AuthShopKeeper
    public ResultVO deleteProduct(String productId,@RequestHeader("token")String token){
        ResultVO resultVO = shopService.deleteProduct(productId);
        return resultVO;
    }
}
