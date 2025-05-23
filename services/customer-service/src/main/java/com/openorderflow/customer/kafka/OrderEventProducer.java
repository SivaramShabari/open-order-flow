package com.openorderflow.customer.kafka;

import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import com.openorderflow.common.kafka.topics.KafkaTopicsV1;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {
    private final KafkaTemplate<String, RequestOrderEventV1> kafkaTemplate;

    public void sendOrderPlacedEvent(RequestOrderEventV1 event) {
        kafkaTemplate.send(KafkaTopicsV1.ORDER_CREATION_REQUESTED_V1, event.getCustomerId().toString(), event);
    }
}
