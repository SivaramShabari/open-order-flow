package com.openorderflow.order.kafka.config;

import com.openorderflow.common.kafka.config.CommonKafkaProducerConfig;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidationRequestV1;
import com.openorderflow.common.kafka.events.v1.order.OrderPlacedEventV1;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<String, OrderItemsValidationRequestV1> validationRequestKafkaTemplate() {
        return new KafkaTemplate<>(CommonKafkaProducerConfig.producerFactory(bootstrapServers));
    }

    @Bean
    public KafkaTemplate<String, OrderPlacedEventV1> orderPlacedKafkaTemplate() {
        return new KafkaTemplate<>(CommonKafkaProducerConfig.producerFactory(bootstrapServers));
    }
}