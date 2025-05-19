package com.openorderflow.business.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessOutletDto {

    @NotNull
    private String name;

    @NotNull
    private String addressLine1;

    @NotNull
    private String addressLine2;

    private String addressLine3;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private int postalCode;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;
}
