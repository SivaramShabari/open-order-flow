package com.openorderflow.common.dto.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddInventoryItemRequest {
    @NotNull private UUID inventoryId;
    @NotNull private UUID businessItemId;
    private LocalDate arrivalDate;
    private LocalDate expiryDate;
    @NotNull private Integer quantity;
    @NotNull private BigDecimal price;
}
