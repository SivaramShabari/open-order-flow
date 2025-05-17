package com.openorderflow.customer.dto;

import com.openorderflow.customer.entity.CustomerProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OtpVerifyResponse(
        CustomerProfile customerProfile,
        @NotBlank String jwtToken
) {
}
