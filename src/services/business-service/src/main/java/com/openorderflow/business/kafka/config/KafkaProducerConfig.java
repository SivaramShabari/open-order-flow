package com.openorderflow.business.kafka.config;

import com.openorderflow.common.kafka.config.CommonKafkaProducerConfig;
import com.openorderflow.common.kafka.events.v1.business.BusinessApprovedEventV1;
import com.openorderflow.common.kafka.events.v1.business.BusinessRejectedEventV1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<String, BusinessApprovedEventV1> businessOrderApprovedKafkaTemplate(){
        return new KafkaTemplate<>(CommonKafkaProducerConfig.producerFactory(bootstrapServers));
    }

    @Bean
    public KafkaTemplate<String, BusinessRejectedEventV1> businessRejectedEventV1KafkaTemplate(){
        return new KafkaTemplate<>(CommonKafkaProducerConfig.producerFactory(bootstrapServers));
    }
}
