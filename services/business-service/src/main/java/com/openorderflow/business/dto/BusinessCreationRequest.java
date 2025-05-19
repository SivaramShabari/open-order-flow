package com.openorderflow.business.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BusinessCreationRequest {

    @NotNull
    private String ownerName;

    @NotNull
    @Size(min =  10, max = 13)
    private  String ownerPhone;

    @NotNull
    @Email
    private String ownerEmail;

    @NotNull
    private String businessName;

    @NotNull
    private String businessType;

    @NotNull
    private String businessOutletName;

    @NotNull
    private String businessOutletAddressLine1;
    
    @NotNull
    private String businessOutletAddressLine2;

    private String businessOutletAddressLine3;

    private String businessOutletCity;
    
    @NotNull
    private int businessOutletPostalCode;

    @NotNull
    private BigDecimal businessOutletLatitude;

    @NotNull
    private BigDecimal businessOutletLongitude;

}
