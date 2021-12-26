package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.ProductSku;
import com.javaweb.shopping.entity.ShoppingCartVO;
import com.javaweb.shopping.mapper.ProductMapper;
import com.javaweb.shopping.mapper.ProductSkuMapper;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author:zyy
 * @date:2021/12/25
 */
public class ProductSkuServiceImpl {
    @Autowired
    ProductSkuMapper productSkuMapper;
    //根据产品ID获取库存信息
    public ResultVO selectSkusByProductId(String productId){
        List<ProductSku> list = productSkuMapper.selectSkusByProductId(productId);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", list);
        return resultVO;
    }
}
