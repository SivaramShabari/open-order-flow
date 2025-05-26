package com.openorderflow.common.dto.order;

import com.openorderflow.common.common.GeoLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class OrderMapperDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustomerSnapshot {
        private UUID id;
        private String name;
        private String email;
        private String phone;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BusinessSnapshot {
        private UUID id;
        private UUID outletId;
        private String name;
        private String outletName;
        private String outletPhone;
        private String outletAddress;
        private GeoLocation location;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryLocation {
        private String name;
        private String type;
        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String city;
        private String state;
        private GeoLocation location;
    }
}
