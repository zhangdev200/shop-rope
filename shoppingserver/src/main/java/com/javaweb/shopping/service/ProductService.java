package com.javaweb.shopping.service;

import com.javaweb.shopping.vo.ResultVO;

public interface ProductService {
    ResultVO listRecommendProducts(); //获取首页推荐产品

    ResultVO getProductBasicInfo(String productId); //获取商品基本信息

    ResultVO getProductParamsById(String productId); //根据ID获取产品信息

    ResultVO getProductsByCategoryId(int categoryId,int pageNum,int limit); //根据类别获取商品

    ResultVO listBrands(int categoryId); //根据类别获取品牌

    ResultVO searchProduct(String kw,int pageNum,int limit); //搜索产品

    ResultVO listBrands(String kw); //根据关键词获取品牌
}
