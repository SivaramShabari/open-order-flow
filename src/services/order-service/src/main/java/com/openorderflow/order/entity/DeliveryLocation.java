package com.openorderflow.order.entity;

import com.openorderflow.common.common.GeoLocation;
import jakarta.persistence.*;

@Embeddable
public class DeliveryLocation {
    private String deliverLocationName;
    private String deliverLocationType;
    private String deliverLocationAddressLine1;
    private String deliverLocationAddressLine2;
    private String deliverLocationAddressLine3;
    private String deliverLocationCity;
    private String deliverLocationState;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "delivery_location_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "delivery_location_longitude")),
    })
    private GeoLocation location;
}
