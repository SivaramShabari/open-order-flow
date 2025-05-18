package com.openorderflow.customer.kafka;

import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderPlacedEvent(RequestOrderEventV1 event) {
        kafkaTemplate.send("order.creation.requested", event.getCustomerId().toString(), event);
    }
}
