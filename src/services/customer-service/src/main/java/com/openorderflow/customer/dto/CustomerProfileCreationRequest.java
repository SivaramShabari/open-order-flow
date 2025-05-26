package com.openorderflow.customer.dto;

import com.openorderflow.common.common.GeoLocation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerProfileCreationRequest(
        @Email String email,
        @NotNull String name,
        @NotNull GeoLocation location,
        @NotNull String phone
        ) {
}
