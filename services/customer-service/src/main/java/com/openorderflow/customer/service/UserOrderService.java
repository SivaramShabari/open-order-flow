package com.openorderflow.customer.service;

import com.openorderflow.common.auth.util.CurrentUserContext;
import com.openorderflow.common.dto.order.PlaceOrderRequest;
import com.openorderflow.common.kafka.events.v1.order.PlaceOrderEventV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    public void placeOrder(PlaceOrderRequest placeOrderRequest) {

        var placeOrderEvent = new PlaceOrderEventV1(

        );

    }
}
