package com.openorderflow.order.mapper;

import com.openorderflow.common.dto.order.OrderItemDto;
import com.openorderflow.common.dto.order.OrderResponseDto;
import com.openorderflow.common.kafka.events.v1.order.OrderPlacedEventV1;
import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import com.openorderflow.order.entity.BusinessSnapshot;
import com.openorderflow.order.entity.CustomerSnapshot;
import com.openorderflow.order.entity.Order;
import com.openorderflow.order.entity.OrderItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    // Event to Entity
    public abstract Order toEntityFromRequestEvent(RequestOrderEventV1 event);

    public abstract List<OrderItem> toOrderItemsFromRequest(List<RequestOrderEventV1.RequestedItem> items);

    // Entity to REST DTO
    public abstract OrderResponseDto toDto(Order order);
    public abstract List<OrderItemDto> toItemDtos(List<OrderItem> items);

    // Entity to Event
    public abstract OrderPlacedEventV1 toOrderPlacedEvent(Order order);
    public abstract List<OrderPlacedEventV1.OrderItemSnapshot> toOrderPlacedItemEvents(List<OrderItem> items);

    // Enrich partial order with entity updates
    @AfterMapping
    protected void enrichOrderFromRequest(RequestOrderEventV1 event, @MappingTarget Order order) {
        order.setCustomer(CustomerSnapshot.builder()
                .id(event.getCustomerId())
                .name(event.getCustomerName())
                .email(event.getCustomerEmail())
                .phone(event.getCustomerPhone())
                .build());

        order.setBusiness(BusinessSnapshot.builder()
                .outletId(event.getBusinessOutletId())
                .build()); // further enrichment after inv validation

        order.setCustomerInstructions(event.getDeliveryInstruction());
        order.setCreatedAt(event.getOrderedAt());
        order.setUpdatedAt(event.getOrderedAt());
    }

    @AfterMapping
    protected void enrichPayment(RequestOrderEventV1.PaymentDetails payment, @MappingTarget Order order) {
        order.setIsPaid(payment.getIsPaid());
        order.setPaymentId(payment.getPaymentId());
    }
}
