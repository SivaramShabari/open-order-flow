package com.openorderflow.common.common;

import java.math.BigDecimal;

public record GeoLocation(
        BigDecimal latitude,
        BigDecimal longitude
) {
}
