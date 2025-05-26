package com.openorderflow.inventory.kafka.config;

import com.openorderflow.common.kafka.config.CommonKafkaProducerConfig;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsRejectedEventV1;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;


@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;


    @Bean
    public KafkaTemplate<String, OrderItemsValidatedEventV1> orderItemsValidatedKafkaTemplate() {
        return new KafkaTemplate<>(CommonKafkaProducerConfig.producerFactory(bootstrapServers));
    }


    @Bean
    public KafkaTemplate<String, OrderItemsRejectedEventV1> orderItemsRejectedKafkaTemplate() {
        return new KafkaTemplate<>(CommonKafkaProducerConfig.producerFactory(bootstrapServers));
    }
}
