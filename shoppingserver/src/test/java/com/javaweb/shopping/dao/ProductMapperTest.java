package com.javaweb.shopping.dao;

import com.javaweb.shopping.ShoppingApplication;
import com.javaweb.shopping.entity.ProductVO;
import com.javaweb.shopping.mapper.ProductMapper;
import com.javaweb.shopping.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test(){

/*      List<ProductVO> productVOS=productMapper.selectProductByCategoryId(46,1,100);
      for (int i=0;i<productVOS.size();i++){
          System.out.println(productVOS.get(i).getProductId());
          System.out.println(productVOS.get(i).getCategoryId());
      }
      System.out.println(productVOS.size());*/

        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        System.out.println(productVOS.size());

    }
}
