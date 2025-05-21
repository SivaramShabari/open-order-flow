package com.openorderflow.order.kafka.producer;

import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidationRequestV1;
import com.openorderflow.common.kafka.events.v1.order.OrderPlacedEventV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, OrderItemsValidationRequestV1> validationRequestKafkaTemplate;
    private final KafkaTemplate<String, OrderPlacedEventV1> orderPlacedKafkaTemplate;

    public void sendInventoryValidationRequested(OrderItemsValidationRequestV1 event) {
        String topic = "inventory.validation.requested.v1";
        validationRequestKafkaTemplate.send(topic, event.getOrderId().toString(), event);
        log.info("Produced inventory validation request: {} to topic {}", event, topic);
    }

    public void sendOrderPlaced(OrderPlacedEventV1 event) {
        String topic = "order.placed.v1";
        orderPlacedKafkaTemplate.send(topic, event.getOrderId().toString(), event);
        log.info("Produced order placed event: {} to topic {}", event, topic);
    }
}
