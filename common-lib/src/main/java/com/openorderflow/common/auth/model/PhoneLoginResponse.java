package com.openorderflow.common.auth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PhoneLoginResponse(
        @NotNull Boolean isOtpSent,
        @NotNull Boolean isNewUser,
        @NotBlank String phoneNumber
) {
}
