package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.ProductComments;
import com.javaweb.shopping.vo.ResultVO;

public interface ProductCommentsService {
    /**
     * 根据商品id实现评论的分页查询
     * @param productId 商品ID
     * @param pageNum 查询页码
     * @param limit 每页显示条数
     * @return
     */
    public ResultVO listCommontsByProductId(String productId, int pageNum, int limit);

    /**
     * 根据商品ID统计当前商品的评价信息
     * @param productId
     * @return
     */
    ResultVO getCommentsCountByProductId(String productId);

    ResultVO insertProductComment(ProductComments productComments);

    ResultVO deleteProductComment(String ID);
}
