package com.openorderflow.customer.service;

import com.openorderflow.common.dto.order.OrderCreationRequest;
import com.openorderflow.common.kafka.events.v1.order.PlaceOrderEventV1;
import com.openorderflow.customer.mapper.OrderRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderService {
    private final OrderRequestMapper orderRequestMapper;

    public void requestOrderCreation(OrderCreationRequest orderCreationRequest) {
        PlaceOrderEventV1 reqOrderEvent = orderRequestMapper.toEvent(orderCreationRequest);

    }
}
