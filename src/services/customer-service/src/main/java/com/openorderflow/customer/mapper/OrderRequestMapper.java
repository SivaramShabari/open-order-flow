package com.openorderflow.customer.mapper;

import com.openorderflow.common.dto.order.CustomerOrderCreationRequest;
import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {
    RequestOrderEventV1 toEvent(CustomerOrderCreationRequest request);
    RequestOrderEventV1.RequestedItem toEvent(CustomerOrderCreationRequest.RequestedItem request);
    RequestOrderEventV1.PaymentDetails toEvent(CustomerOrderCreationRequest.PaymentDetails request);
}
