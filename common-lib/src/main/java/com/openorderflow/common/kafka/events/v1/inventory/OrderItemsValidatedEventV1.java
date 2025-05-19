package com.openorderflow.common.kafka.events.v1.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsValidatedEventV1 {
    private UUID orderId;
    private Boolean validated;
    private Instant validatedAt;
}
