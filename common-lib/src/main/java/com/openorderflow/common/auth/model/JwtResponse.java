package com.openorderflow.common.auth.model;

public record JwtResponse(
        String accessToken,
        String tokenType
) {
    public JwtResponse(String token) {
        this(token, "Bearer");
    }
}