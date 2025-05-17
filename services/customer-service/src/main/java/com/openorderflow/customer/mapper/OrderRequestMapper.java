package com.openorderflow.customer.mapper;

import com.openorderflow.common.dto.order.OrderCreationRequest;
import com.openorderflow.common.kafka.events.v1.order.PlaceOrderEventV1;
import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {
    PlaceOrderEventV1 toEvent(OrderCreationRequest request);
    RequestOrderEventV1.RequestedItem toEvent(OrderCreationRequest.RequestedItem request);
    RequestOrderEventV1.PaymentDetails toEvent(OrderCreationRequest.PaymentDetails request);
}
