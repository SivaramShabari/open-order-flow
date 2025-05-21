package com.openorderflow.order.entity;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@Embeddable
public class BusinessItemSnapshot {
    private UUID id;
    private String itemName;
    private String itemDescription;
    private String type;
    private BigDecimal priceAtOrder;
    private BigDecimal discountedPriceAtOrder;
}
