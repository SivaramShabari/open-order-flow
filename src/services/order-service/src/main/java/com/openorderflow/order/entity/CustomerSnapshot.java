package com.openorderflow.order.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@Embeddable
public class CustomerSnapshot {
    private UUID customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
}