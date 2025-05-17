package com.openorderflow.common.auth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PhoneLoginRequest(
        @NotBlank
        @Pattern(regexp = "\\d{10}")  // Adjust as per your region
        String phone
) {}
