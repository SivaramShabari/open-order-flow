package com.openorderflow.business.kafka.consumer;

import com.openorderflow.business.service.BusinessOrderService;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import com.openorderflow.common.kafka.topics.KafkaTopicsV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderItemsValidatedEventConsumer {

    private final BusinessOrderService businessOrderService;

    @KafkaListener(
            topics = KafkaTopicsV1.INVENTORY_ITEMS_VALIDATED_V1,
            groupId = "business-service",
            containerFactory = "orderValidationConsumerFactory"
    )
    public void consumeOrderItemValidation(OrderItemsValidatedEventV1 event) {
        businessOrderService.addToOrderQueue(event);
        log.info("Added new order to order_queue. {orderId} {}", event.getOrderId());
    }
}
