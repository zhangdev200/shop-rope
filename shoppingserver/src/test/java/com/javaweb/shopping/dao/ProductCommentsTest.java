package com.javaweb.shopping.dao;

import com.javaweb.shopping.ShoppingApplication;
import com.javaweb.shopping.entity.ProductCommentsVO;
import com.javaweb.shopping.mapper.ProductCommentsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class ProductCommentsTest {
    @Autowired
    private ProductCommentsMapper productCommentsMapper;
    @Test
    public void  test(){
        final List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommontsByProductId("3", 1, 0);
        System.out.println(productCommentsVOS.size());
    }
}
