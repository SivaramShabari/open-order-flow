package com.openorderflow.common.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private UUID orderId;
    private String status;
    private Boolean isPaid;
    private BigDecimal totalAmount;
    private BigDecimal discountedAmount;
    private String customerInstructions;
    private Instant createdAt;
    private Instant updatedAt;

    private OrderMapperDto.CustomerSnapshot customer;
    private OrderMapperDto.BusinessSnapshot business;
    private OrderMapperDto.DeliveryLocation deliveryLocation;

    private List<OrderItemDto> items;
}
