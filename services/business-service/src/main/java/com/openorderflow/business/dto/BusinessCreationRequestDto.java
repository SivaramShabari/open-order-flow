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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessCreationRequest {

    @NotNull
    private BusinessUserProfileCreationDto owner;

    @NotNull
    private BusinessDto business;

    @NotNull
    private BusinessOutletDto outlet;
}
