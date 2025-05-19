package com.openorderflow.inventory.kafka.producer;

import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsRejectedEventV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderItemsRejectedProducer {

    private final KafkaTemplate<String, OrderItemsRejectedEventV1> kafkaTemplate;

    public void send(OrderItemsRejectedEventV1 event) {
        String topic = "order.items.rejected.v1";
        kafkaTemplate.send(topic, event.getOrderId().toString(), event);
        log.info("Produced: {} to topic {}", event, topic);
    }
}
