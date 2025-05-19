package com.openorderflow.common.kafka.events.v1.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsRejectedEventV1 {
    private UUID orderId;
    private String reason;
    private List<UUID> unavailableItemIds;
    private Instant rejectedAt;
}