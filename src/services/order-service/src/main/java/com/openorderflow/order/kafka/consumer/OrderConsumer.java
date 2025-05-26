package com.openorderflow.order.kafka.consumer;

import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsRejectedEventV1;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import com.openorderflow.common.kafka.topics.KafkaTopicsV1;
import com.openorderflow.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final OrderService orderService;

    @KafkaListener(
            topics = KafkaTopicsV1.ORDER_CREATION_REQUESTED_V1,
            groupId = "order-service",
            containerFactory = "orderCreationConsumerFactory"
    )
    public void consumeOrderCreation(@Payload RequestOrderEventV1 event) {
        log.info("Consumed order.creation.requested: {}", event.getOrderId());
        orderService.handleOrderCreationRequested(event);
    }

    @KafkaListener(
            topics = KafkaTopicsV1.INVENTORY_ITEMS_VALIDATED_V1,
            groupId = "order-service",
            containerFactory = "inventoryValidationConsumerFactory"
    )
    public void consumeInventoryValidated(@Payload OrderItemsValidatedEventV1 event) {
        log.info("Consumed inventory.validated: {}", event.getOrderId());
        orderService.handleInventoryValidated(event);
    }

    @KafkaListener(
            topics = KafkaTopicsV1.INVENTORY_ITEMS_REJECTED_V1,
            groupId = "order-service",
            containerFactory = "inventoryRejectionConsumerFactory"
    )
    public void consumeInventoryInvalidated(@Payload OrderItemsRejectedEventV1 event) {
        log.info("Consumed inventory.invalidated: {}", event.getOrderId());
        orderService.handleInventoryInvalidated(event);
    }
}
