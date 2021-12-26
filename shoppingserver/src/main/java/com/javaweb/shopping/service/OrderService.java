package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.Orders;
import com.javaweb.shopping.vo.ResultVO;

import java.sql.SQLException;
import java.util.Map;

public interface OrderService {

    public Map<String,String> addOrder(String cids, Orders order) throws SQLException;

    //立即购买
    public Map<String,String> addInstanceOrder(String productId, Orders order) throws SQLException;

    public int updateOrderStatus(String orderId, String status);

    public ResultVO getOrderById(String orderId);

    public void closeOrder(String orderId);

    public ResultVO listOrders(String userId,String status,int pageNum, int limit);



}

