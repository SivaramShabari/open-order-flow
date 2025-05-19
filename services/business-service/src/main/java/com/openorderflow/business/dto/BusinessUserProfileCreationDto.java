package com.openorderflow.business.dto;

import com.openorderflow.business.entity.BusinessUserProfile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserProfileCreationDto {

    @NotNull
    private String name;

    @NotNull
    @Size(min = 10, max = 13)
    private String phone;

    @NotNull
    @Email
    private String email;

    @NotNull
    private UUID businessOutletId;

    @NotNull
    private BusinessUserProfile.BusinessAdminRoleEnum role;
}
