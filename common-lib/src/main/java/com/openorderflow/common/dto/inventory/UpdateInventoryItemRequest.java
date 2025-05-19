package com.openorderflow.common.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInventoryItemRequest {
    private LocalDate arrivalDate;
    private LocalDate expiryDate;
    private Integer quantity;
    private BigDecimal price;
}
