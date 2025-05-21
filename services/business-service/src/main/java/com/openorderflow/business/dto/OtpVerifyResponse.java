package com.openorderflow.business.dto;

public record OtpVerifyResponse(
        BusinessOutletDto.BusinessUserProfileDto profile,
        String jwtToken
) {
}
