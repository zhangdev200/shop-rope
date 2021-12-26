package com.javaweb.shopping.service;

import com.javaweb.shopping.vo.ResultVO;

public interface ProductSkuService {
    ResultVO selectSkusByProductId(String productId);//根据产品ID获取库存信息
}
