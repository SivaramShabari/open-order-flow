package com.openorderflow.customer.dto;

import com.openorderflow.common.dto.customer.CustomerProfileDto;
import com.openorderflow.customer.entity.CustomerProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OtpVerifyResponse(
        CustomerProfileDto customerProfile,
        @NotBlank String jwtToken
) {
}
