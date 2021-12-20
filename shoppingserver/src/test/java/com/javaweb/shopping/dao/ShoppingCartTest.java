package com.javaweb.shopping.dao;

import com.javaweb.shopping.ShoppingApplication;
import com.javaweb.shopping.entity.ShoppingCartVO;
import com.javaweb.shopping.mapper.ShoppingCartMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class ShoppingCartTest {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Test
    public  void test(){
        List<ShoppingCartVO> shoppingCartVOS = shoppingCartMapper.selectShopcartByUserId(6);
        List<Integer> list=new LinkedList<>();
        list.add(1);
        list.add(6);
        List<ShoppingCartVO> shoppingCartVOS1 = shoppingCartMapper.selectShopcartByCids(list);
        System.out.println(shoppingCartVOS1.size());
    }
}
