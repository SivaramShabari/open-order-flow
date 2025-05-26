package com.openorderflow.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessFeedDto {
    private UUID businessId;
    private String businessName;
    private String businessType;

    private UUID outletId;
    private String outletName;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private int postalCode;
}