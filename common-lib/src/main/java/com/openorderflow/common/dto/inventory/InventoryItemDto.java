package com.openorderflow.common.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemDto {
    private UUID id;
    private UUID inventoryId;
    private UUID businessItemId;
    private LocalDate arrivalDate;
    private LocalDate expiryDate;
    private Integer quantity;
    private BigDecimal price;
    private Instant updatedAt;
}
