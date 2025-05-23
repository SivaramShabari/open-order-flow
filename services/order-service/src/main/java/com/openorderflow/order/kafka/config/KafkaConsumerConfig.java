package com.openorderflow.order.kafka.config;

import com.openorderflow.common.kafka.config.CommonKafkaConsumerConfig;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsRejectedEventV1;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, RequestOrderEventV1> orderCreationConsumerFactory() {
        var props = CommonKafkaConsumerConfig.baseConsumerProps(bootstrapServers);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, RequestOrderEventV1.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, OrderItemsValidatedEventV1> inventoryValidationConsumerFactory() {
        var props = CommonKafkaConsumerConfig.baseConsumerProps(bootstrapServers);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderItemsValidatedEventV1.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, OrderItemsRejectedEventV1> inventoryRejectionConsumerFactory() {
        var props = CommonKafkaConsumerConfig.baseConsumerProps(bootstrapServers);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderItemsRejectedEventV1.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }
}