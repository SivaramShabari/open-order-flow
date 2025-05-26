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
    private UUID id;
    private String name;
    private String email;
    private String phone;
}