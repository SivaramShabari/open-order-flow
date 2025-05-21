package com.openorderflow.order.kafka.consumer;

import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsRejectedEventV1;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import com.openorderflow.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "order.creation.requested.v1", groupId = "order-service")
    public void consumeOrderCreation(RequestOrderEventV1 event) {
        log.info("Consumed order.creation.requested: {}", event.getOrderId());
        orderService.handleOrderCreationRequested(event);
    }

    @KafkaListener(topics = "inventory.validated", groupId = "order-service")
    public void consumeInventoryValidated(OrderItemsValidatedEventV1 event) {
        log.info("Consumed inventory.validated: {}", event.getOrderId());
        orderService.handleInventoryValidated(event);
    }

    @KafkaListener(topics = "inventory.invalidated", groupId = "order-service")
    public void consumeInventoryInvalidated(OrderItemsRejectedEventV1 event) {
        log.info("Consumed inventory.invalidated: {}", event.getOrderId());
        orderService.handleInventoryInvalidated(event);
    }
}
