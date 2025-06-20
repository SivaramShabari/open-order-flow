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
    private UUID businessItemId;
    private String businessItemName;
    private String businessItemDescription;
    private String businessItemType;
    private BigDecimal businessItemPriceAtOrder;
    private BigDecimal businessItemDiscountedPriceAtOrder;
}
