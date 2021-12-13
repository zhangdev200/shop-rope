package com.javaweb.shopping.dao;

import com.javaweb.shopping.ShoppingApplication;
import com.javaweb.shopping.entity.IndexImg;
import com.javaweb.shopping.mapper.IndexImgMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class IndexImgTest {
    @Autowired
    private IndexImgMapper indexImgMapper;
    @Test
    public  void test(){
        List<IndexImg> indexImgs = indexImgMapper.listIndexImgs();
        System.out.println(indexImgs.size());

    }
}
