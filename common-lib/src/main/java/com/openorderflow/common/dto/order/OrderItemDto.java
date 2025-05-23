package com.openorderflow.common.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private UUID businessItemId;
    private String name;
    private String description;
    private String type;
    private BigDecimal unitPrice;
    private BigDecimal discountedPrice;
    private int quantity;
}
