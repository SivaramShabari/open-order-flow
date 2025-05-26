package com.openorderflow.common.auth.util;

import com.openorderflow.common.auth.model.AuthenticatedUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserContext {
    public static AuthenticatedUser get() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof AuthenticatedUser user))
            throw new IllegalStateException("No authenticated user found");
        return user;
    }

    public static String userId() {
        return get().userId();
    }

    public static String phone() {
        return get().phone();
    }

    public static String role() {
        return get().role();
    }

}
