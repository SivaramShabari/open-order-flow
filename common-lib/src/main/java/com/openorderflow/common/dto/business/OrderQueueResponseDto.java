package com.openorderflow.common.dto.business;

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
public class OrderQueueResponseDto {

    private UUID orderQueueId;
    private UUID orderId;
    private UUID customerId;

    private UUID businessOutletId;
    private String outletName; // optional (if fetched via join or enrichment)

    private BigDecimal orderAmount;
    private String status;
    private Instant createdAt;

    private List<OrderQueueItemDto> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderQueueItemDto {
        private UUID itemId;
        private String name;
        private int quantity;
        private BigDecimal basePrice;
    }
}

