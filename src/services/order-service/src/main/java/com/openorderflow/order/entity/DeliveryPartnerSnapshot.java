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
    private UUID deliveryPartnerId;
    private String deliveryPartnerName;
    private String deliveryPartnerEmail;
    private String deliveryPartnerPhone;
    @Embedded
    private GeoLocation deliveryPartnerStartingLocation;
}
