package com.openorderflow.common.dto.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessItemDto {
    private UUID id;
    private String name;
    private BigDecimal basePrice;
    private Integer quantity;
    private UUID itemCatalogId;
    private Boolean isAvailable;
}
