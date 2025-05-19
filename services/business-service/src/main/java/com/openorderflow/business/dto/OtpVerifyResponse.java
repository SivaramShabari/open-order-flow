package com.openorderflow.business.dto;

import com.openorderflow.business.entity.BusinessUserProfile;
import com.openorderflow.common.dto.business.BusinessUserProfileDto;

public record OtpVerifyResponse(
        BusinessUserProfileDto profile,
        String jwtToken
) {
}
