package com.javaweb.shopping.dao;

import com.javaweb.shopping.ShoppingApplication;
import com.javaweb.shopping.entity.Orders;
import com.javaweb.shopping.entity.OrdersVO;
import com.javaweb.shopping.mapper.OrdersMapper;
import com.javaweb.shopping.service.OrderService;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class OrdersMapperTest {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderService orderService;
    @Test
    public  void  test() throws SQLException {
//        List<OrdersVO> ordersVOS = ordersMapper.selectOrders("1", "2", 1, 20);
//        System.out.println(ordersVOS.size());

//        Orders order = new Orders();
//        order.setCreateTime(new Date());
//        order.setUntitled("测试");
//        order.setStatus("1");
//        order.setUserId("4");
//        order.setReceiverName("zhangsan");
//        order.setReceiverMobile("123");
//        order.setReceiverAddress("Beijing");
//
//        //生成订单编号
//        String orderId = UUID.randomUUID().toString().replace("-", "");
//        order.setOrderId(orderId);
//        orderService.addOrder("142,146,155",order);
        orderService.deleteOrders("04e5f68143eb4679aafb10309df12faa");
    }
}
