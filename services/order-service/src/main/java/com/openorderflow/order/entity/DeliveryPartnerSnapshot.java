package com.openorderflow.order.entity;

import com.openorderflow.common.common.GeoLocation;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.UUID;

@Embeddable
public class DeliveryPartnerSnapshot {
    private UUID Id;
    private String name;
    private String email;
    private String phone;
    @Embedded
    private GeoLocation starting_location;
}
