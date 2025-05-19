package com.openorderflow.common.auth.model;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record AuthenticatedUser(
        String userId,
        String phone,
        String role,
        String email
) implements UserDetails {

    public static AuthenticatedUser fromClaims(Claims claims) {
        return new AuthenticatedUser(
                claims.get("userId", String.class),
                claims.getSubject(),
                claims.get("role", String.class),
                claims.get("email", String.class)
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override public String getPassword() { return null; }
    @Override public String getUsername() { return phone; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}