package com.openorderflow.business.dto;

import com.openorderflow.business.entity.BusinessOutlet;
import com.openorderflow.business.entity.BusinessUserProfile;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessOutletDto {

    @NotNull
    private String name;

    @NotNull
    private String addressLine1;

    @NotNull
    private String addressLine2;

    private String addressLine3;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private int postalCode;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BusinessUserProfileDto {

        private UUID id;
        private String phone;
        private String name;
        private String email;
        private BusinessUserProfile.BusinessAdminRoleEnum role;
    }
}
