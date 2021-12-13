package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.ProductImg;
import com.javaweb.shopping.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImgMapper extends GeneralDAO<ProductImg> {
    List<ProductImg> selectProductImgByProductId(int productId);
}
