package com.openorderflow.common.dto.customer;

import com.openorderflow.common.common.GeoLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProfileDto {

    private UUID id;
    private String name;
    private String primaryPhoneNumber;
    private String email;
    private Boolean isEmailVerified;

    private AddressDto primaryAddress; // can be null
    private List<AddressDto> addresses;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AddressDto {
        private UUID id;
        private String addressName;
        private String type;
        private String timings;
        private String addressLine1;
        private String addressLine2;
        private GeoLocation geoLocation;
    }
}
