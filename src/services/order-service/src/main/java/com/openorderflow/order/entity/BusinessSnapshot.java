package com.openorderflow.order.entity;

import com.openorderflow.common.common.GeoLocation;
import jakarta.persistence.*;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@Embeddable
public class BusinessSnapshot {
    private UUID businessId;
    private UUID businessOutletId;
    private String businessName;
    private String businessOutletName;
    private String businessOutletPhone;
    private String businessOutletAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "business_outlet_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "business_outlet_longitude")),
    })
    private GeoLocation location;
}
