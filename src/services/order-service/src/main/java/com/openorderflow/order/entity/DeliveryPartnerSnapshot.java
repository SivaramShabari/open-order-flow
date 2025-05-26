package com.openorderflow.order.entity;

import com.openorderflow.common.common.GeoLocation;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@Embeddable
public class DeliveryPartnerSnapshot {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    @Embedded
    private GeoLocation starting_location;
}
