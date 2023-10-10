package com.kbf.Api.service;



import java.util.List;

import com.kbf.Api.model.Order;

public interface OrderService {

    List<Order> getOrders();

    List<Order> getOrdersContainingText(String text);

    Order validateAndGetOrder(String id);

    Order saveOrder(Order order);

    void deleteOrder(Order order);
}
