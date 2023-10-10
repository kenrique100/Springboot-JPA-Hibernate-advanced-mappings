package com.kbf.Api.mapper;

import com.kbf.Api.model.Order;
import com.kbf.Api.model.dto.CreateOrderRequest;
import com.kbf.Api.model.dto.OrderDto;

public interface OrderMapper {

    Order toOrder(CreateOrderRequest createOrderRequest);

    OrderDto toOrderDto(Order order);
}