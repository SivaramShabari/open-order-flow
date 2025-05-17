package com.openorderflow.common.auth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record OtpVerifyRequest(
        @NotBlank
        @Pattern(regexp = "\\d{10}")
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // assume 4–6 digit OTP
        String otp
) {}