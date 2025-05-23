package com.openorderflow.common.kafka.events.v1.inventory;

import com.openorderflow.common.dto.business.OrderQueueResponseDto;
import com.openorderflow.common.dto.inventory.BusinessItemDto;
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
    private List<OrderQueueResponseDto.OrderQueueItemDto> validatedItems;

    @NotNull
    private Instant validatedAt;
}
