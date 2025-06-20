package com.openorderflow.common.dto.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBusinessItemRequest {
    @NotNull private UUID businessId;
    @NotNull private UUID itemCatalogId;
    @NotNull private BigDecimal basePrice;
    @NotNull private String name;
    private String imageUrl;
    @NotNull private Boolean isAvailable;

}
