package com.openorderflow.order.entity;

import com.openorderflow.common.common.GeoLocation;
import jakarta.persistence.*;

@Embeddable
public class DeliveryLocation {
    private String name;
    private String type;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "delivery_location_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "delivery_location_longitude")),
    })
    private GeoLocation location;
}
