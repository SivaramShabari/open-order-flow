package com.openorderflow.business.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessCreationRequestDto {

    @NotNull
    private BusinessUserProfileCreationDto owner;

    @NotNull
    private BusinessDto business;

    @NotNull
    private BusinessOutletDto outlet;
}
