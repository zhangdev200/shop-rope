package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.OrderItem;
import com.javaweb.shopping.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper extends GeneralDAO<OrderItem> {

    public List<OrderItem> listOrderItemsByOrderId(String orderId);
}
