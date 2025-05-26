package com.openorderflow.business.kafka.producer;

import com.openorderflow.common.kafka.events.v1.business.BusinessApprovedEventV1;
import com.openorderflow.common.kafka.events.v1.business.BusinessRejectedEventV1;
import com.openorderflow.common.kafka.topics.KafkaTopicsV1;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusinessOrderRejectEventProducer {
    private final KafkaTemplate<String, BusinessRejectedEventV1> kafkaTemplate;

    public void send(BusinessRejectedEventV1 event) {
        var topic = KafkaTopicsV1.BUSINESS_ORDER_REJECTED;
        kafkaTemplate.send(topic, event.getOrderId().toString(), event);
    }
}
