package com.shop.sales.service;

import com.shop.core.model.dto.OrderDto;
import com.shop.core.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createSalesOrder(OrderDto orderDto);

    List<Order> queryAllOrders();

    Optional<Order> queryOrderById(String id);

    int countCustomerOrders(String id);
}
