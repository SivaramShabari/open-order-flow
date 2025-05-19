package com.openorderflow.business.dto;

import com.openorderflow.business.entity.BusinessUserProfile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUserCreationRequest {

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private UUID businessOutletId;

    @NotNull
    private BusinessUserProfile.BusinessAdminRoleEnum role;
}
