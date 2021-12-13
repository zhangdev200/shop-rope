package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.ShoppingCart;
import com.javaweb.shopping.entity.ShoppingCartVO;
import com.javaweb.shopping.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper extends GeneralDAO<ShoppingCart> {
    public List<ShoppingCartVO> selectShopcartByUserId(int userId);

    public int updateCartnumByCartid(@Param("cartId") int cartId,
                                     @Param("cartNum") int cartNum);

    //1,8
    public List<ShoppingCartVO> selectShopcartByCids(List<Integer> cids);
}

