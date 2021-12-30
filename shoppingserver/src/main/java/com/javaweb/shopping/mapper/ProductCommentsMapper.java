package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.ProductComments;
import com.javaweb.shopping.entity.ProductCommentsVO;
import com.javaweb.shopping.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductCommentsMapper extends GeneralDAO<ProductComments> {
    /**
     * 根据商品id分页查询评论信息
     * @param productId 商品ID
     * @param start 起始索引
     * @param limit 查询条数
     * @return
     */
    public List<ProductCommentsVO> selectCommentsByProductId(@Param("productId") String productId,
                                                             @Param("start") int start,
                                                             @Param("limit") int limit);

    /*通过前端获取评论产品的product_id 将该评论放在评论表中*/
    void insertProductComment(ProductComments productComments);

    /*通过删除的产品id删除该产品的评论*/
    void deleteProductComment(String ID);

    /*删除某商品特定的评论*/
    void deleteOneComment(String comm_ID);
}
