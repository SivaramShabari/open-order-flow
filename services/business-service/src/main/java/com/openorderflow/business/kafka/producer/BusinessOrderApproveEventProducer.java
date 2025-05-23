package com.openorderflow.business.kafka.producer;

import com.openorderflow.common.kafka.events.v1.business.BusinessApprovedEventV1;
import com.openorderflow.common.kafka.topics.KafkaTopicsV1;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusinessOrderApproveEventProducer {
    private final KafkaTemplate<String, BusinessApprovedEventV1> kafkaTemplate;

    public void send(BusinessApprovedEventV1 event) {
        var topic = KafkaTopicsV1.BUSINESS_ORDER_CONFIRMED;
        kafkaTemplate.send(topic, event.getOrderId().toString(), event);
    }
}
