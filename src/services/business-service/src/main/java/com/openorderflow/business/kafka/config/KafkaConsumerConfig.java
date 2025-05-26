package com.openorderflow.business.kafka.config;

import com.openorderflow.common.kafka.config.CommonKafkaConsumerConfig;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, OrderItemsValidatedEventV1> orderValidationConsumerFactory() {
        var props = CommonKafkaConsumerConfig.baseConsumerProps(bootstrapServers);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderItemsValidatedEventV1.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderItemsValidatedEventV1> orderValidationListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, OrderItemsValidatedEventV1>();
        factory.setConsumerFactory(orderValidationConsumerFactory());
        return factory;
    }
}
