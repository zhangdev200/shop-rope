package com.javaweb.shopping.dao;


import com.javaweb.shopping.ShoppingApplication;
import com.javaweb.shopping.entity.ProductParams;
import com.javaweb.shopping.entity.ProductVO;
import com.javaweb.shopping.mapper.ProductMapper;
import com.javaweb.shopping.mapper.ProductParamsMapper;
import com.javaweb.shopping.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class ProductParamsTest {
    @Autowired
    private ProductParamsMapper productParamsMapper;

    @Test
    public void test(){

        Example example1 = new Example(ProductParams.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("productId",88);//状态为1表示上架商品

    }
}
