package com.openorderflow.inventory.kafka.consumer;

import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsRejectedEventV1;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidationRequestV1;
import com.openorderflow.inventory.kafka.producer.OrderItemsRejectedProducer;
import com.openorderflow.inventory.kafka.producer.OrderItemsValidatedProducer;
import com.openorderflow.inventory.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderItemValidationRequestConsumer {

    private final InventoryItemService inventoryItemService;
    private final OrderItemsValidatedProducer validatedProducer;
    private final OrderItemsRejectedProducer rejectedProducer;

    @KafkaListener(
            topics = "order.validate.items.v1",
            groupId = "inventory-service"
    )
    public void consume(@Payload OrderItemsValidationRequestV1 request,
                        @Header(KafkaHeaders.KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        MDC.put("traceId", key);
        log.info("Consuming validate-order-items for order: {}", request.getOrderId());

        List<UUID> failedItems = new ArrayList<>();

        for (var item : request.getItems()) {
            try {
                inventoryItemService.reduceQuantity(request.getBusinessOutletId(), item.getBusinessItemId(), item.getQuantity());
            } catch (Exception ex) {
                failedItems.add(item.getBusinessItemId());
            }
        }

        if (failedItems.isEmpty()) {
            log.info("All items validated for order {}", request.getOrderId());
            validatedProducer.send(OrderItemsValidatedEventV1.builder()
                    .orderId(request.getOrderId())
                    .validated(true)
                    .validatedAt(Instant.now())
                    .build());
        } else {
            log.warn("Validation failed for order {}: unavailable items: {}", request.getOrderId(), failedItems);
            rejectedProducer.send(OrderItemsRejectedEventV1.builder()
                    .orderId(request.getOrderId())
                    .reason("One or more items unavailable or insufficient quantity")
                    .unavailableItemIds(failedItems)
                    .rejectedAt(Instant.now())
                    .build());
        }

        MDC.clear();
    }
}
