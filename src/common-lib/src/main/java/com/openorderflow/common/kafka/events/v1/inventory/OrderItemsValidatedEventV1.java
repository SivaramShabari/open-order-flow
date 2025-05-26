package com.openorderflow.common.kafka.events.v1.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsValidatedEventV1 {
    @NotNull
    private UUID orderId;

    @NotNull
    private UUID businessId;

    @NotNull
    private UUID businessOutletId;

    private BigDecimal amount;

    @NotNull
    private List<OrderQueueItem> validatedItems;

    @NotNull
    private Instant validatedAt;

    public static class OrderQueueItem {
        private UUID id;
        private String name;
        private int quantity;
        private BigDecimal basePrice;
    }
}
