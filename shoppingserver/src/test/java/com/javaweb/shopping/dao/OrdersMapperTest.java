package com.javaweb.shopping.dao;

import com.javaweb.shopping.ShoppingApplication;
import com.javaweb.shopping.entity.OrdersVO;
import com.javaweb.shopping.mapper.OrdersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class OrdersMapperTest {
    @Autowired
    private OrdersMapper ordersMapper;
    @Test
    public  void  test(){
        List<OrdersVO> ordersVOS = ordersMapper.selectOrders("1", "2", 1, 20);
        System.out.println(ordersVOS.size());
    }
}
