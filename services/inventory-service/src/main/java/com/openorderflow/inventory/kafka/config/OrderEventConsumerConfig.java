package com.openorderflow.inventory.kafka.config;

import com.openorderflow.common.kafka.config.CommonKafkaConsumerConfig;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidationRequestV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

@Configuration
public class OrderEventConsumerConfig {

    @Bean
    public ConsumerFactory<String, OrderItemsValidationRequestV1> orderValidationConsumerFactory(CommonKafkaConsumerConfig common) {
        var props = new HashMap<>(common.baseConsumerProps());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderItemsValidationRequestV1.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderItemsValidationRequestV1> orderValidationKafkaFactory(
            ConsumerFactory<String, OrderItemsValidationRequestV1> factory) {
        var containerFactory = new ConcurrentKafkaListenerContainerFactory<String, OrderItemsValidationRequestV1>();
        containerFactory.setConsumerFactory(factory);
        return containerFactory;
    }
}
