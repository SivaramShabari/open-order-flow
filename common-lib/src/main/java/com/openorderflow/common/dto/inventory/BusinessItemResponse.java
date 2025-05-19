package com.openorderflow.common.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessItemResponse {
    private UUID id;
    private UUID businessId;
    private UUID itemCatalogId;
    private BigDecimal basePrice;
    private boolean isAvailable;
    private Instant updatedAt;
}
