package com.openorderflow.inventory.kafka.producer;

import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import com.openorderflow.common.kafka.topics.KafkaTopicsV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderItemsValidatedProducer {

    private final KafkaTemplate<String, OrderItemsValidatedEventV1> kafkaTemplate;

    public void send(OrderItemsValidatedEventV1 event) {
        String topic = KafkaTopicsV1.INVENTORY_ITEMS_VALIDATED_V1;
        kafkaTemplate.send(topic, event.getOrderId().toString(), event);
        log.info("Produced: {} to topic {}", event, topic);
    }
}
