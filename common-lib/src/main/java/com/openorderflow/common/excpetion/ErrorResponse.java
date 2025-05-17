package com.openorderflow.common.excpetion;

import java.time.Instant;

public record ErrorResponse(
        Instant timestamp,
        String code,
        String message
) {}
